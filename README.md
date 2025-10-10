# 🚀 Tarefas API (To-Do List Manager)

Este projeto é uma **API RESTful** completa para um Gerenciador de Tarefas, desenvolvida utilizando **Spring Boot 3** e **Java 17**.

A API utiliza **Spring Data JPA** com **PostgreSQL** para persistência de dados. Inclui a implementação de validações de entrada (`@Valid`, `@NotBlank`, `@Min`/`@Max`) para atender ao requisito de pontuação extra.

### ⚙️ Configuração Principal

* **Tecnologias:** Java 17, Spring Boot 3+, Maven, PostgreSQL.
* **Acesso:** A API está configurada para rodar na porta **8081** e com o caminho de contexto **/tarefas-api**.
* **Entidade:** Possui a entidade única `Tarefa`, com campos para `id`, `descricao` (máx. 100 caracteres, não nulo), `concluida` (padrão `false`), e `prioridade` (não nulo).

### 🧭 Endpoints Implementados

O `TarefaController` implementa todos os 7 endpoints solicitados, seguindo os métodos HTTP e status corretos (como `201 Created` e `404 Not Found`):

* `POST /` : Cria uma nova tarefa.
* `GET /` : Lista todas (suporta filtro por `?descricao=...`).
* `GET /{id}` : Busca tarefa por ID.
* `PUT /{id}` : Atualiza a tarefa completa.
* `DELETE /{id}` : Deleta a tarefa.
* `GET /pendentes` : Lista apenas tarefas não concluídas.
* `PATCH /{id}/concluir` : Marca a tarefa como concluída.

### 🛠️ Como Executar

1.  **Configurar Banco:** Edite `src/main/resources/application.properties` com as suas credenciais do PostgreSQL.
2.  **Build:** `mvn clean package`
3.  **Run:** `java -jar target/tarefas-api-0.0.1-SNAPSHOT.jar`

A API estará acessível em `http://localhost:8081/tarefas-api/`.

### 👥 Integrantes

* Erick Almeida Iarenczuk
* Kauã Hilgenberg
* Gustavo Alves Zanon Ricardo