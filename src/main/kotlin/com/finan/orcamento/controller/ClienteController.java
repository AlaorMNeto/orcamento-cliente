package com.finan.orcamento.controller;

import com.finan.orcamento.model.ClienteModel;
import com.finan.orcamento.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Página inicial de clientes
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("clienteModel", new ClienteModel());
        return "clientePage";
    }

    // Salvar novo cliente ou atualizar existente
    @PostMapping
    public String salvarCliente(@ModelAttribute ClienteModel clienteModel) {
        clienteService.salvar(clienteModel);
        return "redirect:/clientes";
    }

    // Excluir cliente
    @GetMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable Long id) {
        clienteService.excluir(id);
        return "redirect:/clientes";
    }

    // Editar cliente
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        ClienteModel cliente = clienteService.buscarPorId(id);
        model.addAttribute("clienteModel", cliente);
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientePage";
    }

    // Buscar cliente por nome ou CPF
    @PostMapping("/buscar")
    public String buscarCliente(@RequestParam String termo, Model model) {
        List<ClienteModel> clientesEncontrados = clienteService.buscarPorNomeOuCpf(termo);
        model.addAttribute("clientes", clientesEncontrados);
        model.addAttribute("clienteModel", new ClienteModel());
        return "clientePage";
    }
}
