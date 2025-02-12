package com.example.spring_boot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional  =
                studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){

        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exists"
            );
        }
        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student wth ID " + studentId + " does not exist"
        ));

        if(name != null && !name.isEmpty() && !name.equals(student.getName())){
            student.setName(name);
        }

        if(email != null && !email.isEmpty() && !email.equals(student.getEmail())){
            Optional<Student> studentWithEmail = studentRepository.findStudentByEmail(email);
            if(studentWithEmail.isPresent() && !studentWithEmail.get().getId().equals(studentId)){
                throw new IllegalStateException("Email is already in use");
            }

            student.setEmail(email);
        }
    }
}
