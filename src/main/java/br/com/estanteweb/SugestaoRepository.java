package br.com.estanteweb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SugestaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(Sugestao sugestao) {
        String sql = "INSERT INTO sugestao (nome_cliente, livro_sugerido) VALUES (?, ?)";
        jdbcTemplate.update(sql, sugestao.getNomeCliente(), sugestao.getLivroSugerido());
    }


    public List<Sugestao> listarTodas() {
        String sql = "SELECT id, nome_cliente, livro_sugerido FROM sugestao ORDER BY id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Sugestao sugestao = new Sugestao(rs.getString("nome_cliente"), rs.getString("livro_sugerido"));
            sugestao.setId(rs.getLong("id"));
            return sugestao;
        });
    }

    public Sugestao buscarPorId(Long id) {
        List<Sugestao> sugestoes = jdbcTemplate.query(
                "SELECT id, nome_cliente, livro_sugerido FROM sugestao WHERE id = ?",
                (rs, rowNum) -> {
                    Sugestao sugestao = new Sugestao(rs.getString("nome_cliente"), rs.getString("livro_sugerido"));
                    sugestao.setId(rs.getLong("id"));
                    return sugestao;
                }, id);
        return sugestoes.isEmpty() ? null : sugestoes.get(0);
    }

    public int atualizar(Sugestao sugestao) {
        return jdbcTemplate.update("UPDATE sugestao SET nome_cliente = ?, livro_sugerido = ? WHERE id = ?",
                sugestao.getNomeCliente(), sugestao.getLivroSugerido(), sugestao.getId());
    }

    public int remover(Long id) {
        return jdbcTemplate.update("DELETE FROM sugestao WHERE id = ?", id);
    }
}
