<!DOCTYPE html>
<html>
<head>
    <title>Entrar - EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">
    <h1>Entrar</h1>
    <#if erro??>
        <p>${erro}</p>
    </#if>
    <form method="post" action="/login">
        E-mail: <input type="email" name="email" value="${email!}"><br>
        Senha: <input type="password" name="senha"><br>
        <button type="submit">Entrar</button>
    </form>
</body>
</html>
