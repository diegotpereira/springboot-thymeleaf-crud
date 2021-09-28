package br.com.java.springbootthymeleafcrud.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.java.springbootthymeleafcrud.domain.Contato;

public interface ContatoRepositorio extends PagingAndSortingRepository<Contato, Long>, JpaSpecificationExecutor<Contato> {
    
}
