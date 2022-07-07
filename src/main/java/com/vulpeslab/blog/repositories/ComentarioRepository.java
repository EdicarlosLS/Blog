package com.vulpeslab.blog.repositories;

import com.vulpeslab.blog.models.Comentario;
import com.vulpeslab.blog.models.Postagem;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ComentarioRepository extends CrudRepository<Comentario, Long>{
	List<Comentario> findByPostagem(Postagem p);

}

