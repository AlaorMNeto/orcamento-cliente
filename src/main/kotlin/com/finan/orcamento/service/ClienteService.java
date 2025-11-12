package com.finan.orcamento.service;

import com.finan.orcamento.model.ClienteModel;
import com.finan.orcamento.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    @Autowired private ClienteRepository clienteRepository;

    public List<ClienteModel> listarTodos() { return clienteRepository.findAll(); }
    public ClienteModel salvar(ClienteModel c) { return clienteRepository.save(c); }
    public void excluir(Long id) { clienteRepository.deleteById(id); }
    public ClienteModel buscarPorId(Long id) { return clienteRepository.findById(id).orElse(null); }
    public List<ClienteModel> buscarPorNomeOuCpf(String termo) {
        if (termo == null || termo.isBlank()) return clienteRepository.findAll();
        return clienteRepository.findByNomeContainingIgnoreCaseOrCpfContaining(termo.trim(), termo.trim());
    }
}
