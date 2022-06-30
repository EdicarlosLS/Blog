package com.vulpeslab.blog.controllers;

import com.vulpeslab.blog.models.Usuario;
import com.vulpeslab.blog.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioRepository repository;

	@RequestMapping(value = "/usuarios/registro", method = RequestMethod.GET)
	public String formRegistro() {
		return "registro";
	}

	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	public String salvar(Usuario usuario){
		repository.save(usuario);
		return "redirect:/postagens";
	}
}

