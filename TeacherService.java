package com.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.model.PdfFile;
import com.cms.model.Teacher;
import com.cms.repository.PdfRepository;
import com.cms.repository.TeacherRepository;

@Component
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private PdfRepository pdfRepository;
	
	// retieve all teacher
		public List<Teacher> getAllTeacher(){
			
			return this.teacherRepository.findAll();
		}
		
}
