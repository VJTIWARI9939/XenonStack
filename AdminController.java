package com.cms.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cms.model.Admin;
import com.cms.model.Student;
import com.cms.model.Teacher;
import com.cms.repository.AdminRepository;
import com.cms.repository.StudentRepository;
import com.cms.repository.TeacherRepository;
import com.cms.service.StudentEmailService;
import com.cms.service.StudentService;
import com.cms.service.TeacherEmailService;
import com.cms.service.TeacherService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private TeacherEmailService teacherEmailService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentEmailService studentEmailService;
	
	// endpoint for admin dashboard
	@GetMapping("/dashboard")
	public String adminDashboard(HttpSession session, Principal principal, Model model) {
		
		String UserName = principal.getName();
		System.out.println(UserName);

		Admin admin = adminRepository.getAdminByUserName(UserName);			
		System.out.println(admin);

		// for send current user logged in data in dashboard
		model.addAttribute("admin", admin);
		
		// count appointment
				session.setAttribute("countteacher", teacherRepository.count());

				// count doctor
				session.setAttribute("countstudent", studentRepository.count());
				
				List<Student>student = studentService.getAllStudent();
				model.addAttribute("student", student);
				
				List<Teacher> t = teacherService.getAllTeacher();
				model.addAttribute("teacher", t);

		return "admin/index";
	}					
	
	
	// view teacher
	@GetMapping("/teacher")
	public String viewTeacher(Model model) {
		
		List<Teacher>teacher = teacherService.getAllTeacher();
		model.addAttribute("teachers", teacher);
			
		return "admin/view-teacher";
	}
	
	// redirect to add-teacher
	@GetMapping("/add-teacher")
	public String addTeacher(){
		
		return"admin/add-teacher";
	}
	
	// save teacher
	
	@PostMapping("/saveTeacher")
	public String saveTeacher(@ModelAttribute("teacher") Teacher teacher, @RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("userid") String username, @RequestParam("password") String password, HttpSession session) throws AddressException, MessagingException, IOException {

		System.out.println("Email: " + email);

		String subject = "";
		String message = "<h3>Dear: " + name + "\n Your username is "+ username +"\n and password is "+ password + "</h3>";
		String to = email;

		boolean flag = this.teacherEmailService.sendmail(subject, message, to);

		if (flag) {
			session.setAttribute("email", email);
		}
		
		this.teacherRepository.save(teacher);
		
		 return "redirect:/admin/teacher";
		
	}
	
	
	// delete teacher by id
		@GetMapping("/deleteTeacher/{id}")
		public String deleteBook(@PathVariable("id") Integer id) {
			
			Optional<Teacher> teacherOptional = this.teacherRepository.findById(id);
			Teacher teacher = teacherOptional.get();
			this.teacherRepository.delete(teacher);

			return "redirect:/admin/teacher";
		}
		
		
		// redirect to edit page
		@GetMapping("/updateTeacher/{id}")
			public String editTeacher(@PathVariable("id") Integer id, Model model) {
				
				Teacher teacher1 = this.teacherRepository.findById(id).get();
				model.addAttribute("teacher", teacher1);
				
				List<Teacher>teacher2 = this.teacherService.getAllTeacher();
				model.addAttribute("teacher", teacher2);
				
				return "admin/update-teacher";
			}
		
		
		// update teacher by id
		@PostMapping("/update-teacher/{id}")
		public String updateTeacher(@PathVariable("id") Integer id,Teacher teacher, Model model) {
			
				teacher.setId(id);
				teacherRepository.save(teacher);
				 return "redirect:/admin/teacher";
			}
	
	
		
		
		//------------------------------
		
		@GetMapping("/add-student")
		public String addStudent() {
			
			return "admin/add-student";
		}
		
		@PostMapping("/saveStudent")
		public String saveStudent(@ModelAttribute("student") Student student, @RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("password") String password, HttpSession session) throws AddressException, MessagingException, IOException {

			System.out.println("Email: " + email);

			String subject = "";
			String message = "<h3>Dear: " + name + "\n Your username is "+ email +"\n and password is "+ password + "</h3>";
			String to = email;

			boolean flag = this.studentEmailService.sendmail(subject, message, to);

			if (flag) {
				session.setAttribute("email", email);
			}
			
			this.studentRepository.save(student);
			 return "redirect:/admin/viewStudent";
		}
		
		
		@GetMapping("/viewStudent")
		public String viewStudent(Model model) {
			
			List<Student>student = studentService.getAllStudent();
			model.addAttribute("students", student);
				
			return "admin/view-student";
		}
		
		
		// delete teacher by id
		@GetMapping("/deleteStudent/{id}")
		public String deleteStudent(@PathVariable("id") Integer id) {
			
			Optional<Student> studentOptional = this.studentRepository.findById(id);
			Student student = studentOptional.get();
			this.studentRepository.delete(student);

			return "redirect:/admin/viewStudent";
		}
		
		
		// redirect to edit page
		@GetMapping("/updateStudent/{id}")
			public String editStudent(@PathVariable("id") Integer id, Model model) {
				
				Student student1 = this.studentRepository.findById(id).get();
				model.addAttribute("student", student1);
				
				List<Student>student2 = this.studentService.getAllStudent();
				model.addAttribute("student", student2);
				
				return "admin/update-teacher";
			}
		
		
		// update teacher by id
//		@PostMapping("/update-teacher/{id}")
//		public String updateTeacher(@PathVariable("id") Integer id,Teacher teacher, Model model) {
//			
//				teacher.setId(id);
//				teacherRepository.save(teacher);
//				 return "redirect:/admin/teacher";
//			}
		
		
		
		
		// ----------------------Admin Login------------------------

		// redirect to admin login

		@GetMapping("/login")
		public String adminLogin() {

			return "admin/login";
		}
		
		
		@PostMapping("/loginadmin")
		public String adminLogin(Model model, Principal principal, HttpSession session) {
			String UserName = principal.getName();
			System.out.println(UserName);

			Admin admin = adminRepository.getAdminByUserName(UserName);
			System.out.println(admin);

			// for send current admin logged in data in dashboard
			model.addAttribute("admins", admin);
			session.setAttribute("admin", admin);

			// get all appointment data on doctor dashboard
			List<Teacher> t = teacherService.getAllTeacher();
			model.addAttribute("teacher", t);

			// get all doctor data on admin dashboard
			List<Student> std = studentService.getAllStudent();
			model.addAttribute("student", std);
			return "redirect:/admin/dashboard";
		}
		
		// handler for admin profile
		@GetMapping("/profile")
		public String profile(Model model, Principal principal) {

			String UserName = principal.getName();
			System.out.println(UserName);

			Admin admin = adminRepository.getAdminByUserName(UserName);			
			System.out.println(admin);

			// for send current user logged in data in dashboard
			model.addAttribute("admin", admin);

			return "admin/profile";
		}
		
		// logout
		@GetMapping("/logout")
		public String logOut() {
			
			return "redirect:/admin/login";
		}
	
}
