package Tests;

import Models.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

class TestsWorker {

    @Test
    void getYear() {
        Worker w1 = new Worker.Builder().setBDay(LocalDate.now()).build();

        Assertions.assertEquals(2019, w1.getYear());
    }

    @Test
    void getInfo() {
        Worker w1 = new Worker.Builder()
                .setBDay(LocalDate.now())
                .setFName("Alex")
                .setLName("Lusek")
                .setPosition("software engineer")
                .setSalary(1500)
                .build();

        String ex = w1.getInfo();

        Assertions.assertEquals(true, w1.getInfo().contains("First Name: Alex"));
        Assertions.assertEquals(true, w1.getInfo().contains(LocalDate.now().toString()));
    }

    @Test
    void getFullName() {
        Worker w1 = new Worker.Builder().setFName("Alex").setLName("Lusek").build();

        Assertions.assertEquals(true, w1.getFullName().contains("Alex Lusek"));
        Assertions.assertEquals(true, new Worker.Builder().build().getFullName().contains(""));
    }
}