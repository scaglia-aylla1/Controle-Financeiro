package com.scaglia.controle_financeiro.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDTO {

    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;

    @NotNull(message = "O valor é obrigatório")
    @Min(value = 0, message = "O valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "A data é obrigatória")
    private LocalDate data;

    private Long categoriaId; // ID da categoria

    @NotBlank(message = "O e-mail do usuário é obrigatório")
    private String emailUsuario; // Agora o e-mail é passado na requisição
}
