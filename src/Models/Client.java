package Models;

import java.lang.*;
import java.time.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Client implements Comparable<Client>{

    @Size(min = 2, max = 15)
    @NotBlank
    @NotEmpty
    private String fName;

    @Size(min = 2, max = 15)
    @NotBlank
    @NotEmpty
    private String lName;

    @PastOrPresent
    @NotNull
    private LocalDate bDay;

    @Size(min = 16, max = 16)
    @NotBlank
    @NotEmpty
    private String paymentInfo;

    @NotBlank
    @NotEmpty
    private String phone;

    @Email
    @NotBlank
    @NotEmpty
    private String email;

    public Client(){
        fName = "Null";
        lName = "Null";

        bDay = LocalDate.now();

        paymentInfo = "0000 0000 0000 0000";

        phone = "+000-00-00-0000";
        email = "none@none.none";
    }

    public Client(String fName, String lName, LocalDate bDay, String paymentInfo, String phone, String email) {
        this.fName = fName;
        this.lName = lName;
        this.bDay = bDay;
        this.paymentInfo = paymentInfo;
        this.phone = phone;
        this.email = email;
    }

    /**
     *  GETTERS / SETTERS   begin
     */
    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public LocalDate getbDay() {
        return bDay;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    /**
     *  GETTERS / SETTERS end
     */

    @JsonIgnoreProperties
    public String getFullName(){
        return (getfName()+" "+getlName());
    }
    @JsonIgnoreProperties
    public String getClientContact(){
        return("Client: "+getFullName()+"\nContact: " + getPhone() + ", " + getEmail());
    }

    @Override
    public int compareTo(Client client) {
        if (this.bDay.getYear() == client.bDay.getYear()) {
            return 0;
        } else if (this.bDay.getYear() < client.bDay.getYear()) {
            return -1;
        } else {
            return 1;
        }
    }

    public int compareTo(String email){
        if (this.getEmail() == email) {
            return 1;
        } else
            return -1;
    }

    public static final class Builder {
        private String fName;
        private String lName;
        private LocalDate bDay;
        private String paymentInfo;
        private String phone;
        private String email;

        public Builder() {}

        public static Builder aClient() {
            return new Builder();
        }

        public Builder setFName(String fName) {
            this.fName = fName;
            return this;
        }

        public Builder setLName(String lName) {
            this.lName = lName;
            return this;
        }

        public Builder setBDay(LocalDate bDay) {
            this.bDay = bDay;
            return this;
        }

        public Builder setPaymentInfo(String paymentInfo) {
            this.paymentInfo = paymentInfo;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Client build() {
            return new Client(fName, lName, bDay, paymentInfo, phone, email);
        }
    }
}
