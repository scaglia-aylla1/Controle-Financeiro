package com.scaglia.controle_financeiro.services;

import com.scaglia.controle_financeiro.dto.ReceitaDTO;
import com.scaglia.controle_financeiro.models.Categoria;
import com.scaglia.controle_financeiro.models.Receita;
import com.scaglia.controle_financeiro.models.Usuario;
import com.scaglia.controle_financeiro.repositories.CategoriaRepository;
import com.scaglia.controle_financeiro.repositories.ReceitaRepository;
import com.scaglia.controle_financeiro.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;


    @Transactional
    public Receita cadastrarReceita(ReceitaDTO receitaDTO) {
        // Buscar usuário pelo e-mail
        Usuario usuario = usuarioRepository.findByEmail(receitaDTO.getEmailUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário com e-mail " + receitaDTO.getEmailUsuario() + " não encontrado."));

        // Buscar categoria pelo ID (caso tenha categoria)
        Categoria categoria = receitaDTO.getCategoriaId() != null
                ? categoriaRepository.findById(receitaDTO.getCategoriaId()).orElse(null)
                : null;

        // Criar a nova receita
        Receita receita = new Receita();
        receita.setDescricao(receitaDTO.getDescricao());
        receita.setValor(receitaDTO.getValor());
        receita.setData(receitaDTO.getData());
        receita.setUsuario(usuario);
        receita.setCategoria(categoria);

        return receitaRepository.save(receita);
    }


    public List<Receita> listarReceitas(String emailUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(emailUsuario);
        return receitaRepository.findByUsuario(usuario.orElse(null));
    }

    public Optional<Receita> buscarReceitaPorId(Long id) {
        return receitaRepository.findById(id);
    }

    @Transactional
    public Receita atualizarReceita(Long id, Receita receitaAtualizada) {
        return receitaRepository.findById(id)
                .map(receitaExistente -> {
                    receitaExistente.setDescricao(receitaAtualizada.getDescricao());
                    receitaExistente.setValor(receitaAtualizada.getValor());
                    receitaExistente.setData(receitaAtualizada.getData());
                    return receitaRepository.save(receitaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    @Transactional
    public void excluirReceita(Long id) {
        receitaRepository.deleteById(id);
    }

    public List<Receita> buscarReceitasPorPeriodo(String emailUsuario, LocalDate dataInicio, LocalDate dataFim) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(emailUsuario);
        return receitaRepository.findByUsuarioAndDataBetween(usuario.orElse(null), dataInicio, dataFim);
    }
}

