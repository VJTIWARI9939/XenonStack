package com.cms.controller;

import java.io.IOException;
import java.lang.StackWalker.Option;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cms.model.Admin;
import com.cms.model.PdfFile;
import com.cms.model.Student;
import com.cms.model.Teacher;
import com.cms.repository.PdfRepository;
import com.cms.repository.StudentRepository;
import com.cms.repository.TeacherRepository;
import com.cms.service.PdfService;
import com.cms.service.StudentService;
import com.cms.service.TeacherService;


@Controller
public class TeacherController {

	@Autowired
	private PdfRepository pdfRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private PdfService pdfService;
	
	@GetMapping("/index")
	public String teacher(Principal principal, Model model, HttpSession session) {
		
//		String UserName = principal.getName();
//		System.out.println(UserName);
//
//		Teacher teacher = teacherRepository.getTeacherByUserName(UserName);
//		System.out.println(teacher);
//
//		// for send current admin logged in data in dashboard
//		model.addAttribute("teacher", teacher);
		
		// count appointment
		session.setAttribute("countteacher", teacherRepository.count());

		// count doctor
		session.setAttribute("countstudent", studentRepository.count());
		
		return "teacher/index";
	}
	
	@GetMapping("/viewfile")
	public String viewFile(Model model) {
		
		List<PdfFile> t = pdfService.getAllPdf();
		model.addAttribute("p", t);
		
		return "teacher/view-file";
	}
	
	// redirect to upload file
	@GetMapping("/uploadfile")
	public String uploadFile() {
		
		return "teacher/add-file";
	}
	
	
    @PostMapping("/uploadfile")
    public String uploadFile(@RequestParam("name") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
    	
    	PdfFile pdfFile = new PdfFile();
//    	pdfFile.setName(file.getOriginalFilename());
//        pdfFile.setContentType(file.getContentType());
//        pdfFile.setData(file.getBytes());
    	
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/uploadfile";
        }
        try {
            // Save the file
            byte[] bytes = file.getBytes();
            Path path = Paths.get("C:\\Users\\SD\\Desktop\\CourseManagementSystem\\CourseManagementSystem\\src\\main\\resources\\static\\pdf\\" + file.getOriginalFilename());
            Files.write(path, bytes);
            // Save the PDF file to the database using a repository or service
            pdfRepository.save(pdfFile);
            redirectAttributes.addFlashAttribute("message", "File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/viewfile";
    }
	
    // redirect to attendance page
    @GetMapping("/add-attendance")
    public String attendance(Model model) {
    	
    	List<Student>student = studentService.getAllStudent();
		model.addAttribute("students", student);
    	
    	return "teacher/add-attendance";
    }
    
    @PostMapping("/saveStudent")
	public String saveStd(@ModelAttribute("student") Student student) {
		
		 this.studentRepository.save(student);
		 return "redirect:/index";
	}
    
     
    @GetMapping("/login")
    public String teacherlogin() {
    	return "teacher/login";
    }
    
    
    // teacher login
    
    @PostMapping("/loginteacher")
	public String teacherLogin(Model model, Principal principal, HttpSession session) {
//		String UserName = principal.getName();
//		System.out.println(UserName);
//
//		
//		Optional<Teacher> teacher = teacherRepository.getTeacherByUserName(UserName);
//		System.out.println(teacher);
//
//		// for send current teacher logged in data in dashboard
//		model.addAttribute("teacher", teacher);
//		session.setAttribute("teacher", teacher);
    	
    	System.out.println(principal);

		// get all appointment data on doctor dashboard
		List<Teacher> t = teacherService.getAllTeacher();
		model.addAttribute("teacher", t);

		// get all doctor data on admin dashboard
		List<Student> std = studentService.getAllStudent();
		model.addAttribute("student", std);
		return "redirect:/index";
	}
}


