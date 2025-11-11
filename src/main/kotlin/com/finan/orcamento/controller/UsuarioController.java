package com.finan.orcamento.controller;

import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.repositories.UsuarioRepository;
import com.finan.orcamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String getUsuarioPage(Model model) {
        model.addAttribute("usuarioModel", new UsuarioModel());
        model.addAttribute("usuarioToEdit", new UsuarioModel()); // ADICIONADO
        model.addAttribute("usuarios", usuarioService.buscarUsuario()); // opcional, mostra já a lista
        return "usuarioPage";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UsuarioModel> cadastraUsuario(@ModelAttribute UsuarioModel usuarioModel) {
        return ResponseEntity.ok(usuarioService.cadastrarUsuario(usuarioModel));
    }

    @GetMapping("pesquisa")
    public String listarUsuarios(Model model) {
        List<UsuarioModel> usuarios = usuarioService.buscarUsuario();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioModel", new UsuarioModel());
        model.addAttribute("usuarioToEdit", new UsuarioModel()); // ADICIONADO
        return "usuarioPage";
    }

    @PostMapping("buscar")
    public String buscarPorNome(@RequestParam("nomeBusca") String nomeBusca, Model model) {
        List<UsuarioModel> resultados = usuarioService.buscarPorNome(nomeBusca);
        model.addAttribute("resultadosBusca", resultados);
        // preenche o formulário de edição com o primeiro resultado (se houver)
        model.addAttribute("usuarioToEdit", resultados.isEmpty() ? new UsuarioModel() : resultados.get(0));
        // também atualiza a lista completa e o form de criação
        model.addAttribute("usuarios", usuarioService.buscarUsuario());
        model.addAttribute("usuarioModel", new UsuarioModel());
        return "usuarioPage";
    }

    @PostMapping("atualizar")
    public ResponseEntity<UsuarioModel> atualizarUsuario(@ModelAttribute("usuarioToEdit") UsuarioModel usuarioToEdit) {
        if (usuarioToEdit.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        UsuarioModel atualizado = usuarioService.atualizaUsuario(usuarioToEdit, usuarioToEdit.getId());
        return ResponseEntity.ok(atualizado);
    }
}