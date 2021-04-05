package com.btl.sqa.util;

import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ModelMapper {

  @Mappings({
      @Mapping(target = "dob", source = "dob", qualifiedByName = "mapDate")
  })
  User convertStudentToUser(UserDTO userDTO);

  @Named("mapDate")
  default Date userStateToString(String date) throws ParseException {
    if (Objects.nonNull(date)){
      return ServiceUtil.formatDate(date);
    }else return null;
  }
}
