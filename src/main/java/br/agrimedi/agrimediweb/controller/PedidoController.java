package br.agrimedi.agrimediweb.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.agrimedi.agrimediweb.entity.ItemPedido;
import br.agrimedi.agrimediweb.entity.Pedido;
import br.agrimedi.agrimediweb.entity.PedidoItem;
import br.agrimedi.agrimediweb.repository.ClienteRepository;
import br.agrimedi.agrimediweb.repository.PedidoRepository;
import br.agrimedi.agrimediweb.repository.ProdutoRepository;
import br.agrimedi.agrimediweb.service.ClienteService;
import br.agrimedi.agrimediweb.service.PedidoService;
import br.agrimedi.agrimediweb.service.ProdutoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ModelAndView index() {
        var modelAndView = new ModelAndView("pedido/index");
        modelAndView.addObject("listaPedidos", service.getAll());
        return modelAndView;
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("novoItem", new ItemPedido());
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        model.addAttribute("listaClientes", clienteRepository.findAll());
        return "pedido/form";
    }

    @PostMapping(params = "incitem")
    public ModelAndView incluirItem(Pedido pedido, 
                ItemPedido novoItem){
        pedido.getItens().add(novoItem);

        var listaProdutos = produtoService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("pedido",pedido);
        dados.put("novoItem", new ItemPedido());
        dados.put("listaProdutos",listaProdutos);

        return new ModelAndView("pedido/form",dados);
    }

    @PostMapping(params = "save")
    public ModelAndView save(Pedido pedido){
        service.save(pedido);
        return new ModelAndView("redirect:/pedidos");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable Long id) {
        var pedido = service.findById(id);
        var clientes = clienteService.getAll();
        var produtos = produtoService.getAll();
        
        HashMap<String, Object> dados = new HashMap<>();
        dados.put("pedido", pedido);
        dados.put("listaClientes", clientes);
        dados.put("listaProdutos", produtos);
        dados.put("novoItem", new PedidoItem());
        
        return new ModelAndView("pedido/form", dados);
    }

    @PostMapping
    public String salvar(@ModelAttribute Pedido pedido) {
        if (pedido.getId() > 0) {
            Pedido pedidoExistente = pedidoRepository.findById(pedido.getId()).get();
            pedido.setCliente(pedido.getCliente() != null ? pedido.getCliente() : pedidoExistente.getCliente());
        }
        pedidoRepository.save(pedido);
        return "redirect:/pedidos";
    }

    @PostMapping(params = "removeitem")
    public ModelAndView removerItem(@RequestParam("removeitem") int index, 
                                Pedido pedido){
        pedido.getItens().remove(index);

        var listaProdutos = produtoService.getAll();

        HashMap<String,Object> dados = 
            new HashMap<>();
        dados.put("pedido",pedido);
        dados.put("novoItem", new ItemPedido());
        dados.put("listaProdutos",listaProdutos);

        return new ModelAndView("pedido/form",dados);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long id){
        var pedido = service.getById(id);
        if(pedido != null){
            service.delete(id);
        }
        return new ModelAndView("redirect:/pedidos");
    }
}
