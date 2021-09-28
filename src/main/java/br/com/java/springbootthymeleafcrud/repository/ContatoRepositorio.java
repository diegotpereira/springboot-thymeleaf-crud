package br.com.java.springbootthymeleafcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.java.springbootthymeleafcrud.domain.Contato;

public interface ContatoRepositorio extends JpaRepository<Contato, Long> {
    
}
