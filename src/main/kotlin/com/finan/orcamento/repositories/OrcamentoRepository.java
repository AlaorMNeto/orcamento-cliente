package com.finan.orcamento.repositories;

import com.finan.orcamento.model.OrcamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<OrcamentoModel, Long> {}
