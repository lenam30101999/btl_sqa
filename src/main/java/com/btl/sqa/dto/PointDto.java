package com.btl.sqa.dto;

import com.btl.sqa.model.Semester;
import com.btl.sqa.model.Student;
import com.btl.sqa.model.Subject;
import com.sun.source.doctree.SerialDataTree;

import java.io.Serializable;

public class PointDto implements Serializable {
    private Integer id;
    private Double point4;
    private Double point10;
    private String pointApha;
    private Subject subject;
    private Student student;

    public Subject getSubject() {
        return subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPoint4() {
        return point4;
    }

    public void setPoint4(Double point4) {
        this.point4 = point4;
    }

    public Double getPoint10() {
        return point10;
    }

    public void setPoint10(Double point10) {
        this.point10 = point10;
    }

    public String getPointApha() {
        return pointApha;
    }

    public void setPointApha(String pointApha) {
        this.pointApha = pointApha;
    }
}
