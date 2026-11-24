<!DOCTYPE html>
<html>
<head>
    <title>Acervo EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">
    <h1>Acervo EstanteWeb</h1>
    <#if usuarioLogado??>
        <p><a href="/livros/novo">Cadastrar livro</a></p>
    </#if>
    <p>Ordenar por:
        <a href="/livros/html?ordem=titulo">Título</a> |
        <a href="/livros/html?ordem=autor">Autor</a> |
        <a href="/livros/html?ordem=ano">Ano</a> |
        <a href="/livros/html?ordem=preco">Preço</a>
    </p>

    <#if livros?size == 0>
        <p>Nenhum livro no acervo no momento</p>
    <#else>
        <ul>
            <#list livros as livro>
                <li>
                    <a href="/livros/${livro.id?c}">${livro.titulo}</a>, de ${livro.autor} (R$ ${livro.preco?c})
                    <#if usuarioLogado??>
                        <form method="post" action="/livros/${livro.id?c}/excluir" class="remover-livro" data-titulo="${livro.titulo}">
                            <button type="submit">Excluir</button>
                        </form>
                    </#if>
                </li>
            </#list>
        </ul>
    </#if>
    <script>
        document.querySelectorAll("form.remover-livro").forEach((formulario) => {
            formulario.addEventListener("submit", (evento) => {
                const confirmar = window.confirm(`Deseja remover o livro "${formulario.dataset.titulo}"?`);
                if (!confirmar) evento.preventDefault();
            });
        });
    </script>
</body>
</html>
