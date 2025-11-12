package com.finan.orcamento.repositories;

import com.finan.orcamento.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    List<ClienteModel> findByNomeContainingIgnoreCaseOrCpfContaining(String nome, String cpf);
}
