package com.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.model.Student;
import com.cms.model.Teacher;
import com.cms.repository.StudentRepository;
import com.cms.repository.TeacherRepository;

@Component
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	// retrieve all student
		public List<Student> getAllStudent(){
			
			return this.studentRepository.findAll();
		}
		
	// retrieve teacher data
		public List<Student> getAllTeacher(){
			
			return this.studentRepository.findAll();
		}
}
