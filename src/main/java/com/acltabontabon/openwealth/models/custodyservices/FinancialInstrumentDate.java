package com.acltabontabon.openwealth.models.custodyservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInstrumentDate {

    /**
     * Indicates the type of date.
     * FIXME: this should be an enum.
     */
    private String type;

    /**
     * Date according to ISO 8601 i.e. YYYY-MM-DD format.
     */
    private String date;
}