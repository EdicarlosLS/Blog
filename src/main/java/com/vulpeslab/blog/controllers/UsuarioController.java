package com.vulpeslab.blog.controllers;

import javax.servlet.http.HttpSession;

import com.vulpeslab.blog.models.Usuario;
import com.vulpeslab.blog.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

	@RequestMapping(value = "/usuarios/login", method = RequestMethod.GET)
	public String formLogin() {
		return "login";
	}

	@RequestMapping(value = "/usuarios/login", method = RequestMethod.POST)
	public String salvar(@Param("email") String email, @Param("senha") String senha, HttpSession session){
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null) {
			return "redirect:/postagens/1";
		}

		if (!usuario.getSenha().equals(senha)) {
			return "redirect:/postagens/4";
		}

		session.setAttribute("usuario", usuario);
		return "redirect:/postagens";
	}

	@RequestMapping(value = "/usuarios/logout", method = RequestMethod.GET)
	public String logout(HttpSession session)  {
		session.removeAttribute("usuario");
		return "redirect:/postagens";
	}
}

