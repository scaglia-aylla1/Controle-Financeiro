package com.scaglia.controle_financeiro.dto;

import com.scaglia.controle_financeiro.models.Categoria;
import com.scaglia.controle_financeiro.models.Despesa;
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
public class DespesaDTO {

    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;

    @NotNull(message = "O valor é obrigatório")
    @Min(value = 0, message = "O valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "A data é obrigatória")
    private LocalDate data;

    @NotNull(message = "A categoria é obrigatória")
    private Long categoriaId;

    @NotBlank(message = "O e-mail do usuário é obrigatório")
    private String emailUsuario;

    public Despesa toEntity() {
        Despesa despesa = new Despesa();
        despesa.setDescricao(this.descricao);
        despesa.setValor(this.valor);
        despesa.setData(this.data);
        despesa.setCategoria(new Categoria(this.categoriaId));
        return despesa;
    }


}

