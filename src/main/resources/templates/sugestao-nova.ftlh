<!DOCTYPE html>
<html>
<head>
    <title>Sugerir Livro - EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h1>Sugerir um livro</h1>

    <#if erro??>
        <p style="color: red;">${erro}</p>
    </#if>

    <form method="post" action="/sugestoes">
        Seu nome: <input type="text" name="nomeCliente" value="${nomeCliente!}"><br>
        Livro sugerido: <input type="text" name="livroSugerido" value="${livroSugerido!}"><br>
        <button type="submit">Enviar sugestão</button>
    </form>
</body>
</html>