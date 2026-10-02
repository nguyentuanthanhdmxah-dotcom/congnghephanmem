package com.example.saleappv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	@GetMapping 
	public String hello(Model model) {
		model.addAttribute("message", "Xin chao");
		return "index";
	}
}
