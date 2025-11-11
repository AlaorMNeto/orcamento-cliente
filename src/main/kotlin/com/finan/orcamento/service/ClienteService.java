package com.finan.orcamento.service;

import com.finan.orcamento.model.ClienteModel;
import com.finan.orcamento.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Salvar cliente
    public ClienteModel salvar(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    // Listar todos os clientes
    public List<ClienteModel> listarTodos() {
        return clienteRepository.findAll();
    }

    // Buscar cliente por ID
    public ClienteModel buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // Excluir cliente
    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

    // Buscar por nome ou CPF
    public List<ClienteModel> buscarPorNomeOuCpf(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return clienteRepository.findAll();
        }
        return clienteRepository.findByNomeContainingIgnoreCaseOrCpfContaining(termo.trim(), termo.trim());
    }
}
