package ru.itis.webflux.entries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OpenWeatherRecord {
    private String name;
    private String weatherDescription;
    private Double temperature;
    private Long pressure;
    private Long humidity;
    private Long visibility;
    private Double windSpeed;

    @JsonProperty("weather")
    private void unpackWeather(List<Weather> weathers) {
        this.weatherDescription = weathers.get(0).getDescription();
    }

    @JsonProperty("main")
    private void unpackMain(Map<String, String> mainFields) {
        this.temperature = Double.parseDouble(mainFields.get("temp"));
        this.pressure = Long.parseLong(mainFields.get("pressure"));
        this.humidity = Long.parseLong(mainFields.get("humidity"));
    }

    @JsonProperty("wind")
    private void unpackWind(Map<String, String> windFields) {
        this.windSpeed = Double.parseDouble(windFields.get("speed"));
    }

    @Data
    private static class Weather {
        private String description;
    }

}
