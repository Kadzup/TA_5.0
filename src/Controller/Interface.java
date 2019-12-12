package Controller;
import java.io.IOException;
import java.time.LocalDate;

import DB.DataBaseControll;
import Models.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Interface{

    public Interface(){}

    public static void main(String args[]) throws IOException {

        Interface interf = new Interface();

        Client client = new Client.Builder()
                .setBDay(LocalDate.of(2019,10, 10))
                .setEmail("dstefurak@email.net")
                .setFName("Dmytro")
                .setLName("Stefurak")
                .setPaymentInfo("0000-0000-0000-0000")
                .setPhone("+380 5088 340 11")
                .build();

        Worker worker = new Worker.Builder()
                .setAddress("some street")
                .setBDay(LocalDate.of(2019,10, 10))
                .setFName("Alex")
                .setLName("Lusek")
                .setPhone("+000 000 0000 00")
                .setPosition("software engineer")
                .setSalary(1500)
                .build();

        Country country = new Country.Builder()
                .setArea(50000)
                .setCapital("some capital")
                .setName("Country")
                .setPopulation(1000000)
                .build();

        Location location = new Location.Builder()
                .setAddress("some street")
                .setAvailable(true)
                .setCountry(country)
                .setName("some name")
                .setPhone("+000 000 0000 00")
                .setRating(5)
                .build();

        Service service = new Service.Builder()
                .setName("some name")
                .setPrice(1000)
                .setProviderCompany("some company")
                .setRiskCover(true)
                .build();

        Tour tour = new Tour.Builder()
                .setAccommodation(true)
                .setAccommodation_type("some type")
                .setCompanyName("New company")
                .setExcursion(true)
                .setFood(true)
                .setFood_type("some type")
                .setLocation(location)
                .setOwner(worker)
                .setPrice(1000)
                .setService(service)
                .setTaxes(5)
                .setTransport("some car")
                .setVisa_service(true)
                .build();

        Order order = new Order.Builder()
                .setArrival(LocalDate.of(2019,10, 10))
                .setClient(client)
                .setDeparture(LocalDate.of(2019,10, 10))
                .setPrice(1000)
                .setTour(tour)
                .build();

        DataBaseControll db = new DataBaseControll();

        db.getCountry("%a%",0);
    }


}