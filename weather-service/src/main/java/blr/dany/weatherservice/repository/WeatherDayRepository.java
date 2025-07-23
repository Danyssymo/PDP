package blr.dany.weatherservice.repository;

import blr.dany.weatherservice.entity.ForecastDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDayRepository extends JpaRepository<ForecastDay, Long> {
}
