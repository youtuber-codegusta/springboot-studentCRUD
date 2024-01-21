package com.api.demon.services;

import java.util.List;

import com.api.demon.model.Student;

public interface IStudentService {
    Student addStudent(Student student);
    List<Student> getStudants();
    Student updateStudent(Student student, Long id);
    Student getStudentId(Long id);
    void deleteStudent(Long id);

}
