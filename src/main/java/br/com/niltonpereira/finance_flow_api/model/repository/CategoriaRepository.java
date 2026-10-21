package br.com.niltonpereira.finance_flow_api.model.repository;

import br.com.niltonpereira.finance_flow_api.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
