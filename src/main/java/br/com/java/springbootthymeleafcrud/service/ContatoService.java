package br.com.java.springbootthymeleafcrud.service;

import br.com.java.springbootthymeleafcrud.domain.Contato;
import br.com.java.springbootthymeleafcrud.exception.BadResourceException;
import br.com.java.springbootthymeleafcrud.exception.ResourceAlreadyExistsException;
import br.com.java.springbootthymeleafcrud.exception.ResourceNotFoundException;
import br.com.java.springbootthymeleafcrud.repository.ContatoRepositorio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ContatoService {
    
    @Autowired
    private ContatoRepositorio contatoRepositorio;

    private boolean existePorId(Long id){
        return contatoRepositorio.existsById(id);
    }

    public Contato buscarPorId(Long id) throws ResourceNotFoundException {
        Contato contato = contatoRepositorio.findById(id).orElse(null);
        if (contato == null) {
            throw new ResourceNotFoundException("Não é possível encontrar contato com id: " + id);
        }
        else return contato;
    }

    public List<Contato> buscarTodos(int pageNumber, int rowPerPage) {
        List<Contato> contatos = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber -1, rowPerPage,
                 Sort.by("id").ascending());
        contatoRepositorio.findAll(sortedByIdAsc).forEach(contatos::add);
        return contatos;   
    }

    public Contato salvar(Contato contato) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(contato.getNome())) {
            if (contato.getId() != null && existePorId(contato.getId())) {
                throw new ResourceAlreadyExistsException("Contato com id: " + contato.getId() + "já existe");
            }

            return contatoRepositorio.save(contato);
        }else {
            BadResourceException exc = new BadResourceException("Falha ao Salvar Contato..!");
            exc.addErrorMessage("Contato nulo ou vazio..!");
            throw exc;
        }
    }

    public void atualizar(Contato contato) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(contato.getNome())) {
            if (!existePorId(contato.getId())) {
                throw new ResourceNotFoundException("Não é possível encontrar contato com id: " + contato.getId());
            }
            contatoRepositorio.save(contato);
        }else {
            BadResourceException exc = new BadResourceException("Falha ao Salvar Contato..!");
            exc.addErrorMessage("Contato nulo ou vazio..!");
            throw exc;
        }
    }

    public void deletarPorId(Long id) throws ResourceNotFoundException {
        if (!existePorId(id)) {
            throw new ResourceNotFoundException("Não é possível encontrar contato com id: " + id);
        } else {
            contatoRepositorio.deleteById(id);
        }
    }

    public Long count() {
        return contatoRepositorio.count();
    }
}
