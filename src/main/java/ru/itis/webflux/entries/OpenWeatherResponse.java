package ru.itis.webflux.entries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherResponse {
    @JsonProperty("list")
    private List<OpenWeatherRecord> list;
}
