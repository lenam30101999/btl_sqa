package com.btl.sqa.service;

import com.btl.sqa.dto.PointInputDTO;
import com.btl.sqa.model.Point;
import com.btl.sqa.model.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
@Log4j2
public class PointService extends BaseService{

  public void inputPoint(PointInputDTO pointDTO){
    try {
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
      }
    }catch (Exception e) {
      log.debug(e);
    }
  }
}

