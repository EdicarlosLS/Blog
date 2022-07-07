package com.vulpeslab.blog.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.vulpeslab.blog.models.Comentario;
import com.vulpeslab.blog.models.Postagem;
import com.vulpeslab.blog.models.Usuario;
import com.vulpeslab.blog.repositories.ComentarioRepository;
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

	@Autowired
	private ComentarioRepository comentarioRepo;
	
	@RequestMapping(value = "/postagens", method = RequestMethod.GET)
	public ModelAndView lista(HttpSession session) {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Postagem> postagens = repository.findAll();
		mv.addObject("postagens", postagens);
		mv.addObject("usuario", session.getAttribute("usuario"));
		return mv;
	}

	@RequestMapping(value = "/postagens/nova", method = RequestMethod.GET)
	public String nova(HttpSession session) {
		if(session.getAttribute("usuario") == null){
			return "redirect:/postagens";
		}
		return "form_postagem";
	}

	@RequestMapping(value = "/postagens", method = RequestMethod.POST)
        public String salvar(Postagem postagem, HttpSession session) {
		postagem.setUsuario((Usuario) session.getAttribute("usuario"));
		postagem.setDataPostagem(new Date());
		postagem.setDataAtualizacao(new Date());
		repository.save(postagem);         
		return "redirect:/postagens";
	}

	@RequestMapping(value = "/postagens/{id}")
	public ModelAndView mostrar(@PathVariable long id){
		ModelAndView mv = new ModelAndView("postagem");
		Postagem p = repository.findById(id);
		mv.addObject("postagem", p);
		Iterable<Comentario> comentarios = comentarioRepo.findByPostagem(p);
		mv.addObject("comentarios", comentarios);
		return mv;
	}

	@RequestMapping(value = "/postagens/{id}/editar", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable long id){
		ModelAndView mv = new ModelAndView("form_postagem");
		Postagem p = repository.findById(id);
		mv.addObject("postagem", p);
		return mv;
	}

	@RequestMapping(value = "/postagens/{id}", method = RequestMethod.PUT)
	public String atualizar(Postagem postagem){
		postagem.setDataAtualizacao(new Date());
		repository.save(postagem);
		return "redirect:/postagens";
	}

	@RequestMapping(value = "/postagens/{id}", method = RequestMethod.DELETE)
	public String apagar(Postagem postagem) {
		repository.delete(postagem);
		return "redirect:/postagens";
	}

	@RequestMapping(value = "/postagens/{id}/comentar", method = RequestMethod.POST)
	public String comentar(@PathVariable long id, Comentario comentario, HttpSession session){
		Postagem p = repository.findById(id);
		comentario.setPostagem(p);
		comentario.setUsuario((Usuario)session.getAttribute("usuario"));
		comentario.setDataPublicacao(new Date());
		comentarioRepo.save(comentario);
		return "redirect:/postagens/"+id;
	}
}

