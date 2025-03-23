package com.scaglia.controle_financeiro.services;

import com.scaglia.controle_financeiro.dto.DespesaDTO;
import com.scaglia.controle_financeiro.models.Categoria;
import com.scaglia.controle_financeiro.models.Despesa;
import com.scaglia.controle_financeiro.models.Usuario;
import com.scaglia.controle_financeiro.repositories.CategoriaRepository;
import com.scaglia.controle_financeiro.repositories.DespesaRepository;
import com.scaglia.controle_financeiro.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;


    @Transactional
    public Despesa cadastrarDespesa(Despesa despesa, String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o e-mail: " + emailUsuario));

        Categoria categoria = categoriaRepository.findById(despesa.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        despesa.setUsuario(usuario);
        despesa.setCategoria(categoria);

        return despesaRepository.save(despesa);
    }


    public List<Despesa> listarDespesas(String emailUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(emailUsuario);
        return despesaRepository.findByUsuario(usuario.orElse(null));
    }

    public Optional<Despesa> buscarDespesaPorId(Long id) {
        return despesaRepository.findById(id);
    }

    @Transactional
    public Despesa atualizarDespesa(Long id, Despesa despesaAtualizada) {
        return despesaRepository.findById(id)
                .map(despesaExistente -> {
                    despesaExistente.setDescricao(despesaAtualizada.getDescricao());
                    despesaExistente.setValor(despesaAtualizada.getValor());
                    despesaExistente.setData(despesaAtualizada.getData());
                    return despesaRepository.save(despesaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
    }

    @Transactional
    public void excluirDespesa(Long id) {
        despesaRepository.deleteById(id);
    }

    public List<Despesa> buscarDespesasPorPeriodo(String emailUsuario, LocalDate dataInicio, LocalDate dataFim) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(emailUsuario);
        return despesaRepository.findByUsuarioAndDataBetween(usuario.orElse(null), dataInicio, dataFim);
    }
}

