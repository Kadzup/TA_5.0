package Models;

import java.lang.*;
import java.time.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    @NotNull
    @PastOrPresent
    private LocalDate departure;

    @NotNull
    @FutureOrPresent
    private LocalDate arrival;

    @NotNull
    private Client client;

    @NotNull
    private Tour tour;

    @NotNull
    @PositiveOrZero
    @Min(11)
    private float price;

    public Order(LocalDate departure, LocalDate arrival, Client client, Tour tour, float price) {
        this.departure = departure;
        this.arrival = arrival;
        this.client = client;
        this.tour = tour;
        this.price = price;
    }

    /**
     *  GETTERS / SETTERS begin
     */
    public Client getClient() {
        return client;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public Tour getTour() {
        return tour;
    }

    /**
     *  GETTERS / SETTERS end
     */

    @JsonIgnoreProperties
    public boolean checkTour(){
        return getTour().getLocation().isAvailable();
    }

    @JsonIgnoreProperties
    public String orderTour(){
        if(!checkTour())
            return "Not available";
        String result = "";

        result = getTour().getInfo(getClient());
        result +=   "\n\nDeparture date: "+ getDeparture()+
                    "\nArrival date: " + getArrival();

        return result;
    }

    public static final class Builder {
        private LocalDate departure;
        private LocalDate arrival;
        private Client client;
        private Tour tour;
        private float price;

        public Builder() {
        }

        public static Builder anOrder() {
            return new Builder();
        }

        public Builder setDeparture(LocalDate departure) {
            this.departure = departure;
            return this;
        }

        public Builder setArrival(LocalDate arrival) {
            this.arrival = arrival;
            return this;
        }

        public Builder setClient(Client client) {
            this.client = client;
            return this;
        }

        public Builder setTour(Tour tour) {
            this.tour = tour;
            return this;
        }

        public Builder setPrice(float price) {
            this.price = price;
            return this;
        }

        public Order build() {
            return new Order(departure, arrival, client, tour, price);
        }
    }
}

