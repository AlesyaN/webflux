package ru.itis.webflux;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.webflux.entities.Weather;
import ru.itis.webflux.repositories.WeatherRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@SpringBootApplication
public class WebFluxApplication {

    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebFluxApplication.class, args);
//        WeatherRepository weatherRepository = context.getBean(WeatherRepository.class);
//        File file = new File("C:\\WS\\study\\java\\WebFlux\\src\\main\\resources\\static\\weather.txt");
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        ObjectMapper mapper = new ObjectMapper();
//        String line;
//        while (!(line = reader.readLine()).equals(null)) {
//            Weather weather = mapper.readValue(line, Weather.class);
//            weatherRepository.save(weather);
//        }
    }

}
