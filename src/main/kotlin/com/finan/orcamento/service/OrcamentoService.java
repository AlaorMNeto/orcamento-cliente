package com.finan.orcamento.service;

import com.finan.orcamento.model.ClienteModel;
import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.repositories.ClienteRepository;
import com.finan.orcamento.repositories.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // ✅ Salvar orçamento associado ao cliente
    public OrcamentoModel salvarOrcamento(OrcamentoModel orcamento, Long clienteId) {
        ClienteModel cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        orcamento.setCliente(cliente);
        return orcamentoRepository.save(orcamento);
    }

    // ✅ Listar todos os orçamentos cadastrados
    public List<OrcamentoModel> listarOrcamentos() {
        return orcamentoRepository.findAll();
    }
}
