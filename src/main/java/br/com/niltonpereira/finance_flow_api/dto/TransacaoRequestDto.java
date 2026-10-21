package br.com.niltonpereira.finance_flow_api.dto;

import br.com.niltonpereira.finance_flow_api.model.entity.TipoTransicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoRequestDto(

        @NotBlank(message = "A descrição é Obrigatória")
        String descricao,

        @NotNull(message = "O valor é Obrigatório")
        @Positive(message = "O valor deve ser maior que zero")
        BigDecimal valor,

        @NotNull(message = "O tipo de transação é Obrigatório")
        TipoTransicao tipo,

        @NotNull(message = "A data é Obrigatória")
        LocalDate data,

        @NotNull(message = "O ID da categoria é obrigatório")
        Long categoriaId,

        @NotNull(message = "O ID usuário é obrigatório")
        Long usuarioId
) {
}
