package ru.itis.webflux.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.webflux.entities.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
