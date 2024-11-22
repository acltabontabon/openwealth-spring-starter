package com.acltabontabon.openwealth.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class AssetDetails {

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc..
     */
    private String nameOfBank;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String domicileOfBank;

    /**
     * Indicates the type of property.
     */
    private String typeOfProperty;

    /**
     * Usage of the property.
     */
    private String usageOfProperty;

    /**
     * Place of the property.
     */
    private String placeOfProperty;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String countryOfProperty;

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String nameOfCompany;

    /**
     * Percentage of shareholding in the company.
     */
    private Integer shareholdingsInPercent;

    /**
     * Type of non-tradable assets.
     */
    @JsonAlias("typeOfNon-tradableAsset")
    private String typeOfNonTradableAsset;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformation;
}
