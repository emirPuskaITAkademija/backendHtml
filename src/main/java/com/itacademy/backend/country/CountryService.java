package com.itacademy.backend.country;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CountryService {

    private final JdbcTemplate jdbcTemplate;

    public CountryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Country> getAll() {
        List<Map<String, Object>> countries = getCountries();
        List<Country> countryList = new ArrayList<>();
        for (Map<String, Object> country : countries) {
            Country countryObj = new Country();
            countryObj.setCountry(country.get("country")+"");
            countryObj.setCountryId(country.get("country_id")+"");
            countryList.add(countryObj);
        }
        return countryList;
    }

    public List<Map<String, Object>> getCountries() {
        String sql = "SELECT * FROM country";
        return jdbcTemplate.queryForList(sql);
    }
}
