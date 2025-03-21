package com.scaglia.controle_financeiro.repositories;

import com.scaglia.controle_financeiro.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para buscar usuário por email
    Usuario findByEmail(String email);

    // Verifica se o email já existe
    boolean existsByEmail(String email);
}
