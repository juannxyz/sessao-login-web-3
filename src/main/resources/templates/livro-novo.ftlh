<!DOCTYPE html>
<html>
<head>
    <title>Cadastrar Livro - EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h1>Cadastrar um livro</h1>

    <#if erro??>
        <p style="color: red;">${erro}</p>
    </#if>

    <form method="post" action="/livros">
        Título: <input type="text" name="titulo" value="${titulo!}"><br>
        Autor: <input type="text" name="autor" value="${autor!}"><br>
        Ano: <input type="text" name="ano" value="${(ano?c)!}"><br>
        Preço: <input type="text" name="preco" value="${(preco?c)!}"><br>
        <button type="submit">Cadastrar</button>
    </form>
</body>
</html>
