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

import br.com.java.springbootthymeleafcrud.domain.Contato;
import br.com.java.springbootthymeleafcrud.exception.ResourceNotFoundException;
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

    @GetMapping(value = "/contatos")
    public String buscarContatos(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Contato> contatos = contatoService.buscarTodos(pageNumber, ROW_PER_PAGE);
        
        long count = contatoService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("contatos", contatos);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);

        return "contato-lista";
    }

    @GetMapping(value = "/contatos/{contatoId}")
    public String buscarContatoPorId(Model model, @PathVariable long contatoId) {
        Contato contato = null;

        try {
            contato = contatoService.buscarPorId(contatoId);
        } catch (ResourceNotFoundException e) {
            model.addAttribute("errorMessage", "Contato não encontrado..!");
        }
        model.addAttribute("contato", contato);

        return "contato";
    }

    @GetMapping(value = {"/contatos/add"})
    public String mostrarAdicionarContato(Model model) {
        Contato contato = new Contato();
        model.addAttribute("add", true);
        model.addAttribute("contato", contato);

        return "contato-editar";
    }

    @PostMapping(value = "/contatos/add")
    public String addContato(Model model, @ModelAttribute("contato") Contato contato) {
        try {
            Contato novoContato = contatoService.salvar(contato);

            return "redirect:/contatos/" + String.valueOf(novoContato.getId());
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);

            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);

            return "contato-editar";
        }
    }

    @GetMapping(value = {"/contatos/{contatoId}/editar"})
    public String mostrarEditarContato(Model model, @PathVariable long contatoId) {
        Contato contato = null;

        try {
            contato = contatoService.buscarPorId(contatoId);
        } catch (ResourceNotFoundException  e) {
            model.addAttribute("errorMessage", "Contato não encontrado");
        }
        model.addAttribute("add", false);
        model.addAttribute("contato", contato);

        return "contato-editar";
    }

    @PostMapping(value = {"/contatos{contatoId}/editar"})
    public String atualizarContato(Model model, @PathVariable long contatoId, @ModelAttribute("contato") Contato contato) {
        try {
            contato.setId(contatoId);
            contatoService.atualizar(contato);

            return "redirect:/contatos/" + String.valueOf(contato.getId());

        } catch (Exception e) {
    
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);

            return "contato-editar";

        }
    }

    @GetMapping(value = {"/contatos/{contatoId}/deletar"})
    public String exibirdeletarContatoPorId(Model model, @PathVariable long contatoId) {
        Contato contato = null;

        try {
            contato = contatoService.buscarPorId(contatoId);
        } catch (ResourceNotFoundException  e) {
            model.addAttribute("errorMessage", "Contato não encontrado..!");
        }
        model.addAttribute("permitirDeletar", true);
        model.addAttribute("contato", contato);

        return "contato";
    }

    @PostMapping(value = {"/contatos/{contatoId}/deletar"})
    public String deletarContatoPorId(Model model, @PathVariable long contatoId) {
        try {
            contatoService.deletarPorId(contatoId);

            return "redirect:/contatos";

        } catch (ResourceNotFoundException e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            return "contato";
        }
    }
}
