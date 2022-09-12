package com.kul.logistics.mapper;

import com.kul.logistics.domain.User;
import com.kul.logistics.model.UserRegisterRequestModel;
import com.kul.logistics.model.UserUpdateRequestModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 11-Sep-2022
 */

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "id", ignore = true)
	User mapFromUserRegisterRequestModel(UserRegisterRequestModel userRegisterRequestModel);

	@Mapping(target = "email", ignore = true)
	User mapFromUserUpdateRequestModel(UserUpdateRequestModel requestModel);
}
