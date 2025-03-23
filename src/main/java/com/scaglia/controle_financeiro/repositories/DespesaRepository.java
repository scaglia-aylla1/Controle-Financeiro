package com.scaglia.controle_financeiro.repositories;


import com.scaglia.controle_financeiro.models.Despesa;
import com.scaglia.controle_financeiro.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByUsuario(Usuario usuario);
    List<Despesa> findByUsuarioAndDataBetween(Usuario usuario, LocalDate dataInicio, LocalDate dataFim);

}
