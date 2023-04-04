package com.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class LandingPage {
    
	@GetMapping("/pu")
	
	public String pu(){
		return"landing/index";
	}
	
    @GetMapping("/about")
	
	public String about(){
		return"landing/about";
	
	}
    
    @GetMapping("/services")
	
	public String services(){
		return"landing/service";
	}
    
    @GetMapping("/project")
	
	public String project(){
		return"landing/project";
	}
    
    @GetMapping("/contact")
	
	public String contact(){
		return"landing/contact";
	}
}

   