package com.finan.orcamento.repositories;

import com.finan.orcamento.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    // Busca cliente por nome (ignora maiúsculas/minúsculas) ou CPF (contendo o termo)
    List<ClienteModel> findByNomeContainingIgnoreCaseOrCpfContaining(String nome, String cpf);
}
