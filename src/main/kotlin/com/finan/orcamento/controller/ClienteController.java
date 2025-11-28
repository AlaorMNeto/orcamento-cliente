package com.finan.orcamento.controller;

import com.finan.orcamento.model.ClienteModel;
import com.finan.orcamento.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping
  public String page(Model model) {
    model.addAttribute("listaClientes", clienteService.listarTodos());
    model.addAttribute("clienteModel", new ClienteModel());
    return "clientePage";
  }

  @PostMapping("/salvar")
  public String salvar(@ModelAttribute ClienteModel cliente) {
    clienteService.salvar(cliente);
    return "redirect:/clientes";
  }

  @GetMapping("/excluir/{id}")
  public String excluir(@PathVariable Long id) {
    clienteService.excluir(id);
    return "redirect:/clientes";
  }

  @GetMapping("/editar/{id}")
  public String editar(@PathVariable Long id, Model model) {
    model.addAttribute("clienteModel", clienteService.buscarPorId(id));
    model.addAttribute("listaClientes", clienteService.listarTodos());
    return "clientePage";
  }

  @PostMapping("/buscar")
  public String buscar(@RequestParam String termo, Model model) {
    model.addAttribute("listaClientes", clienteService.buscarPorNomeOuCpf(termo));
    model.addAttribute("clienteModel", new ClienteModel());
    return "clientePage";
  }

  @GetMapping("/relatorio")
  public String relatorioCliente(Model model) {
    model.addAttribute("listaClientes", clienteService.listarTodos());
    return "relatorioClientePage";
  }
}
