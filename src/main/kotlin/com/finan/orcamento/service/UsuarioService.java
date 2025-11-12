package com.finan.orcamento.service;

import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired private UsuarioRepository usuarioRepository;

    public List<UsuarioModel> listarTodos() { return usuarioRepository.findAll(); }
    public UsuarioModel salvar(UsuarioModel u) { return usuarioRepository.save(u); }
    public void excluir(Long id) { usuarioRepository.deleteById(id); }
    public UsuarioModel buscarPorId(Long id) { return usuarioRepository.findById(id).orElse(null); }
    public List<UsuarioModel> buscarPorNomeOuCpf(String termo) {
        if (termo == null || termo.isBlank()) return usuarioRepository.findAll();
        return usuarioRepository.findByNomeContainingIgnoreCaseOrCpfContaining(termo.trim(), termo.trim());
    }
}
