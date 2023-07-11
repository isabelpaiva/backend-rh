# HR System :coffee:
Este repositório contém um projeto de uma API REST construído usando Java Spring.

## Tabela de Conteúdos

- [Instalação](#instalação)
- [Configuração](#configuração)
- [API Endpoints](#api-endpoints)
- [Banco de Dados](#banco-de-dados)

## Instalação

1. Clone o repositório:

```bash
$ git clone git@github.com:isabelpaiva/backend-rh.git
```

2. Instale as dependências com o Maven

## Configuração

1. Inicie a aplicação com o Maven
2. A API estará acessível em http://localhost:8080


## API Endpoints
A API fornece os seguintes endpoints:

```markdown
POST /start - Cadastra um candidato.

POST /schedule - Marca uma entrevista.

POST /disqualify - Desqualifica um candidato.

POST /approve - Aprova um candidato.

GET /candidate{id} - Lista um candidato.

GET /approved - Lista todos os aprovados.

GET /candidates - Lista todos os candidatos.
```

## Banco de Dados

O projeto utiliza o PostgresSQL como banco de dados. As migrações de banco de dados necessárias são gerenciadas usando o Flyway.

Para instalar o PostgresSQL localmente, você pode [clicar aqui](https://www.postgresql.org/download/).
