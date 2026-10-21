package br.com.niltonpereira.finance_flow_api.dto;

public record CategoriaResponseDto(

        Long id,
        String nome,
        String tipo,
        Long usuarioId
) {
}
