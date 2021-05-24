package com.btl.sqa.service;

import com.btl.sqa.dto.PointInputDTO;
import com.btl.sqa.model.Point;
import com.btl.sqa.model.Student;
import com.btl.sqa.util.ServiceUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
@Log4j2
public class PointService extends BaseService{

  public PointInputDTO inputPoint(PointInputDTO pointDTO){
    try {
      if (checkNumber(pointDTO)){
        Student student = getStudent(pointDTO.getStudentId());
        if (Objects.nonNull(student)){
          Point point = Point.builder()
              .diemCC(pointDTO.getDiemCC())
              .diemBTL(pointDTO.getDiemBTL())
              .diemCuoiKy(pointDTO.getDiemCuoiKy())
              .diemKT(pointDTO.getDiemKT())
              .diemTH(pointDTO.getDiemTH())
              .student(student)
              .subject(getSubject(pointDTO.getSubjectId()))
              .manager(getManager(pointDTO.getManagerId()))
              .semester(getSemester(pointDTO.getSemesterId()))
              .build();

          pointRepository.save(point);
          return pointDTO;
        }
      }
    }catch (Exception e) {
      log.debug(e);
    }
    return null;
  }

  private boolean checkNumber(PointInputDTO pointInputDTO){
    return ServiceUtil.formatNumber(pointInputDTO.getDiemBTL()) != null &&
        ServiceUtil.formatNumber(pointInputDTO.getDiemCC()) != null &&
        ServiceUtil.formatNumber(pointInputDTO.getDiemCuoiKy()) != null &&
        ServiceUtil.formatNumber(pointInputDTO.getDiemKT()) != null &&
        ServiceUtil.formatNumber(pointInputDTO.getDiemTH()) != null;
  }
}

