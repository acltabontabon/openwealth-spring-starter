package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.config.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dto.CustomerResponse;
import com.acltabontabon.openwealth.models.Customer;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleCustomerQuery extends QueryAsyncCommand<CustomerResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String correlationId;

    private boolean fullRecord;

    public SingleCustomerQuery fullRecord() {
        this.fullRecord = true;
        return this;
    }

    public CustomerResponse fetch() {
        return execute();
    }

    @Override
    public CustomerResponse execute() {
        try {
            Customer customer = restClient.get()
                .uri(builder -> {
                    if (this.fullRecord) {
                        return builder.path(apiProperties.getCustomerDetails()).build(this.customerId);
                    } else {
                        return builder.path(apiProperties.getCustomer()).build(this.customerId);
                    }
                })
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Customer.class);

            return CustomerResponse.builder()
                .customer(customer)
                .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch customer",e);
        }
    }
}
