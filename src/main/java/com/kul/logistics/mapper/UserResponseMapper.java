package com.kul.logistics.mapper;

import com.kul.logistics.domain.User;
import com.kul.logistics.model.UserRegisterResponseModel;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 11-Sep-2022
 */

@Mapper
public interface UserResponseMapper {

	UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

	UserRegisterResponseModel mapFromUserToRegisterNodel(User user);

}
