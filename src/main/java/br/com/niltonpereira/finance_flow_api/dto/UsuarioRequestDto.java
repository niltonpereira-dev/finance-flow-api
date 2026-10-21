package br.com.niltonpereira.finance_flow_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;




public record UsuarioRequestDto(


        @NotBlank(message = "O nome é Obrigatório")
        String nome,


        @NotBlank(message = "O e-mail é Obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email,

        @NotBlank(message = "A senha é Obrigatória")
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
        String senha
) {
}
