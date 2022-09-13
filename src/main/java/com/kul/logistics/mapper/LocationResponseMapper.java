package com.kul.logistics.mapper;

import java.util.List;

import com.kul.logistics.domain.Location;
import com.kul.logistics.model.response.LocationResponse;

import org.mapstruct.Mapper;
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

}
