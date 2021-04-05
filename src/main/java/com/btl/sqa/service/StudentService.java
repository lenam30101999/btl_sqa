package com.btl.sqa.service;

import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.Class;
import com.btl.sqa.model.Student;
import com.btl.sqa.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Log4j2
@Service
public class StudentService extends BaseService{

  public Student addStudent(UserDTO studentDTO) {
    User user = saveUser(studentDTO);
    try {
      Student student = Student.builder()
          .identifyCard(studentDTO.getIdentifyCard())
          .facultyName(studentDTO.getFacultyName())
          .room(getClass(studentDTO.getClassName()))
          .user(user)
          .gpa(0)
          .points(null)
          .build();

      student = studentRepository.save(student);
      return student;
    }catch (Exception e) {
      log.debug(e);
    }
    return null;
  }

  public List<Student> getAllStudent(){
    return studentRepository.findAll();
  }

  private Class getClass(String className){
    return classRepository.findByName(className).orElseThrow(() -> new NotFoundException("Class not found!"));
  }
}
