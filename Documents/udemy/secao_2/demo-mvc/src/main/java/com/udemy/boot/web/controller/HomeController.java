package com.udemy.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	/**
	 * O getMapping("/") indica raiz do projeto localhost:8080/
	 * O controller vai direcionar para página home
	 * 
	 */
	@GetMapping("/")
	public String home() {
		
		return "/home";
	}
}
