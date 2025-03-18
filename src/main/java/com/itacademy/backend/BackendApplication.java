package com.itacademy.backend;

import com.itacademy.backend.country.Country;
import com.itacademy.backend.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Autowired
	private CountryService countryService;

	@Override
	public void run(String... args) throws Exception {
		List<Country> countries = countryService.getAll();
		for(Country country : countries) {
			System.out.println(country);
		}
	}
}
