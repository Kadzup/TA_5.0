package Tests;

import IO.StreamsCompare;
import Models.Client;
import Models.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestStreamsAndCompares {
        @Test
        void ClientCompareToYear(){
            Client c1 = new Client.Builder()
                    .setBDay(LocalDate.of(2000,10, 10))
                    .setEmail("dstefurak@email.net")
                    .setFName("1")
                    .setLName("Stefurak")
                    .setPaymentInfo("0000-0000-0000-0000")
                    .setPhone("+380 5088 340 11")
                    .build();
            Client c2 = new Client.Builder()
                    .setBDay(LocalDate.of(2017,10, 10))
                    .setEmail("dstefurak@email.net")
                    .setFName("2")
                    .setLName("Stefurak")
                    .setPaymentInfo("0000-0000-0000-0000")
                    .setPhone("+380 5088 340 11")
                    .build();
            Client c3 = new Client.Builder()
                    .setBDay(LocalDate.of(1999,10, 10))
                    .setEmail("dstefurak@email.net")
                    .setFName("3")
                    .setLName("Stefurak")
                    .setPaymentInfo("0000-0000-0000-0000")
                    .setPhone("+380 5088 340 11")
                    .build();

            List<Client> clients = new ArrayList<Client>();
            clients.add(c1);
            clients.add(c2);
            clients.add(c3);

            StreamsCompare sc = new StreamsCompare();
            var actual = sc.getSortedByName(clients);

            Assertions.assertEquals(c3, actual.get(0));
        }
}
