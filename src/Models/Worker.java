package Models;

import java.lang.*;
import java.time.*;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

public class Worker {
    /**
     * First name of Worker
     * */
    @Size(min=2, max = 20)
    private String fName;
    /**
     * Last name of Worker
     * */
    @Size(min=2, max = 20)
    private String lName;
    /**
     * Birth date of Worker
     * */
    @NotNull
    @PastOrPresent
    private LocalDate bDay;

    /**
     * Information about Worker Salary
     * */
    @PositiveOrZero
    private float salary;
    /**
     * Information about Worker Position
     * */
    @NotBlank
    private String position;

    /**
     * Information about Worker Address
     * */
    @NotBlank
    private  String address;
    /**
     * Information about Worker Phone
     * */
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
    private  String phone;

    /**
     * Basic constructor of Worker object
     * */
    Worker(){
    }

    public Worker(String fName, String lName, LocalDate bDay, float salary, String position, String address, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.bDay = bDay;
        this.salary = salary;
        this.position = position;
        this.address = address;
        this.phone = phone;
    }

    public Worker(Worker obj) {
        this.fName = obj.fName;
        this.lName = obj.lName;
        this.bDay = obj.bDay;
        this.salary = obj.salary;
        this.position = obj.position;
        this.address = obj.address;
        this.phone = obj.phone;
    }

    /**
     * GETTER / SETTER
     */

    public String getfName(){
        return fName;
    }
    public String getlName(){
        return lName;
    }
    public LocalDate getbDay(){
        return bDay;
    }
    public float getSalary(){
        return salary;
    }
    public String getPosition(){
        return position;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }

    @JsonIgnore
    public int getYear(){
        return this.bDay.getYear();
    }

    @JsonIgnore
    public void changeName(String firstName, String lastName){
        fName = firstName;
        lName = lastName;
    }

    @JsonIgnore
    public float calculateSalary(float taxes){
        if(salary-taxes > 0){
            return salary-taxes;
        }
        else{
            return -1;
        }
    }

    @JsonIgnore
    public String getInfo(){
        return(
                "First Name: " + getfName() +
                "\nLast Name:" + getlName() +
                "\nBirth Day: " + getbDay() +
                "\nPosition: " + getPosition() +
                "\nSalary: " + getSalary()
        );
    }

    @JsonIgnore
    public String howToFind(){
        return (
                "Location: " + getAddress()+
                "\nContact Phone: " + getPhone()
        );
    }

    @JsonIgnore
    public String getFullName(){
        return (getfName() + " " + getlName());
    }

    public static final class Builder {
        private String fName;
        private String lName;
        private LocalDate bDay;
        private float salary;
        private String position;
        private  String address;
        private  String phone;

        public Builder() {
        }

        public static Builder aWorker() {
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

        public Builder setSalary(float salary) {
            this.salary = salary;
            return this;
        }

        public Builder setPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Worker build() {
            return new Worker(fName, lName, bDay, salary, position, address, phone);
        }
    }
}
