package com.itacademy.backend;

import com.itacademy.backend.country.Country;
import com.itacademy.backend.country.CountryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class BackendController {

    private final CountryService countryService;

    public BackendController(CountryService countryService) {
        this.countryService = countryService;
    }

    //http://localhost:8080/country
    @PostMapping("/country")
    public String selectCountry(@RequestParam String selectedCountry){
        return """
                <html>
                <head>
                <meta charset="UTF-8">
                </head>
                <body>
                <h1>Odabrana zemlja '%s'. Odgovor na POST request</h1>
                </body>
                </html>
                """.formatted(selectedCountry);
    }

    @GetMapping("/countries")
    @CrossOrigin(origins = "http://localhost:63342")
    public List<String> listCountries(){
        return countryService
                .getAll()
                .stream()
                .map(Country::getCountry)
                .toList();
    }

    //GET http://localhost:8080/country
    @GetMapping("/country")
    public String getCountries() {
        StringBuilder htmlResponse = new StringBuilder("""
                <html>
                <head>
                <meta charset="UTF-8">
                </head>
                <body>
                <h2>Odaberi državu</h2>
                <form action="/country" method="POST">
                 <label for="countrySelectId">Odaberi državu</label>
                   <select id="countrySelectId" name="selectedCountry">
                """);
        List<String> countries = countryService
                .getAll()
                .stream()
                .map(Country::getCountry)
                .toList();
        for(String country : countries){
            htmlResponse
                    .append("<option value=")
                    .append(country)
                    .append(">")
                    .append(country)
                    .append("</option>");
        }
        htmlResponse.append("""
                   </select>
                   <input type="submit">
                </form>
                </body>
                </html>
                """);
        return htmlResponse.toString();
    }

    @GetMapping("/person")
    public String method(@RequestParam String firstName, @RequestParam String lastName) {
        String templateResponse = """
                <html>
                <head>
                <meta charset="UTF-8">
                </head>
                <body>
                <h1>Hello '%s %s'. Odgovor na GET request</h1>
                </body>
                </html>
                """;
        String formatiranResponse = String.format(templateResponse, firstName, lastName);
        return formatiranResponse;
    }


    @PostMapping("/person")
    public String postMethod(@RequestParam String firstName, @RequestParam String lastName) {
        String templateResponse = """
                <html>
                <head>
                <meta charset="UTF-8">
                </head>
                <body>
                <h1>Hello '%s %s'. Odgovor šaljem na tvoj POST request</h1>
                </body>
                </html>
                """;
        String formatiranResponse = String.format(templateResponse, firstName, lastName);
        return formatiranResponse;
    }

    @GetMapping("/car")
    public String favouriteCar(@RequestParam String selectedCar) {
        return """
                <html>
                <head>
                <meta charset="UTF-8">
                </head>
                <body>
                <h1>Tvoj omiljeni auto je '%s'. Odgovor šaljem na tvoj GET request</h1>
                </body>
                </html>
                """.formatted(selectedCar);
    }

    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password) {
        if ("admin".equalsIgnoreCase(username) && "admin".equalsIgnoreCase(password)) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String language,
                           @RequestParam String bike,
                           @RequestParam String car,
                           @RequestParam String yahta,
                           @RequestParam LocalDate datum,
                           @RequestParam String favColor) {
        String htmlTemplate = """
                <html>
                <head>
                <meta charset="UTF-8">
                </head>
                <body>
                <h3>Unijeli ste  '%s', '%s', '%s'.</h3>
                <p>Ja, '%s %s' posjedujem '%s', '%s' i '%s'</p><br>
                <p>Rođen sam ko vrlo mlad '%s'</p><br>
                <p>Omiljena boja '%s'</p>
                </body>
                """;
        return String.format(htmlTemplate, name, surname, language, name, surname, bike, car, yahta, datum.toString(), favColor);
    }
}
