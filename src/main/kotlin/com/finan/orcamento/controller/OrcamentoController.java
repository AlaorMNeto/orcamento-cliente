package com.finan.orcamento.controller;

import com.finan.orcamento.model.*;
import com.finan.orcamento.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentoController {
    @Autowired private OrcamentoService orcamentoService;
    @Autowired private ClienteService clienteService;
    @Autowired private UsuarioService usuarioService;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("orcamentos", orcamentoService.listarTodos());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("orcamentoModel", new OrcamentoModel());
        return "orcamentoPage";
    }

    // Endpoint genérico: decide por parâmetros qual salvar
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute OrcamentoModel orcamento,
                         @RequestParam(required = false) Long clienteId,
                         @RequestParam(required = false) Long usuarioId,
                         Model model) {

        if (usuarioId != null) {
            orcamentoService.salvarParaUsuario(usuarioId, orcamento);
        } else if (clienteId != null) {
            orcamentoService.salvarParaCliente(clienteId, orcamento);
        } else {
            model.addAttribute("erro", "Escolha um Cliente ou Usuário antes de salvar.");
            return page(model);
        }
        return "redirect:/orcamentos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        orcamentoService.excluir(id);
        return "redirect:/orcamentos";
    }
}
