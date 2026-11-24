package br.com.estanteweb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Usuario buscarPorEmail(String email) {
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(
                "SELECT * FROM usuario WHERE email = ?", email);
        return linhas.isEmpty() ? null : Usuario.deMap(linhas.get(0));
    }
}
