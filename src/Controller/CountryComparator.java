package Controller;

import Models.Country;
import java.util.Comparator;

public class CountryComparator implements Comparator<Country> {


    public int compare(Country c1, Country c2) {
        if (c1.getPopulation() == c2.getPopulation()) {
            return 0;
        }
        if (c1.getPopulation() > c2.getPopulation()) {
            return 1;
        }
        else {
            return -1;
        }
    }
    public int compare(Country c1, String name) {
        if (c1.getName() == name) {
            return 0;
        }
        else {
            return -1;
        }
    }
}
