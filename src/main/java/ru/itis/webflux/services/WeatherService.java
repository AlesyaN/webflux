package ru.itis.webflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.itis.webflux.clients.OpenWeatherWebClient;
import ru.itis.webflux.entries.WeatherEntry;

@Service
public class WeatherService {

    @Autowired
    private OpenWeatherWebClient client;

    public Flux<WeatherEntry> getWeatherAroundKazan() {
        return client.getWeather().subscribeOn(Schedulers.elastic()); //логика выполняется не в основном потоке
    }

}
