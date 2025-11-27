package com.finan.orcamento.controller;

import com.finan.orcamento.service.ClienteService;
import com.finan.orcamento.service.OrcamentoService;
import com.finan.orcamento.model.OrcamentoModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

  @Autowired private ClienteService clienteService;
  @Autowired private OrcamentoService orcamentoService;

  @GetMapping
  public String page(Model model) {
    model.addAttribute("clientes", clienteService.listarTodos());
    return "relatorioClientePage";
  }

  @PostMapping("/filtrar")
  public String filtrar(
    @RequestParam(required = false) Long clienteId,
    @RequestParam(required = false) BigDecimal valorMin,
    @RequestParam(required = false) BigDecimal valorMax,
    Model model) {

    List<OrcamentoModel> lista = orcamentoService.filtrarRelatorio(clienteId, valorMin, valorMax);

    BigDecimal total = lista.stream()
      .map(OrcamentoModel::getValor)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    model.addAttribute("clientes", clienteService.listarTodos());
    model.addAttribute("orcamentos", lista);
    model.addAttribute("total", total);

    return "relatorioClientePage";
  }
}
