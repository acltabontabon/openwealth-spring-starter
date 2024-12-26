package com.acltabontabon.openwealth.services.customermgmt.document;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagementResourcePaths;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.services.QueryCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class DocumentQuery extends QueryCommand<GenericResponse<List<Contact>>> {

    private final RestClient restClient;
    private final CustomerManagementResourcePaths apiProperties;

    private final String correlationId;
    private final String customerId;

    @Override
    protected GenericResponse<List<Contact>> execute() {
        try {
            List<Contact> contacts = restClient.get()
                .uri(builder -> builder.path(apiProperties.getCustomerDocuments()).build(this.customerId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return new GenericResponse<>(contacts);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch documents",e);
        }
    }

    public SingleDocumentQuery withDocumentId(String documentId) {
        return new SingleDocumentQuery(restClient, apiProperties, correlationId, customerId, documentId);
    }
}
