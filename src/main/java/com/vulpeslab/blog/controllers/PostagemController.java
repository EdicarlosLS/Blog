package com.vulpeslab.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostagemController {

	@RequestMapping("/novapostagem")
	public String form() {
		return "form_postagem";
	}
}

