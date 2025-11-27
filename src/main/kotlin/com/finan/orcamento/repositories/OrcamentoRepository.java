package com.finan.orcamento.repositories;

import com.finan.orcamento.model.OrcamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrcamentoRepository extends JpaRepository<OrcamentoModel, Long> {

  @Query("""
        SELECT o FROM OrcamentoModel o
        WHERE (:clienteId IS NULL OR o.cliente.id = :clienteId)
        AND (:valorMin IS NULL OR o.valor >= :valorMin)
        AND (:valorMax IS NULL OR o.valor <= :valorMax)
    """)
  List<OrcamentoModel> filtrarRelatorio(Long clienteId, BigDecimal valorMin, BigDecimal valorMax);
}
