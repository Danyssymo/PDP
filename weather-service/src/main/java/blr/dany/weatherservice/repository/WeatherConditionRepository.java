package blr.dany.weatherservice.repository;

import blr.dany.weatherservice.entity.WeatherCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherConditionRepository extends JpaRepository<WeatherCondition, Long> {
}
