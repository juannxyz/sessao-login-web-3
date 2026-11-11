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
    <p><a href="/livros/html">Voltar para o acervo</a></p>
</body>
</html>
