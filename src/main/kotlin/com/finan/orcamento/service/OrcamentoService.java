package com.finan.orcamento.service;

import com.finan.orcamento.model.ClienteModel;
import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.repositories.ClienteRepository;
import com.finan.orcamento.repositories.OrcamentoRepository;
import com.finan.orcamento.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrcamentoService {

  @Autowired
  private OrcamentoRepository orcamentoRepository;

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  public List<OrcamentoModel> listarTodos() {
    return orcamentoRepository.findAll();
  }

  public void salvarParaCliente(Long clienteId, OrcamentoModel orcamento) {
    ClienteModel cliente = clienteRepository.findById(clienteId).orElse(null);
    if (cliente != null) {
      orcamento.setCliente(cliente);
      orcamento.setUsuario(null);
      orcamentoRepository.save(orcamento);
    }
  }

  public void salvarParaUsuario(Long usuarioId, OrcamentoModel orcamento) {
    UsuarioModel usuario = usuarioRepository.findById(usuarioId).orElse(null);
    if (usuario != null) {
      orcamento.setUsuario(usuario);
      orcamento.setCliente(null);
      orcamentoRepository.save(orcamento);
    }
  }

  // MÉTODO QUE FALTAVA
  public void excluir(Long id) {
    orcamentoRepository.deleteById(id);
  }

  // OrcamentoService.java

  public List<OrcamentoModel> filtrarRelatorio(Long clienteId, BigDecimal valorMin, BigDecimal valorMax) {

    // Se nenhum filtro for enviado → retorna todos
    if (clienteId == null && valorMin == null && valorMax == null) {
      return orcamentoRepository.findAll();
    }

    return orcamentoRepository.filtrarRelatorio(clienteId, valorMin, valorMax);
  }
}
