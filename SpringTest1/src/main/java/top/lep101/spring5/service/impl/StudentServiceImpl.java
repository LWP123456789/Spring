package top.lep101.spring5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lep101.spring5.dao.StudentDao;
import top.lep101.spring5.entity.Student;
import top.lep101.spring5.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    /*
    * dao是引用类型.  StudentDao类型的对象是在spring的配置文件中创建的对象
    *
    * 引用类型自动注入 @Autowired @Resource
    * */
    @Resource
    private StudentDao dao;

    @Override
    public int addStudent(Student student) {
        int rows = dao.insertStudent(student);
        return rows;
    }

    @Override
    public List<Student> queryStudents() {

        List<Student> list = dao.selectStudents();

        return list;
    }
}
