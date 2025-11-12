package com.finan.orcamento.controller;

import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired private UsuarioService usuarioService;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("usuarioModel", new UsuarioModel());
        return "usuarioPage";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute UsuarioModel usuario) {
        usuarioService.salvar(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("usuarioModel", usuarioService.buscarPorId(id));
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarioPage";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam String termo, Model model) {
        model.addAttribute("usuarios", usuarioService.buscarPorNomeOuCpf(termo));
        model.addAttribute("usuarioModel", new UsuarioModel());
        return "usuarioPage";
    }
}
