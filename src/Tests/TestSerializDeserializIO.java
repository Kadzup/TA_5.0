package Tests;

import IO.serializDeserialiIO;
import Models.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class TestSerializDeserializIO {
    @Test
    void SerializeJson(){
        serializDeserialiIO jPars = new serializDeserialiIO();
        String path = "c://";
        Worker worker = new Worker.Builder()
                .setAddress("some street")
                .setBDay(LocalDate.of(2019,10, 10))
                .setFName("Alex")
                .setLName("Lusek")
                .setPhone("+000 000 0000 00")
                .setPosition("software engineer")
                .setSalary(1500)
                .build();

        String expected = jPars.SerializeJson(worker, path);

        Assertions.assertEquals(true, expected.contains("\"fName\":\"Alex\""));
    }

    @Test
    void DeserializeJson(){
        serializDeserialiIO jPars = new serializDeserialiIO();
        String path = "c://";
        Worker worker = new Worker.Builder()
                .setAddress("some street")
                .setBDay(LocalDate.of(2019,10, 10))
                .setFName("Alex")
                .setLName("Lusek")
                .setPhone("+000 000 0000 00")
                .setPosition("software engineer")
                .setSalary(1500)
                .build();

        jPars.SerializeJson(worker, path);
        var expected = new Worker(jPars.DeserializeJson(worker, jPars.lastPath));

        Assertions.assertEquals("Alex", expected.getfName());
        Assertions.assertEquals("some street", expected.getAddress());
    }

    @Test
    void SerializeXml(){
        serializDeserialiIO jPars = new serializDeserialiIO();
        String path = "c://";
        Worker worker = new Worker.Builder()
                .setAddress("some street")
                .setBDay(LocalDate.of(2019,10, 10))
                .setFName("Alex")
                .setLName("Lusek")
                .setPhone("+000 000 0000 00")
                .setPosition("software engineer")
                .setSalary(1500)
                .build();

        String expected = jPars.SerializeXml(worker, path);

        Assertions.assertEquals(true, expected.contains("<fName>Alex</fName"));
    }

    @Test
    void DeserializeXml(){
        serializDeserialiIO jPars = new serializDeserialiIO();
        String path = "c://";
        Worker worker = new Worker.Builder()
                .setAddress("some street")
                .setBDay(LocalDate.of(2019,10, 10))
                .setFName("Alex")
                .setLName("Lusek")
                .setPhone("+000 000 0000 00")
                .setPosition("software engineer")
                .setSalary(1500)
                .build();

        jPars.SerializeXml(worker, path);
        var expected = new Worker(jPars.DeserializeXml(worker, jPars.lastPath));

        Assertions.assertEquals("Alex", expected.getfName());
        Assertions.assertEquals("some street", expected.getAddress());
    }
}
