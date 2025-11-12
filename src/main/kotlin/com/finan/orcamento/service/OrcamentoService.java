package com.finan.orcamento.service;

import com.finan.orcamento.model.*;
import com.finan.orcamento.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrcamentoService {
    @Autowired private OrcamentoRepository orcamentoRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    public List<OrcamentoModel> listarTodos() { return orcamentoRepository.findAll(); }

    // salva para usuário (set usuario, limpa cliente)
    public OrcamentoModel salvarParaUsuario(Long usuarioId, OrcamentoModel orcamento) {
        UsuarioModel usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuarioId));
        orcamento.setUsuario(usuario);
        orcamento.setCliente(null);
        return orcamentoRepository.save(orcamento);
    }

    // salva para cliente (set cliente, limpa usuario)
    public OrcamentoModel salvarParaCliente(Long clienteId, OrcamentoModel orcamento) {
        ClienteModel cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + clienteId));
        orcamento.setCliente(cliente);
        orcamento.setUsuario(null);
        return orcamentoRepository.save(orcamento);
    }

    public void excluir(Long id) { orcamentoRepository.deleteById(id); }
}
