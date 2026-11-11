<!DOCTYPE html>
<html>
<head>
    <title>Contato - EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h1>Fale com a EstanteWeb</h1>

    <#if erro??>
        <p style="color: red;">${erro}</p>
    </#if>

    <form method="post" action="/contato">
        Nome: <input type="text" name="nome" value="${nome!}"><br>
        Mensagem: <input type="text" name="mensagem"><br>
        <button type="submit">Enviar</button>
    </form>
</body>
</html>