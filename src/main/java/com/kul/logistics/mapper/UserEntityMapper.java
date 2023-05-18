package com.kul.logistics.mapper;

import com.kul.logistics.domain.User;
import com.kul.logistics.model.request.UserRegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 11-Sep-2022
 */

@Mapper
public interface UserEntityMapper {

  UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

  @Mapping(target = "id", ignore = true)
  User mapFromUserRegisterRequestModel(UserRegisterRequest userRegisterRequestModel);

}
