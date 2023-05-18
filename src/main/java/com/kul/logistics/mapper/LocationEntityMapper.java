package com.kul.logistics.mapper;

import com.kul.logistics.domain.Location;
import com.kul.logistics.domain.LocationLink;
import com.kul.logistics.model.LocationLinkModel;
import com.kul.logistics.model.request.LocationCreateRequest;
import com.kul.logistics.model.request.LocationUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@Mapper
public interface LocationEntityMapper {

  LocationEntityMapper INSTANCE = Mappers.getMapper(LocationEntityMapper.class);

  @Mapping(target = "locationLinkList", ignore = true)
  @Mapping(target = "id", ignore = true)
  Location mapFromAddRequest(LocationCreateRequest request);

  @Mapping(target = "locationLinkList", source = "adjacentLocations")
  Location mapFromUpdateRequest(LocationUpdateRequest request);

  @Mapping(target = "adjacentLocation", source = "name")
  @Mapping(target = "id", ignore = true)
  LocationLink mapFromLocationLinkModel(LocationLinkModel locationLinkModel);
}
