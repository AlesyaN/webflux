package ru.itis.webflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.webflux.entries.WeatherEntry;
import ru.itis.webflux.services.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/kazan", produces = MediaType.TEXT_EVENT_STREAM_VALUE) //стримим
    public Flux<WeatherEntry> getWeatherAroundKazan() {
        return weatherService.getWeatherAroundKazan(); //Netti подписывается на Flux и ждет response, не блокируя при этом клиентские потоки
    }
}
