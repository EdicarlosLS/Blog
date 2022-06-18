package com.vulpeslab.blog.controllers;

import com.vulpeslab.blog.models.Postagem;
import com.vulpeslab.blog.repositories.PostagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	@RequestMapping(value = "/novapostagem", method = RequestMethod.GET)
	public String form() {
		return "form_postagem";
	}

	@RequestMapping(value = "/novapostagem", method = RequestMethod.POST)
        public String form(Postagem postagem) {
		repository.save(postagem);
         
		return "form_postagem";
	}

	@RequestMapping("/postagens")
	public ModelAndView listaPostagens() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Postagem> postagens = repository.findAll();
		mv.addObject("postagens", postagens);
		return mv;
	}
}

