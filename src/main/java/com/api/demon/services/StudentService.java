package com.api.demon.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.demon.exception.StudentNotFoundException;
import com.api.demon.exception.studentAlreadyExistsException;
import com.api.demon.model.Student;
import com.api.demon.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExists(student.getEmail())){
            throw new studentAlreadyExistsException(student.getEmail() + "Already exists");
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudants() {
    return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartament(student.getDepartament());
            return studentRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    @Override
    public Student getStudentId(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("student não encontrado"));
    }
    
    
    private boolean studentAlreadyExists(String email) {
        // TODO Auto-generated method stub
        return studentRepository.findByEmail(email).isPresent();
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Student nao encontrado");
        } 
        studentRepository.deleteById(id);
    }
    
} 
