package blr.dany.weatherservice.repository;

import blr.dany.weatherservice.entity.ForecastDay;
import blr.dany.weatherservice.entity.HourlyForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface HourlyForecastRepository extends JpaRepository<HourlyForecast, Long> {

    Optional<HourlyForecast> findByForecastDayAndTime(ForecastDay forecastDay, LocalDateTime hour);

}
