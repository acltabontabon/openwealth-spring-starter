package com.acltabontabon.openwealth.service.customer;

import static com.acltabontabon.openwealth.config.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.model.Person;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.service.QueryAsyncCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PersonQuery extends QueryAsyncCommand<List<Person>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String correlationId;

    public SinglePersonQuery withPersonId(String personId) {
        return new SinglePersonQuery(restClient, apiProperties, customerId, personId, this.correlationId);
    }

    @Override
    protected List<Person> execute() {
        return restClient.get()
            .uri(apiProperties.getPersons(), this.customerId)
            .header(HEADER_CORRELATION_ID, this.correlationId)
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});
    }
}
