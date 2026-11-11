<!DOCTYPE html>
<html>
<head>
    <title>Sugestões - EstanteWeb</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h1>Sugestões de leitura</h1>

    <#if enviada?? && enviada>
        <p style="color: green;">Sugestão enviada com sucesso!</p>
    </#if>

    <ul>
        <#list sugestoes as sugestao>
            <li>${sugestao.nomeCliente} sugeriu: ${sugestao.livroSugerido}</li>
        </#list>
    </ul>
</body>
</html>