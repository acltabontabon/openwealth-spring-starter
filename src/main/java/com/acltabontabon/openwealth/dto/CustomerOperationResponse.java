package com.acltabontabon.openwealth.dto;

import com.acltabontabon.openwealth.model.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class CustomerOperationResponse implements ApiResponse {

    private String temporaryId;

    private String externalReference;

    @Singular
    private List<Customer> customers;

}
