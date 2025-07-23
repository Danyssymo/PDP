package blr.dany.weatherservice.mapper;

import blr.dany.weatherservice.dto.response.LocationResponse;
import blr.dany.weatherservice.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "forecastDays", ignore = true)
    @Mapping(target = "latitude", source = "lat")
    @Mapping(target = "longitude", source = "lon")
    @Mapping(target = "timezone", source = "tzId")
    Location toEntity(LocationResponse locationDto);
}
