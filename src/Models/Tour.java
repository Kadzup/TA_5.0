package Models;

import java.lang.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * <h1>Welcome to TA!</h1>
 * <p>This class allows us to create object of available Tour.<br>
 * If You want to <b>Get</b> information from <b>Fields</b><br>
 * Then You should use Getters functions.<br>
 * You can read more about them bellow.
 * </p>
 *
 * @version <b>0.0.1</b>, <i><b>in dev</b></i>
 * @author <a href="https://github.com/Kadzup"><b><u>Kadzup</u></b></a>
 * @since <b>15.09.2019</b>
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tour {
    /**
     * Information about Company name
     * */
    @NotNull
    @NotBlank
    private String companyName;
    /**
     * Information about Tour price
     * */
    @PositiveOrZero
    private float price;
    /**
     * Information about Company Taxes
     * */
    @Positive
    private float taxes;
    /**
     * Information about Tour Owner
     * */
    @NotNull
    private Worker owner;
    /**
     * Id of connected Service
     * */
    @NotNull
    private Service service;
    /**
     * Id of connected Location
     * */
    @NotNull
    private Location location;
    /**
     * Information about transport
     * */
    @NotEmpty
    @NotBlank
    private String transport;
    /**
     * Information about Visa Services accessibility
     * */
    @NotNull
    private Boolean visa_service;
    /**
     * Information about Accommodation accessibility
     * */
    @NotNull
    private Boolean accommodation;
    /**
     * Information about Accommodation type
     * */
    @NotEmpty
    @NotBlank
    private String accommodation_type;
    /**
     *  Information about Food accessibility
     * */
    @NotNull
    private Boolean food;
    /**
     *  Information about Food type
     * */
    @NotEmpty
    @NotBlank
    private String food_type;
    /**
     * Information about Excursion accessibility
     * */
    @NotNull
    private Boolean excursion;



    /**
     * Basic Constructor of Tour object
     * */
    Tour(){
        companyName = "Test CO";

        price = 0.0f;
        taxes = 0.0f;
        owner = new Worker();

        service = new Service();
        location = new Location();

        transport = "New Transport";
        visa_service = false;

        accommodation = false;
        accommodation_type = "Basic";

        food = false;
        food_type = "Basic";

        excursion = false;
    }

    public Tour(String companyName, float price, float taxes, Worker owner, Service service, Location location, String transport, Boolean visa_service, Boolean accommodation, String accommodation_type, Boolean food, String food_type, Boolean excursion) {
        this.companyName = companyName;
        this.price = price;
        this.taxes = taxes;
        this.owner = owner;
        this.service = service;
        this.location = location;
        this.transport = transport;
        this.visa_service = visa_service;
        this.accommodation = accommodation;
        this.accommodation_type = accommodation_type;
        this.food = food;
        this.food_type = food_type;
        this.excursion = excursion;
    }

    /**
     * @return Name of Company that sale the tour
     * */
    public String getCompanyName(){
        return this.companyName;
    }
    /**
     * @return Price of tour
     * */
    public float getPrice(){
        return this.price;
    }
    /**
     * @return Company taxes for tour
     * */
    public float getTaxes(){
        return this.taxes;
    }
    /**
     * @return Id of service that gives that tour
     * */
    public Service getService(){
        return service;
    }
    /**
     * @return Id of location where tour located
     * */
    public Location getLocation(){
        return location;
    }
    /**
     * @return Information about transport
     * */
    public String getTransport(){
        return this.transport;
    }
    /**
     * @return Is visa service allowed?
     * */
    public Boolean getVisa_service(){
        return this.visa_service;
    }
    /**
     * @return Is accommodation allowed?
     * */
    public Boolean getAccommodation(){
        return this.accommodation;
    }
    /**
     * @return Information about accommodation
     * */
    public String getAccommodation_type(){
        return this.accommodation_type;
    }
    /**
     * @return Is food allowed?
     * */
    public Boolean getFood(){
        return this.food;
    }
    /**
     * @return Information about food
     * */
    public String getFood_type(){
        return this.food_type;
    }
    /**
     * @return Is excursion allowed?
     * */
    public Boolean getExcursion(){
        return this.excursion;
    }
    /**
     * @return Object of Worker, which contain owner information
     * */
    public Worker getOwner(){
        return this.owner;
    }

    @JsonIgnoreProperties
    public float calcPrice(){
        if(getService() != null)
            if(getPrice()+getService().getPrice()+getTaxes() >= 0) {
                return (float)(getPrice() + getService().getPrice() + getTaxes());
            }
        return -1;
    }

    @JsonIgnoreProperties
    public String getContact(){
        if((getOwner() != null) && (getLocation()!= null))
            return (
                "Contact name: " + getOwner().getFullName() +
                "\nAddress: " + getLocation().getFullAddress() +
                "\nContact phone: " + getOwner().getPhone() + ", " + getLocation().getPhone()
                );
        return "Not enough info";
    }

    @JsonIgnoreProperties
    public String getInfo(Client client){
        if(client != null ) {
            if (getService() != null || getLocation() != null) {
                String result = "";
                String currency = "$";

                result = "Company: " + getCompanyName() +
                        "\nPerson: " + client.getFullName() +
                        "\n\nLocation: " + getLocation().getName() +
                        "\nAddress: " + getLocation().getFullAddress() +
                        "\nTransport: " + getTransport() +
                        "\nFood: ";
                if (getFood()) {
                    result += "Yes, " + getFood_type();
                } else {
                    result += "No";
                }
                result += "\nAccommodation: ";
                if (getAccommodation()) {
                    result += "Yes, " + getAccommodation_type();
                } else {
                    result += "No";
                }
                result += "\nExcursion: ";
                if (getExcursion()) {
                    result += "Yes";
                } else {
                    result += "No";
                }
                result += "\n\nAdditional service: " + getService().getName() +
                        "\n\nVisa services: ";
                if (getVisa_service()) {
                    result += "Yes";
                } else {
                    result += "No";
                }
                result += "\nPrice: " + getPrice() + currency + " + " + getService().getPrice() + currency + " for service " +
                        "\nTaxes: " + getTaxes() + currency +
                        "\nTotal: " + calcPrice() + currency;
                return result;
            }
        }
        return "Not enough info";
    }


    public static final class Builder {
        private String companyName = null;
        private float price;
        private float taxes;
        private Worker owner;
        private Service service;
        private Location location;
        private String transport;
        private Boolean visa_service;
        private Boolean accommodation;
        private String accommodation_type;
        private Boolean food;
        private String food_type;
        private Boolean excursion;

        public Builder() {
        }

        public static Builder aTour() {
            return new Builder();
        }

        public Builder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder setPrice(float price) {
            this.price = price;
            return this;
        }

        public Builder setTaxes(float taxes) {
            this.taxes = taxes;
            return this;
        }

        public Builder setOwner(Worker owner) {
            this.owner = owner;
            return this;
        }

        public Builder setService(Service service) {
            this.service = service;
            return this;
        }

        public Builder setLocation(Location location) {
            this.location = location;
            return this;
        }

        public Builder setTransport(String transport) {
            this.transport = transport;
            return this;
        }

        public Builder setVisa_service(Boolean visa_service) {
            this.visa_service = visa_service;
            return this;
        }

        public Builder setAccommodation(Boolean accommodation) {
            this.accommodation = accommodation;
            return this;
        }

        public Builder setAccommodation_type(String accommodation_type) {
            this.accommodation_type = accommodation_type;
            return this;
        }

        public Builder setFood(Boolean food) {
            this.food = food;
            return this;
        }

        public Builder setFood_type(String food_type) {
            this.food_type = food_type;
            return this;
        }

        public Builder setExcursion(Boolean excursion) {
            this.excursion = excursion;
            return this;
        }

        public Tour build() {
            return new Tour(companyName, price, taxes, owner, service, location, transport, visa_service, accommodation, accommodation_type, food, food_type, excursion);
        }
    }
}
