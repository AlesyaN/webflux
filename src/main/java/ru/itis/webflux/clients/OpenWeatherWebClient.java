package ru.itis.webflux.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.itis.webflux.entries.OpenWeatherResponse;
import ru.itis.webflux.entries.WeatherEntry;

@Component
public class OpenWeatherWebClient {

    private WebClient client;

    public OpenWeatherWebClient(@Value("${openweather.api.url}") String url) {
        client = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Flux<WeatherEntry> getWeather() {
        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(OpenWeatherResponse.class))
                .flatMapIterable(OpenWeatherResponse::getList)
                .map(record ->
                        WeatherEntry.builder()
                                .city(record.getName())
                                .weather(record.getWeatherDescription())
                                .temperature(record.getTemperature())
                                .pressure(record.getPressure())
                                .humidity(record.getHumidity())
                                .visibility(record.getVisibility())
                                .windSpeed(record.getWindSpeed())
                                .from("api")
                                .build());
    }

}
