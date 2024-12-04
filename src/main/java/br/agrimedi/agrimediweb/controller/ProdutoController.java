package br.agrimedi.agrimediweb.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.agrimedi.agrimediweb.entity.Produto;
import br.agrimedi.agrimediweb.repository.ProdutoRepository;
import br.agrimedi.agrimediweb.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ModelAndView index(){
        var listaProdutos = service.getAll();
        return new ModelAndView("produto/index",
                "listaProdutos",listaProdutos);
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        var produto = new Produto();

        HashMap<String,Object> dados = 
            new HashMap<>();
        dados.put("produto",produto);
        
        return new ModelAndView("produto/form", 
                    dados);

    }
    @PostMapping
    public ModelAndView save(Produto produto){
        service.save(produto);
        return new ModelAndView("redirect:/produtos");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id){
        var produto = service.getById(id);

        HashMap<String,Object> dados = new HashMap<>();
        dados.put("produto",produto);
        

        return new ModelAndView("produto/form", dados);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long id){
        var produto = service.getById(id);
        if(produto != null){
            service.delete(id);
        }
        return new ModelAndView("redirect:/produtos");
    }

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "produtos";
    }
}
