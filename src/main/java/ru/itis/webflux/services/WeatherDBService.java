package ru.itis.webflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.itis.webflux.entries.WeatherEntry;
import ru.itis.webflux.repositories.WeatherRepository;

import java.util.stream.Collectors;

@Service
public class WeatherDBService implements WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    @Override
    public Flux<WeatherEntry> getWeather() {
        return Flux.fromIterable(weatherRepository.findAll()
                .stream()
                .map(weather -> WeatherEntry.builder()
                        .city(weather.getCity())
                        .weather(weather.getWeather())
                        .temperature(weather.getTemperature())
                        .pressure(weather.getPressure())
                        .humidity(weather.getHumidity())
                        .visibility(weather.getVisibility())
                        .windSpeed(weather.getWindSpeed())
                        .from("DB")
                        .build()).collect(Collectors.toList()));
    }
}
