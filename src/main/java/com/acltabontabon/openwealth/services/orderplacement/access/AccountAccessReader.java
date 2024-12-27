package com.acltabontabon.openwealth.services.orderplacement.access;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.orderplacement.AccountAccess;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class AccountAccessReader extends ReadCommand<Result<List<AccountAccess>>> {

    private final RestClient restClient;
    private final ApiProperties.OrderPlacement apiProperties;

    private String correlationId;

    public AccountAccessReader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @Override
    protected Result<List<AccountAccess>> execute() {
        try {
            List<AccountAccess> response = restClient.get()
                .uri(apiProperties.getAccountAccesses())
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch list of account accesses", e.getStatusMessage());
        }
    }
}