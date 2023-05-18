package com.kul.logistics.mapper;

import com.kul.logistics.domain.Location;
import com.kul.logistics.domain.LocationLink;
import com.kul.logistics.model.LocationLinkModel;
import com.kul.logistics.model.response.LocationResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@Mapper
public interface LocationResponseMapper {

  LocationResponseMapper INSTANCE = Mappers.getMapper(LocationResponseMapper.class);

  List<LocationResponse> mapFromLocationList(List<Location> locationList);

  @Mapping(target = "adjacentLocations", source = "locationLinkList")
  LocationResponse mapFromLocation(Location location);

  @Mapping(target = "name", source = "adjacentLocation")
  LocationLinkModel mapFromLocationLink(LocationLink locationLink);

}
