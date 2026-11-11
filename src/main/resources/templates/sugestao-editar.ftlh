<!DOCTYPE html>
<html>
<head><title>Corrigir sugestão - EstanteWeb</title></head>
<body>
    <#include "cabecalho.ftlh">
    <h1>Corrigir sugestão</h1>
    <#if erro??><p style="color: red;">${erro}</p></#if>
    <form method="post" action="/sugestoes/${sugestao.id?c}/editar">
        Nome: <input type="text" name="nomeCliente" value="${nomeInformado!sugestao.nomeCliente}"><br>
        Livro sugerido: <input type="text" name="livroSugerido" value="${livroInformado!sugestao.livroSugerido}"><br>
        <button type="submit">Salvar correção</button>
    </form>
    <p><a href="/sugestoes">Cancelar</a></p>
</body>
</html>
