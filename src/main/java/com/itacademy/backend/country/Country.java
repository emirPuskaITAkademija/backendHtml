package com.itacademy.backend.country;

import java.io.Serializable;
import java.util.StringJoiner;

public class Country implements Serializable {
    private String countryId;
    private String country;

    public Country() {
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Country.class.getSimpleName() + "[", "]")
                .add("countryId='" + countryId + "'")
                .add("country='" + country + "'")
                .toString();
    }
}
