package top.lep101.spring5.dao;

import top.lep101.spring5.entity.Student;

import java.util.List;

public interface StudentDao {
//    方法名不能重载
    int insertStudent(Student student);

    List<Student> selectStudents();
}
