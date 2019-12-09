package Tests;

import Models.*;
import org.junit.jupiter.api.*;

class TestCountry {

    @Test
    void getInfo() {
        Country country = new Country.Builder()
                .setPopulation(41953020)
                .setName("Ukraine")
                .setCapital("Kiev")
                .setArea(603628)
                .build();

        Assertions.assertEquals(true, country.getInfo().contains("Name: Ukraine"));
        Assertions.assertEquals(true, country.getInfo().contains("Capital: Kiev"));

        Country c2 = new Country.Builder().build();
        Assertions.assertEquals(true, c2.getInfo().contains("Capital: null"));
        Assertions.assertEquals(true, c2.getInfo().contains("0.0"));
    }

    @Test
    void getCountry() {
        Country country = new Country.Builder()
                .setName("Ukraine")
                .setCapital("Kiev")
                .build();

        Assertions.assertEquals(true, country.getCountry().contains("Ukraine, Kiev"));

        Country c2 = new Country.Builder().build();
        Assertions.assertEquals(true, c2.getCountry().contains("null, null"));
    }
}