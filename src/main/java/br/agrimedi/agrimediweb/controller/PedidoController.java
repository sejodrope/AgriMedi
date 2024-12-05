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
import br.agrimedi.agrimediweb.repository.ClienteRepository;
import br.agrimedi.agrimediweb.repository.PedidoRepository;
import br.agrimedi.agrimediweb.repository.ProdutoRepository;
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
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ModelAndView index(){
        var listaPedidos = service.getAll();
        return new ModelAndView("pedido/index",
                "listaPedidos",listaPedidos);
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
    public String alterar(@PathVariable long id, Model model) {
        model.addAttribute("pedido", pedidoRepository.findById(id).get());
        model.addAttribute("novoItem", new ItemPedido());
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        model.addAttribute("listaClientes", clienteRepository.findAll());
        return "pedido/form";
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
