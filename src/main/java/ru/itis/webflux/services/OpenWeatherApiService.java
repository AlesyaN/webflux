package ru.itis.webflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.itis.webflux.clients.OpenWeatherWebClient;
import ru.itis.webflux.entries.WeatherEntry;

@Service
public class OpenWeatherApiService implements WeatherService {

    @Autowired
    private OpenWeatherWebClient client;

    @Override
    public Flux<WeatherEntry> getWeather() {
        return client.getWeather().subscribeOn(Schedulers.elastic()); //логика выполняется не в основном потоке
    }
}
