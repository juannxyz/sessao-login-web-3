<!DOCTYPE html>
<html>
<head>
    <title>${livro.titulo} - EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">
    <h1>${livro.titulo}</h1>
    <p><strong>Autor:</strong> ${livro.autor}</p>
    <p><strong>Ano:</strong> ${livro.ano?c}</p>
    <p><strong>Preço:</strong> R$ ${livro.preco?c}</p>
    <#if usuarioLogado??>
        <p><a href="/livros/${livro.id?c}/editar">Corrigir livro</a></p>
        <p><a href="/livros/${livro.id?c}/excluir">Excluir livro</a></p>
    </#if>
    <p><a href="/livros/html">Voltar para o acervo</a></p>
</body>
</html>
