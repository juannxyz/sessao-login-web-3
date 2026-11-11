<!DOCTYPE html>
<html>
<head><title>Remover livro - EstanteWeb</title></head>
<body>
    <#include "cabecalho.ftlh">
    <h1>Confirmar remoção</h1>
    <p>Você está prestes a remover <strong>${livro.titulo}</strong>, de ${livro.autor}.</p>
    <p>Esta ação não pode ser desfeita.</p>
    <form method="post" action="/livros/${livro.id?c}/remover">
        <button type="submit">Confirmar remoção</button>
    </form>
    <p><a href="/livros/${livro.id?c}">Desistir e voltar à ficha</a></p>
</body>
</html>
