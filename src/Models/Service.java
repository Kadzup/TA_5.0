package Models;

import java.lang.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 5, max = 128)
    private String name;

    @NotNull
    @PositiveOrZero
    @Min(10)
    private double price;

    @NotNull
    @NotEmpty
    @NotBlank
    private String providerCompany;

    @NotNull
    private boolean riskCover;

    Service(){
        name = "New Service";
        price = 0.0;
        providerCompany = "New Provider";
        riskCover = false;
    }

    public Service(String name, double price, String providerCompany, boolean riskCover) {
        this.name = name;
        this.price = price;
        this.providerCompany = providerCompany;
        this.riskCover = riskCover;
    }

    /**
     * GETTER / SETTER
     */

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public String getProviderCompany(){
        return providerCompany;
    }
    public boolean isRiskCover(){
        return riskCover;
    }

    public static final class Builder {
        private String name;
        private double price;
        private String providerCompany;
        private boolean riskCover;

        public Builder() {
        }

        public static Builder aService() {
            return new Builder();
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setProviderCompany(String providerCompany) {
            this.providerCompany = providerCompany;
            return this;
        }

        public Builder setRiskCover(boolean riskCover) {
            this.riskCover = riskCover;
            return this;
        }

        public Service build() {
            return new Service(name, price, providerCompany, riskCover);
        }
    }
}