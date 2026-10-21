package br.com.niltonpereira.finance_flow_api.model.repository;

import br.com.niltonpereira.finance_flow_api.model.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
