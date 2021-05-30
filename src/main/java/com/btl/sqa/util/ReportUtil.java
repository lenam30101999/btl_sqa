package com.btl.sqa.util;

import com.btl.sqa.dto.StudentDTO;

public class ReportUtil {
    public static StudentDTO gpatoReport(StudentDTO studentReportDTO){
        studentReportDTO.setGpa10(studentReportDTO.getGpa()*2.5);
        if(studentReportDTO.getGpa()>=0 && studentReportDTO.getGpa()<1.0){
            studentReportDTO.setGpaApha("F");
        }
        else if(studentReportDTO.getGpa()>=1.0 && studentReportDTO.getGpa()<1.5){
            studentReportDTO.setGpaApha("D");
        }
        else if(studentReportDTO.getGpa()>=1.5 && studentReportDTO.getGpa()<2.0){
            studentReportDTO.setGpaApha("D+");
        }
        else if(studentReportDTO.getGpa()>=2.0 && studentReportDTO.getGpa()<2.5){
            studentReportDTO.setGpaApha("C");
        }
        else if(studentReportDTO.getGpa()>=2.5 && studentReportDTO.getGpa()<3.0){
            studentReportDTO.setGpaApha("C+");
        }
        else if(studentReportDTO.getGpa()>=3.0 && studentReportDTO.getGpa()<3.5){
            studentReportDTO.setGpaApha("B");
        }
        else if(studentReportDTO.getGpa()>=3.5 && studentReportDTO.getGpa()<3.7){
            studentReportDTO.setGpaApha("B+");
        }
        else if(studentReportDTO.getGpa()>=3.7 && studentReportDTO.getGpa()<4.0){
            studentReportDTO.setGpaApha("A");
        }
        else {
            studentReportDTO.setGpaApha("A+");
        }
        return  studentReportDTO;
    }

}
