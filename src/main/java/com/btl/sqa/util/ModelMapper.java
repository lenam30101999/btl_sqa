package com.btl.sqa.util;

import com.btl.sqa.dto.PointDTO;
import com.btl.sqa.dto.SemesterDTO;
import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.Point;
import com.btl.sqa.model.Semester;
import com.btl.sqa.model.User;
import jdk.jfr.MemoryAddress;
import org.mapstruct.*;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ModelMapper {

  @Mappings({
      @Mapping(target = "points", source = "points")
  })
  SemesterDTO convertToSemesterDTO(Semester semester);

  @Mappings({
      @Mapping(target = "subjectName", source = "subject.name")
  })
  PointDTO convertToPointDTO(Point point);

  @AfterMapping
  static void calculatePoint(@MappingTarget PointDTO result,
                             Point point) {
    result.setPoint10(calculatePoint10(point));
    if(result.getPoint10()>=9){
      result.setPoint4(4.0F);
      result.setPointString("A+");
    }
    else if(result.getPoint10()>=8.5 && result.getPoint10()<=8.9){
      result.setPoint4(3.7F);
      result.setPointString("A+");
    }
    else if(result.getPoint10()>=8.0 && result.getPoint10()<=8.4){
      result.setPoint4(3.5F);
      result.setPointString("B+");
    }
    else if(result.getPoint10()>=7.0 && result.getPoint10()<=7.9){
      result.setPoint4(3.0F);
      result.setPointString("B");
    }
    else if(result.getPoint10()>=6.5 && result.getPoint10()<=6.9){
      result.setPoint4(2.5F);
      result.setPointString("C+");
    }
    else if(result.getPoint10()>=5.5 && result.getPoint10()<=6.4){
      result.setPoint4(2.0F);
      result.setPointString("C");
    }
    else if(result.getPoint10()>=5.0 && result.getPoint10()<=5.4){
      result.setPoint4(1.5F);
      result.setPointString("D+");
    }
    else if(result.getPoint10()>=4.0 && result.getPoint10()<=4.9){
      result.setPoint4(1.0F);
      result.setPointString("D");
    }
    else if(result.getPoint10()<4.0){
      result.setPoint4(0.0F);
      result.setPointString("F");
    }
  }

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

  static double calculatePoint10(Point point){
    float abs = point.getDiemCC() * point.getSubject().getPercentCC() +
        point.getDiemKT() * point.getSubject().getPercentKT() +
        point.getDiemBTL() * point.getSubject().getPercentKT()+
        point.getDiemCuoiKy() * point.getSubject().getPercentCuoiKy();
    return Math.round(abs * 100.0) / 100.0;
  }
}
