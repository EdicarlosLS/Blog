package com.vulpeslab.blog.repositories;

import com.vulpeslab.blog.models.Postagem;

import org.springframework.data.repository.CrudRepository;

public interface PostagemRepository extends CrudRepository<Postagem, Long>{
	Postagem findById(long id);
}


