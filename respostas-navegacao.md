# Respostas — Navegação entre Recursos

1. O id identifica qual livro é o recurso solicitado, então faz parte do caminho (`/livros/12`). Assim o endereço é direto, fácil de compartilhar e salvar nos favoritos. Uma query string é mais adequada a opções que alteram a apresentação da listagem.

2. Sem o tratamento de ausência, um id numérico inexistente faz o repositório retornar `null`; tentar renderizar a ficha sem um objeto válido causa erro no template e uma resposta de erro do servidor (normalmente 500). Com o tratamento, a resposta adequada é 404: a URL está bem formada, mas não há livro com aquele id.

3. `/livros/abc` não contém um id que possa ser convertido para `Long`. O Spring rejeita o parâmetro durante a resolução da rota, antes de executar o corpo do método, e responde 400 (Bad Request). É diferente do 404: nesse caso, o pedido está malformado; no `/livros/999`, o pedido é válido, mas o recurso não existe.

4. Sem formatação explícita, o FreeMarker pode formatar um id numérico conforme a localidade, usando vírgula como separador decimal (por exemplo, `1,998`). Isso ocorre em valores com parte fracionária ou em certos formatos numéricos; ids inteiros pequenos podem parecer corretos, escondendo o problema. Usar `?c` gera uma representação adequada para URL, como `/livros/1998`.

5. O id identifica o recurso, então fica no caminho: `/livros/12`. Já `ordem` configura a forma de exibir a coleção e é uma opção da consulta, então fica na query string: `/livros/html?ordem=preco`. Assim, a mesma listagem pode ser exibida com diferentes ordenações sem mudar a identidade dos livros.
