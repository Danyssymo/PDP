package blr.dany.weatherservice.repository;

import blr.dany.weatherservice.entity.ForecastDay;
import blr.dany.weatherservice.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherDayRepository extends JpaRepository<ForecastDay, Long> {
    Optional<ForecastDay> findByLocationAndDate(Location location, LocalDate date);

    @Query("SELECT f FROM ForecastDay f WHERE f.location = :location AND f.date >= :today ORDER BY f.date ASC")
    List<ForecastDay> findNextDays(@Param("location") Location location, @Param("today") LocalDate today);
}
