package com.acltabontabon.openwealth.services.orderplacement.placement;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.orderplacement.Order;
import com.acltabontabon.openwealth.models.orderplacement.RequestedOrder;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class OrderCreator extends CreateCommand<Result<Order>> {

    private final RestClient restClient;
    private final ApiProperties.OrderPlacement apiProperties;

    private final String correlationId;

    private final RequestedOrder requestedOrder;

    @Override
    protected Result<Order> execute() {
        try {
            Order response = restClient.post()
                .uri(apiProperties.getOrders())
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(requestedOrder)
                .retrieve()
                .body(Order.class);

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to create order placement", e.getStatusMessage());
        }
    }
}