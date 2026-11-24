<header>
    <h2>EstanteWeb</h2>
    <a href="/">Voltar para a página inicial</a>
    <#if usuarioLogado??>
        <span>Olá, ${usuarioLogado.nome}</span>
        <form method="post" action="/logout" style="display: inline;">
            <button type="submit">Sair</button>
        </form>
    <#else>
        <a href="/login">Entrar</a>
    </#if>
</header>
