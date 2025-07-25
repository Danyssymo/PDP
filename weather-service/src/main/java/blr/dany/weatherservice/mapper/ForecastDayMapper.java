package blr.dany.weatherservice.mapper;

import blr.dany.weatherservice.dto.response.ForecastDayResponse;
import blr.dany.weatherservice.dto.response.ForecastResponse;
import blr.dany.weatherservice.entity.ForecastDay;
import org.mapstruct.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", uses = HourlyForecastMapper.class)
public interface ForecastDayMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "location", ignore = true) // Будем устанавливать отдельно
    @Mapping(target = "hourlyForecasts", source = "hour")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "sunrise", source = "astro.sunrise", qualifiedByName = "mapAstroTime")
    @Mapping(target = "sunset", source = "astro.sunset", qualifiedByName = "mapAstroTime")
    @Mapping(target = "moonrise", source = "astro.moonrise", qualifiedByName = "mapAstroTime")
    @Mapping(target = "moonset", source = "astro.moonset", qualifiedByName = "mapAstroTime")
    @Mapping(target = "moonPhase", source = "astro.moonPhase")
    @Mapping(target = "moonIllumination", source = "astro.moonIllumination")
    @Mapping(target = "maxTempC", source = "day.maxTempC")
    @Mapping(target = "minTempC", source = "day.minTempC")
    @Mapping(target = "avgTempC", source = "day.avgTempC")
    @Mapping(target = "maxWindKph", source = "day.maxWindKph")
    @Mapping(target = "totalPrecipMm", source = "day.totalPrecipMm")
    @Mapping(target = "avgVisKm", source = "day.avgVisKm")
    @Mapping(target = "avgHumidity", source = "day.avgHumidity")
    @Mapping(target = "dailyWillItRain", source = "day.dailyWillItRain")
    @Mapping(target = "dailyChanceOfRain", source = "day.dailyChanceOfRain")
    @Mapping(target = "uvIndex", source = "day.uv")
    @Mapping(target = "aqiUsEpaIndex", source = "day.airQuality.usEpaIndex")
    @Mapping(target = "aqiGbDefraIndex", source = "day.airQuality.gbDefraIndex")
    ForecastDay toEntity(ForecastDayResponse dto);


    @Mapping(target = "hour", source = "hourlyForecasts")
    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "astro.sunrise", source = "sunrise", qualifiedByName = "mapAstroTimeReverse")
    @Mapping(target = "astro.sunset", source = "sunset", qualifiedByName = "mapAstroTimeReverse")
    @Mapping(target = "astro.moonrise", source = "moonrise", qualifiedByName = "mapAstroTimeReverse")
    @Mapping(target = "astro.moonset", source = "moonset", qualifiedByName = "mapAstroTimeReverse")
    @Mapping(target = "astro.moonPhase", source = "moonPhase")
    @Mapping(target = "astro.moonIllumination", source = "moonIllumination")
    @Mapping(target = "day.maxTempC", source = "maxTempC")
    @Mapping(target = "day.minTempC", source = "minTempC")
    @Mapping(target = "day.avgTempC", source = "avgTempC")
    @Mapping(target = "day.maxWindKph", source = "maxWindKph")
    @Mapping(target = "day.totalPrecipMm", source = "totalPrecipMm")
    @Mapping(target = "day.avgVisKm", source = "avgVisKm")
    @Mapping(target = "day.avgHumidity", source = "avgHumidity")
    @Mapping(target = "day.dailyWillItRain", source = "dailyWillItRain")
    @Mapping(target = "day.dailyChanceOfRain", source = "dailyChanceOfRain")
    @Mapping(target = "day.uv", source = "uvIndex")
    @Mapping(target = "day.airQuality.usEpaIndex", source = "aqiUsEpaIndex")
    @Mapping(target = "day.airQuality.gbDefraIndex", source = "aqiGbDefraIndex")
    ForecastDayResponse toDto(ForecastDay entity);
    List<ForecastDayResponse> toDtos(List<ForecastDay> dtos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "location", ignore = true) // Не трогаем
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "hourlyForecasts", source = "hour")
    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "sunrise", source = "astro.sunrise", qualifiedByName = "mapAstroTime")
    @Mapping(target = "sunset", source = "astro.sunset", qualifiedByName = "mapAstroTime")
    @Mapping(target = "moonrise", source = "astro.moonrise", qualifiedByName = "mapAstroTime")
    @Mapping(target = "moonset", source = "astro.moonset", qualifiedByName = "mapAstroTime")
    @Mapping(target = "moonPhase", source = "astro.moonPhase")
    @Mapping(target = "moonIllumination", source = "astro.moonIllumination")
    @Mapping(target = "maxTempC", source = "day.maxTempC")
    @Mapping(target = "minTempC", source = "day.minTempC")
    @Mapping(target = "avgTempC", source = "day.avgTempC")
    @Mapping(target = "maxWindKph", source = "day.maxWindKph")
    @Mapping(target = "totalPrecipMm", source = "day.totalPrecipMm")
    @Mapping(target = "avgVisKm", source = "day.avgVisKm")
    @Mapping(target = "avgHumidity", source = "day.avgHumidity")
    @Mapping(target = "dailyWillItRain", source = "day.dailyWillItRain")
    @Mapping(target = "dailyChanceOfRain", source = "day.dailyChanceOfRain")
    @Mapping(target = "uvIndex", source = "day.uv")
    @Mapping(target = "aqiUsEpaIndex", source = "day.airQuality.usEpaIndex")
    @Mapping(target = "aqiGbDefraIndex", source = "day.airQuality.gbDefraIndex")
    void updateFromDto(ForecastDayResponse dto, @MappingTarget ForecastDay entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "location", ignore = true) // Будем устанавливать отдельно
    @Mapping(target = "hourlyForecasts", source = "hour")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "sunrise", source = "astro.sunrise", qualifiedByName = "mapAstroTime")
    @Mapping(target = "sunset", source = "astro.sunset", qualifiedByName = "mapAstroTime")
    @Mapping(target = "moonrise", source = "astro.moonrise", qualifiedByName = "mapAstroTime")
    @Mapping(target = "moonset", source = "astro.moonset", qualifiedByName = "mapAstroTime")
    @Mapping(target = "moonPhase", source = "astro.moonPhase")
    @Mapping(target = "moonIllumination", source = "astro.moonIllumination")
    @Mapping(target = "maxTempC", source = "day.maxTempC")
    @Mapping(target = "minTempC", source = "day.minTempC")
    @Mapping(target = "avgTempC", source = "day.avgTempC")
    @Mapping(target = "maxWindKph", source = "day.maxWindKph")
    @Mapping(target = "totalPrecipMm", source = "day.totalPrecipMm")
    @Mapping(target = "avgVisKm", source = "day.avgVisKm")
    @Mapping(target = "avgHumidity", source = "day.avgHumidity")
    @Mapping(target = "dailyWillItRain", source = "day.dailyWillItRain")
    @Mapping(target = "dailyChanceOfRain", source = "day.dailyChanceOfRain")
    @Mapping(target = "uvIndex", source = "day.uv")
    @Mapping(target = "aqiUsEpaIndex", source = "day.airQuality.usEpaIndex")
    @Mapping(target = "aqiGbDefraIndex", source = "day.airQuality.gbDefraIndex")
    void updateForecastDayFromDto(@MappingTarget ForecastDay target, ForecastDayResponse source);

    @Named("mapAstroTime")
    default LocalTime mapAstroTime(String time) {
        if (time == null) return null;
        // Формат времени в астрономических данных: "05:11 AM"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalTime.parse(time, formatter);
    }

    @Named("mapAstroTimeReverse")
    default String mapAstroTimeReverse(LocalTime time) {
        if (time == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return time.format(formatter);
    }

    @AfterMapping
    default void afterMapping(
            @MappingTarget ForecastDay entity,
            ForecastDayResponse dto) {
        // Дополнительные преобразования, если нужны
        if (dto.getDay() != null && dto.getDay().getCondition() != null) {
            // Можно установить condition, если он нужен в ForecastDay
        }
    }
}
