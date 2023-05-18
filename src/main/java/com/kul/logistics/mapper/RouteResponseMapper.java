package com.kul.logistics.mapper;

import com.kul.logistics.domain.Route;
import com.kul.logistics.model.response.RouteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 18-Sep-2022
 */

@Mapper
public interface RouteResponseMapper {

  RouteResponseMapper INSTANCE = Mappers.getMapper(RouteResponseMapper.class);

  RouteResponse mapFromRoute(Route route);

}
