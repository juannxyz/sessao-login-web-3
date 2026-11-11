package br.com.estanteweb;

public class Sugestao {
    private long id;
    private String nomeCliente;
    private String livroSugerido;

    public Sugestao(String nomeCliente, String livroSugerido) {
        this.nomeCliente = nomeCliente;
        this.livroSugerido = livroSugerido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public void setLivroSugerido(String livroSugerido) { this.livroSugerido = livroSugerido; }

    public String getLivroSugerido() {
        return livroSugerido;
    }
}
