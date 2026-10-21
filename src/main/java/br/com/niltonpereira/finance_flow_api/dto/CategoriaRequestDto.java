package br.com.niltonpereira.finance_flow_api.dto;

import br.com.niltonpereira.finance_flow_api.model.entity.TipoTransicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDto(


        @NotBlank(message = "O nome é Obrigatório")
        String nome,


        @NotNull(message = "O tipo é Obrigatório")
        TipoTransicao tipo,


        @NotNull(message = "O ID do usuário é obrigatório")
        Long usuarioId
) {
}
