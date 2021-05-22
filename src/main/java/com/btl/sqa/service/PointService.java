package com.btl.sqa.service;

import com.btl.sqa.dto.PointDTO;
import com.btl.sqa.model.Point;
import com.btl.sqa.model.Student;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PointService extends BaseService{

  public List<PointDTO> getPointList(Student student){
    List<Point> pointList = pointRepository.findPointsByStudentId(student.getId());
    List<PointDTO> PointDTOs= new ArrayList<>();
    pointList.forEach(s->{
      PointDTO PointDTO = modelMapper.convertToPointDTO(s);
      PointDTO.setPoint10(calculatePoint10(s));
      if(PointDTO.getPoint10()>=9){
        PointDTO.setPoint4(4.0F);
        PointDTO.setPointString("A+");
      }
      else if(PointDTO.getPoint10()>=8.5 && PointDTO.getPoint10()<=8.9){
        PointDTO.setPoint4(3.7F);
        PointDTO.setPointString("A+");
      }
      else if(PointDTO.getPoint10()>=8.0 && PointDTO.getPoint10()<=8.4){
        PointDTO.setPoint4(3.5F);
        PointDTO.setPointString("B+");
      }
      else if(PointDTO.getPoint10()>=7.0 && PointDTO.getPoint10()<=7.9){
        PointDTO.setPoint4(3.0F);
        PointDTO.setPointString("B");
      }
      else if(PointDTO.getPoint10()>=6.5 && PointDTO.getPoint10()<=6.9){
        PointDTO.setPoint4(2.5F);
        PointDTO.setPointString("C+");
      }
      else if(PointDTO.getPoint10()>=5.5 && PointDTO.getPoint10()<=6.4){
        PointDTO.setPoint4(2.0F);
        PointDTO.setPointString("C");
      }
      else if(PointDTO.getPoint10()>=5.0 && PointDTO.getPoint10()<=5.4){
        PointDTO.setPoint4(1.5F);
        PointDTO.setPointString("D+");
      }
      else if(PointDTO.getPoint10()>=4.0 && PointDTO.getPoint10()<=4.9){
        PointDTO.setPoint4(1.0F);
        PointDTO.setPointString("D");
      }
      else if(PointDTO.getPoint10()<4.0){
        PointDTO.setPoint4(0.0F);
        PointDTO.setPointString("F");
      }
      PointDTOs.add(PointDTO);
    });
    return PointDTOs;
  }

  public double calculatePoint10(Point point){
    float abs = point.getDiemCC() * point.getSubject().getPercentCC() +
        point.getDiemKT() * point.getSubject().getPercentKT() +
        point.getDiemBTL() * point.getSubject().getPercentKT()+
        point.getDiemCuoiKy() * point.getSubject().getPercentCuoiKy();
    return Math.round(abs * 100.0) / 100.0;
  }
}

