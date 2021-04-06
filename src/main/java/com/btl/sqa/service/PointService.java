package com.btl.sqa.service;

import com.btl.sqa.dto.PointDto;
import com.btl.sqa.model.Point;
import com.btl.sqa.model.Student;
import com.btl.sqa.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PointService extends BaseService{

    @Autowired
    PointRepository pointRepository;
    public List<PointDto> getPointList(Student student){
        List<Point> pointList = pointRepository.findAllByStudent(student);
        List<PointDto> pointDtos= new ArrayList<>();
        pointList.forEach(s->{
            PointDto pointDto = new PointDto();
            pointDto.setId(s.getId());
            pointDto.setPoint10(caculatePoint10(s));
            pointDto.setSubject(s.getSubject());
            pointDto.setStudent(s.getStudent());
            if(pointDto.getPoint10()>=9){
                pointDto.setPoint4(4.0);
                pointDto.setPointApha("A+");
            }
            else if(pointDto.getPoint10()>=8.5 && pointDto.getPoint10()<=8.9){
                pointDto.setPoint4(3.7);
                pointDto.setPointApha("A+");
            }
            else if(pointDto.getPoint10()>=8.0 && pointDto.getPoint10()<=8.4){
                pointDto.setPoint4(3.5);
                pointDto.setPointApha("B+");
            }
            else if(pointDto.getPoint10()>=7.0 && pointDto.getPoint10()<=7.9){
                pointDto.setPoint4(3.0);
                pointDto.setPointApha("B");
            }
            else if(pointDto.getPoint10()>=6.5 && pointDto.getPoint10()<=6.9){
                pointDto.setPoint4(2.5);
                pointDto.setPointApha("C+");
            }
            else if(pointDto.getPoint10()>=5.5 && pointDto.getPoint10()<=6.4){
                pointDto.setPoint4(2.0);
                pointDto.setPointApha("C");
            }
            else if(pointDto.getPoint10()>=5.0 && pointDto.getPoint10()<=5.4){
                pointDto.setPoint4(1.5);
                pointDto.setPointApha("D+");
            }
            else if(pointDto.getPoint10()>=4.0 && pointDto.getPoint10()<=4.9){
                pointDto.setPoint4(1.0);
                pointDto.setPointApha("D");
            }
            else if(pointDto.getPoint10()<4.0){
                pointDto.setPoint4(0.0);
                pointDto.setPointApha("F");
            }
            pointDtos.add(pointDto);
        });
        return pointDtos;
    }

    public Double caculatePoint10(Point point){
        float abs= point.getDiemCC()*point.getPercentCC()/100+point.getDiemKT()*point.getPercentKT()/100+point.getDiemBTL()*point.getPercentBTL()/100+
                point.getDiemCuoiKy()*point.getPercentCuoiKy()/100;
        return Double.valueOf(Math.round(abs*100)/100);
    }


}

