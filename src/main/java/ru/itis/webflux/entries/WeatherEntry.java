package ru.itis.webflux.entries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class WeatherEntry {
    private String city;
    private String weather;
    private Double temperature;
    private Long pressure;
    private Long humidity;
    private Long visibility;
    private Double windSpeed;
    private String from;
}
