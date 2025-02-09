package com.acltabontabon.openwealth.services.custodyservices.position;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.custodyservices.AccountPositionStatement;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class AccountPositionStatementReader extends ReadCommand<Result<AccountPositionStatement>> {

    private final RestClient restClient;
    private final ApiProperties.CustodyServices apiProperties;

    private final String correlationId;
    private final String customerId;

    private final LocalDate date;
    private final boolean eodIndicator;
    private final String dateType;

    @Override
    protected Result<AccountPositionStatement> execute() {
        try {
            AccountPositionStatement response = restClient.get()
                .uri(builder -> builder.path(apiProperties.getAccountPositionStatement())
                    .queryParam("date", this.date.toString())
                    .queryParam("eodIndicator", this.eodIndicator)
                    .queryParam("dateType", this.dateType)
                    .build(this.customerId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(AccountPositionStatement.class);

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch account position statement", e.getStatusMessage());
        }
    }
}
