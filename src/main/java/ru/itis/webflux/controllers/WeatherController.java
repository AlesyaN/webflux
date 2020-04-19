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

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private OpenWeatherApiService weatherService;

    @Autowired
    private WeatherDBService weatherDBService;

    @GetMapping(value = "/api", produces = MediaType.TEXT_EVENT_STREAM_VALUE) //стримим
    public Flux<WeatherEntry> getWeatherFromApi() {
        return weatherService.getWeather(); //Netti подписывается на Flux и ждет response, не блокируя при этом клиентские потоки
    }

    @GetMapping(value = "/db", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<WeatherEntry> getWeatherFromDB() {
        return weatherDBService.getWeather();
    }
}
