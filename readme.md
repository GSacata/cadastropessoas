# Cadastro de Pessoas
Este é um desafio que consiste em construir um sistema SPA responsivo de visualização e cadastro de pessoas físicas e jurídicas.

## Informações
Nome: Gabriel Sacata Aranda
Versão: 1.0.0 (2025-08-20)

## Tecnologias
Backend: Java e ecossistema Spring
Frontend: Vue
Database: H2 (In-Memory)

## Principais funcionalidades
1. Uma página principal com uma tabela de pessoas cadastradas
2. Um componente que consiste em um formulário para adição, edição e exclusão de pessoas.
3. Um modal de confirmação de exclusão.

## Instalação do projeto
1. Certifique-se de ter instalado em seu ambiente os seguintes requisitos:
    1.1. Java 17 ou maior (`java -v`, `java --version` ou correspondente);
    1.2. NPM 11 ou maior (`node -v`, `node --version` ou correspondente);
    1.3. Apache Maven 3 ou maior (`mvn -v`,`mvn --version` ou correspondente);
    1.4. (Opcional) Para testes com a API, instale Postman;
2. Certifique-se de que as portas `8080` e `7070` estarão livres, pois serão usadas para subir o ambiente do projeto.
3. Faça download do `.zip` encontrado neste repositório, e descompacte em um diretório de sua preferência.
4. Em seu terminal, navegue até a pasta de instalação do projeto.
5. Via terminal, entre na pasta "backend" e rode o seguinte comando: `mvn install`.
    5.1. O mesmo instalará as dependências do Spring.
    5.2. Verifique se ao final do processo, aparecerá uma mensagem como `BUILD SUCCESS`.
6. Inicie o backend com o seguinte comando: `mvn spring-boot:run`
7. Em outra guia ou janela no terminal, entre na pasta "frontend" e rode o seguinte comando: `npm install`.
    7.1. O mesmo instalará as dependências do Vue.
8. Ainda na pasta "frontend", rode o comando `npm run build`.
9. Rode o comando `npm run preview`. Ao acessar o endereço http://localhost:7070/, o projeto deve estar em funcionamento.

## Notas
1. Para testar via API, importe o arquivo `postman_collection.json` em anexo.
    1.1. O endpoint `/quick-start` popula o banco com algumas entradas, e é destinado apenas para testes.
2. Para testar a responsividade, use o modo "Inspecionar" de seu navegador.
3. Pelo banco ser "in-memory", cada vez que interromper os serviços do backend, o mesmo será apagado.

## Créditos
Fonte: "Figtree" por Erik Kennedy, https://fonts.google.com/specimen/Figtree?query=figtree
Ícones: Google Icons, https://fonts.google.com/icons