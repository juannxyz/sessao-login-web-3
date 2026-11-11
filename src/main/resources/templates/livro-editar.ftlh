<!DOCTYPE html>
<html>
<head><title>Corrigir livro - EstanteWeb</title></head>
<body>
    <#include "cabecalho.ftlh">
    <h1>Corrigir livro</h1>
    <#if erro??><p style="color: red;">${erro}</p></#if>
    <form method="post" action="/livros/${livro.id?c}/editar">
        Título: <input type="text" name="titulo" value="${tituloInformado!livro.titulo}"><br>
        Autor: <input type="text" name="autor" value="${autorInformado!livro.autor}"><br>
        Ano: <input type="text" name="ano" value="${anoInformado!livro.ano?c}"><br>
        Preço: <input type="text" name="preco" value="${precoInformado!livro.preco?c}"><br>
        <button type="submit">Salvar correção</button>
    </form>
    <p><a href="/livros/${livro.id?c}">Cancelar</a></p>
</body>
</html>
