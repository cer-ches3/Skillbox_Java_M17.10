package FormatJSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String json = Files.readString(Paths.get("data/person.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Person person = objectMapper.readValue(json, Person.class);
            System.out.println(person.getName());

            person.setChildren(List.of("Olga", "Petr"));
            Car car = new Car();
            car.setLicensePlate("P720PY69");
            person.setCar(car);

            String newJson = objectMapper.writeValueAsString(person);
            FileWriter fileWriter = new FileWriter("data/personModified.json");
            fileWriter.write(newJson);
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
