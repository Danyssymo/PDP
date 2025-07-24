package blr.dany.weatherservice.mapper;

import blr.dany.weatherservice.dto.response.HourResponse;
import blr.dany.weatherservice.entity.HourlyForecast;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HourlyForecastMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "forecastDay", ignore = true) // Установим позже
    @Mapping(target = "time", source = "time", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "isDay", expression = "java(dto.getIsDay() == 1)")
    @Mapping(target = "windKph", source = "windKph")
    @Mapping(target = "windDegree", source = "windDegree")
    @Mapping(target = "windDir", source = "windDir")
    @Mapping(target = "pressureMb", source = "pressureMb")
    @Mapping(target = "precipMm", source = "precipMm")
    @Mapping(target = "feelsLikeC", source = "feelsLikeC")
    @Mapping(target = "willItRain", source = "willItRain")
    @Mapping(target = "chanceOfRain", source = "chanceOfRain")
    @Mapping(target = "visibilityKm", source = "visKm")
    @Mapping(target = "aqiCo", source = "airQuality.co")
    @Mapping(target = "aqiNo2", source = "airQuality.no2")
    @Mapping(target = "aqiO3", source = "airQuality.o3")
    @Mapping(target = "aqiSo2", source = "airQuality.so2")
    @Mapping(target = "aqiPm25", source = "airQuality.pm25")
    @Mapping(target = "aqiPm10", source = "airQuality.pm10")
    HourlyForecast toEntity(HourResponse dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "forecastDay", ignore = true) // не меняем ссылку на родителя
    @Mapping(target = "time", source = "time", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "isDay", expression = "java(dto.getIsDay() == 1)")
    @Mapping(target = "windKph", source = "windKph")
    @Mapping(target = "windDegree", source = "windDegree")
    @Mapping(target = "windDir", source = "windDir")
    @Mapping(target = "pressureMb", source = "pressureMb")
    @Mapping(target = "precipMm", source = "precipMm")
    @Mapping(target = "feelsLikeC", source = "feelsLikeC")
    @Mapping(target = "willItRain", source = "willItRain")
    @Mapping(target = "chanceOfRain", source = "chanceOfRain")
    @Mapping(target = "visibilityKm", source = "visKm")
    @Mapping(target = "aqiCo", source = "airQuality.co")
    @Mapping(target = "aqiNo2", source = "airQuality.no2")
    @Mapping(target = "aqiO3", source = "airQuality.o3")
    @Mapping(target = "aqiSo2", source = "airQuality.so2")
    @Mapping(target = "aqiPm25", source = "airQuality.pm25")
    @Mapping(target = "aqiPm10", source = "airQuality.pm10")
    void updateFromDto(HourResponse dto, @MappingTarget HourlyForecast entity);
}
