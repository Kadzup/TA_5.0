package IO;

import Models.Client;

import java.util.List;
import java.util.stream.Collectors;

public class StreamsCompare {

    public StreamsCompare(){}

    public List<Client> getSortedByName(List<Client> clients){
        return  clients.stream()
                .sorted(Client::compareTo)
                .collect(Collectors.toList());
    }
}
