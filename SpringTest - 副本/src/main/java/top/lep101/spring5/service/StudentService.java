package top.lep101.spring5.service;

import top.lep101.spring5.entity.Student;

import java.util.List;

public interface StudentService {

    int addStudent(Student student);
    List<Student> queryStudents();

}
