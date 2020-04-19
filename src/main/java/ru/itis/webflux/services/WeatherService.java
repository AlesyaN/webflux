package ru.itis.webflux.services;

import reactor.core.publisher.Flux;
import ru.itis.webflux.entries.WeatherEntry;

public interface WeatherService {
    public Flux<WeatherEntry> getWeather();
}
