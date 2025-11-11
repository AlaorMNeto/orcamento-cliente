package com.finan.orcamento.controller;

import com.finan.orcamento.model.ClienteModel;
import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.service.ClienteService;
import com.finan.orcamento.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private ClienteService clienteService;

    // ✅ Página principal de orçamentos
    @GetMapping
    public String getOrcamentoPage(Model model) {
        model.addAttribute("orcamentoModel", new OrcamentoModel());
        model.addAttribute("orcamentos", orcamentoService.listarOrcamentos());
        return "orcamentoPage";
    }

    // ✅ Salvar novo orçamento vinculado a um cliente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrcamentoModel> salvarOrcamento(
            @ModelAttribute OrcamentoModel orcamentoModel,
            @RequestParam("clienteId") Long clienteId) {

        OrcamentoModel novoOrcamento = orcamentoService.salvarOrcamento(orcamentoModel, clienteId);
        return ResponseEntity.ok(novoOrcamento);
    }

    // ✅ Buscar cliente por nome ou CPF
    @PostMapping("/buscarCliente")
    public String buscarCliente(@RequestParam String termo, Model model) {
        List<ClienteModel> clientesEncontrados = clienteService.buscarPorNomeOuCpf(termo);
        model.addAttribute("clientesEncontrados", clientesEncontrados);
        model.addAttribute("orcamentoModel", new OrcamentoModel());
        model.addAttribute("orcamentos", orcamentoService.listarOrcamentos());
        return "orcamentoPage";
    }
}
