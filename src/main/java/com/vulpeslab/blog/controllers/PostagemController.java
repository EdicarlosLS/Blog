package com.vulpeslab.blog.controllers;

import com.vulpeslab.blog.models.Postagem;
import com.vulpeslab.blog.repositories.PostagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	@RequestMapping(value = "/postagens", method = RequestMethod.GET)
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Postagem> postagens = repository.findAll();
		mv.addObject("postagens", postagens);
		return mv;
	}

	@RequestMapping(value = "/postagens/nova", method = RequestMethod.GET)
	public String nova() {
		return "form_postagem";
	}

	@RequestMapping(value = "/postagens", method = RequestMethod.POST)
        public String salvar(Postagem postagem) {
		repository.save(postagem);         
		return "redirect:/postagens/nova";
	}

	@RequestMapping(value = "/postagens/{id}")
	public ModelAndView mostrar(@PathVariable long id){
		ModelAndView mv = new ModelAndView("postagem");
		Postagem p = repository.findById(id);
		mv.addObject("postagem", p);
		mv.addObject("id", id);
		return mv;
	}
}

