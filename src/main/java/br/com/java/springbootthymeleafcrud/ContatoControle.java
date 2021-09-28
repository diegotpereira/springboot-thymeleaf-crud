package br.com.java.springbootthymeleafcrud;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.java.springbootthymeleafcrud.service.ContatoService;

@Controller
public class ContatoControle {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private ContatoService contatoService;

    @Value("${msg.titulo}")
    private String titulo;

    @GetMapping(value = {"/", "index"})
    public String  index(Model model) {
        model.addAttribute("titulo", titulo);
        return "index";
    }

    // @GetMapping(value = "/contatos")
    // public String buscarContatos(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) { ... }

    // @GetMapping(value = "/contatos/{contatoId}")
    // public String buscarContatoPorId(Model model, @PathVariable long contatoId) { ... }

    // @GetMapping(value = {"/contatos/add"})
    // public String mostrarAdicionarContato(Model model) { ... }

    // @PostMapping(value = "/contatos/add")
    // public String addContato(Model model, @ModelAttribute("contato") Contato contato) { ... }

    // @GetMapping(value = {"/contatos/{contatoId}/editar"})
    // public String mostrarEditarContato(Model model, @PathVariable long contatoId) { ... }

    // @PostMapping(value = {"/contatos{contatoId}/editar"})
    // public String atualizarContato(Model model, @PathVariable long contatoId, @ModelAttribute("contato") Contato contato) { ... }

    // @GetMapping(value = {"/contatos/{contatoId}/deletar"})
    // public String deletarContatoPorId(Model model, @PathVariable long contatoId) { ... }


}
