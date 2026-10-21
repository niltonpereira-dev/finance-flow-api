package br.com.niltonpereira.finance_flow_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoResponseDto(
        Long id,
        String descricao,
        BigDecimal valor,
        String tipo,
        LocalDate data,
        Long categoriaId,
        Long UsuarioId
) {
}
