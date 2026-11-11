<!DOCTYPE html>
<html>
<head><title>Remover sugestão - EstanteWeb</title></head>
<body>
    <#include "cabecalho.ftlh">
    <h1>Confirmar remoção de sugestão</h1>
    <p>${sugestao.nomeCliente} sugeriu: <strong>${sugestao.livroSugerido}</strong></p>
    <form method="post" action="/sugestoes/${sugestao.id?c}/remover">
        <button type="submit">Confirmar remoção</button>
    </form>
    <p><a href="/sugestoes">Desistir</a></p>
</body>
</html>
