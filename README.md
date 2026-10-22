# Finance Flow API

API REST para gerenciamento de usuários, categorias e transações financeiras. O projeto utiliza Java 17, Spring Boot, Spring Data JPA e PostgreSQL.

## Funcionalidades

- Cadastro, consulta, atualização e remoção de usuários.
- Cadastro, listagem, atualização e remoção de categorias.
- Cadastro, consulta, atualização e remoção de transações.
- Validação dos dados recebidos nos endpoints.

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA / Hibernate
- PostgreSQL 17
- Maven Wrapper

## Pré-requisitos

- JDK 17 ou superior
- Docker e Docker Compose, para iniciar o banco localmente

## Como executar

1. Inicie o PostgreSQL com Docker Compose:

   ```bash
   docker compose up -d
   ```

   O banco será criado com as configurações locais abaixo:

   | Configuração  | Valor         |
   | ------------- | ------------- |
   | Banco         | `financeflow` |
   | Usuário       | `postgres`    |
   | Senha         | `postgres`    |
   | Porta no host | `5433`        |

2. Inicie a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

   A API ficará disponível em `http://localhost:8080`.

3. Para executar os testes:

   ```bash
   ./mvnw test
   ```

> As credenciais acima são valores de desenvolvimento definidos no projeto. Altere-as antes de utilizar a aplicação em outro ambiente.

## Endpoints

Os endpoints abaixo refletem os mapeamentos atualmente presentes nos controllers. Os prefixos não são uniformes: usuários e categorias usam `/api/v1`, enquanto transações usam `/v1`.

### Usuários

| Método   | Rota                    | Descrição                                                                    |
| -------- | ----------------------- | ---------------------------------------------------------------------------- |
| `POST`   | `/api/v1/usuarios`      | Cadastra um usuário (`201 Created`)                                          |
| `GET`    | `/api/v1/usuarios`      | Lista usuários                                                               |
| `PUT`    | `/api/v1/usuarios/{id}` | Atualiza nome e e-mail                                                       |
| `DELETE` | `/api/v1/usuarios`      | Rota declarada para remoção; veja a observação sobre o parâmetro `id` abaixo |

Exemplo de cadastro:

```json
{
  "nome": "Ana Silva",
  "email": "ana@example.com",
  "senha": "senha1234"
}
```

### Categorias

| Método   | Rota                      | Descrição                               |
| -------- | ------------------------- | --------------------------------------- |
| `POST`   | `/api/v1/categorias`      | Cadastra uma categoria (`201 Created`)  |
| `GET`    | `/api/v1/categorias`      | Lista categorias                        |
| `PUT`    | `/api/v1/categorias/{id}` | Atualiza uma categoria                  |
| `DELETE` | `/api/v1/categorias/{id}` | Remove uma categoria (`204 No Content`) |

Exemplo de cadastro:

```json
{
  "nome": "Salário",
  "tipo": "RECEITA",
  "usuarioId": 1
}
```

### Transações

| Método   | Rota                  | Descrição                                                                        |
| -------- | --------------------- | -------------------------------------------------------------------------------- |
| `POST`   | `/v1/transacoes`      | Cadastra uma transação (`201 Created`)                                           |
| `GET`    | `/v1/transacoes`      | Lista transações                                                                 |
| `GET`    | `/v1/transacoes/{id}` | Busca uma transação pelo ID                                                      |
| `PUT`    | `/v1/transacoes`      | Rota declarada para atualização; veja a observação sobre o parâmetro `id` abaixo |
| `DELETE` | `/v1/transacoes`      | Rota declarada para remoção; veja a observação sobre o parâmetro `id` abaixo     |

Exemplo de cadastro:

```json
{
  "descricao": "Conta de luz",
  "valor": 150.75,
  "tipo": "DESPESA",
  "data": "2026-10-21",
  "categoriaId": 2,
  "usuarioId": 1
}
```

O campo `tipo` aceita `RECEITA` ou `DESPESA`. O valor precisa ser maior que zero e a data deve estar no formato `AAAA-MM-DD`.

## Observação sobre rotas com ID

No código atual, os métodos `DELETE` de usuários e `PUT`/`DELETE` de transações recebem um `id` como parâmetro, mas suas rotas não incluem `/{id}`. Para que essas operações possam receber o ID pela URL, os mapeamentos precisam incluir esse segmento, como `/api/v1/usuarios/{id}` e `/v1/transacoes/{id}`. As tabelas mostram as rotas atualmente declaradas, não uma correção aplicada no código.

## Banco de dados

A configuração local está em `src/main/resources/application.yaml`. O Hibernate está configurado com `ddl-auto: update`, que atualiza o esquema automaticamente ao iniciar a aplicação. Essa configuração é voltada ao desenvolvimento; para produção, prefira gerenciar o esquema com migrações.

## Licença

Nenhuma licença foi definida no projeto até o momento.
