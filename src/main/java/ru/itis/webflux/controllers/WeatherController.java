package ru.itis.webflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.webflux.entries.WeatherEntry;
import ru.itis.webflux.services.OpenWeatherApiService;
import ru.itis.webflux.services.WeatherDBService;
import ru.itis.webflux.services.WeatherService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private OpenWeatherApiService weatherApiService;

    @Autowired
    private WeatherDBService weatherDBService;

    @GetMapping(value = "/api", produces = MediaType.TEXT_EVENT_STREAM_VALUE) //стримим
    public Flux<WeatherEntry> getWeatherFromApi() {
        return weatherApiService.getWeather(); //Netti подписывается на Flux и ждет response, не блокируя при этом клиентские потоки
    }

    @GetMapping(value = "/db", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<WeatherEntry> getWeatherFromDB() {
        return weatherDBService.getWeather();
    }

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<WeatherEntry> getWeather() {
        List<WeatherService> services = asList(weatherApiService, weatherDBService);
        List<Flux<WeatherEntry>> fluxes = services.stream().map(WeatherService::getWeather).collect(Collectors.toList());
        return Flux.merge(fluxes);
    }
}
