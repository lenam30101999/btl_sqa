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
      PointDTO PointDTO = new PointDTO();
      PointDTO.setId(s.getId());
      PointDTO.setPoint10(caculatePoint10(s));
      PointDTO.setSubject(s.getSubject());
      PointDTO.setStudent(s.getStudent());
      if(PointDTO.getPoint10()>=9){
        PointDTO.setPoint4(4.0F);
        PointDTO.setPointApla("A+");
      }
      else if(PointDTO.getPoint10()>=8.5 && PointDTO.getPoint10()<=8.9){
        PointDTO.setPoint4(3.7F);
        PointDTO.setPointApla("A+");
      }
      else if(PointDTO.getPoint10()>=8.0 && PointDTO.getPoint10()<=8.4){
        PointDTO.setPoint4(3.5F);
        PointDTO.setPointApla("B+");
      }
      else if(PointDTO.getPoint10()>=7.0 && PointDTO.getPoint10()<=7.9){
        PointDTO.setPoint4(3.0F);
        PointDTO.setPointApla("B");
      }
      else if(PointDTO.getPoint10()>=6.5 && PointDTO.getPoint10()<=6.9){
        PointDTO.setPoint4(2.5F);
        PointDTO.setPointApla("C+");
      }
      else if(PointDTO.getPoint10()>=5.5 && PointDTO.getPoint10()<=6.4){
        PointDTO.setPoint4(2.0F);
        PointDTO.setPointApla("C");
      }
      else if(PointDTO.getPoint10()>=5.0 && PointDTO.getPoint10()<=5.4){
        PointDTO.setPoint4(1.5F);
        PointDTO.setPointApla("D+");
      }
      else if(PointDTO.getPoint10()>=4.0 && PointDTO.getPoint10()<=4.9){
        PointDTO.setPoint4(1.0F);
        PointDTO.setPointApla("D");
      }
      else if(PointDTO.getPoint10()<4.0){
        PointDTO.setPoint4(0.0F);
        PointDTO.setPointApla("F");
      }
      PointDTOs.add(PointDTO);
    });
    return PointDTOs;
  }

  public float caculatePoint10(Point point){
    float abs= point.getDiemCC()*point.getSubject().getPercentCC()+point.getDiemKT()*point.getSubject().getPercentKT()+point.getDiemBTL()*point.getSubject().getPercentKT()+
        point.getDiemCuoiKy()*point.getSubject().getPercentCuoiKy();
    return Math.round(abs*100)/100;
  }
}

