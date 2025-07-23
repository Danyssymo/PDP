package blr.dany.weatherservice.repository;

import blr.dany.weatherservice.entity.HourlyForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourlyForecastRepository extends JpaRepository<HourlyForecast, Long> {
}
