package br.com.niltonpereira.finance_flow_api.model.repository;

import br.com.niltonpereira.finance_flow_api.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
