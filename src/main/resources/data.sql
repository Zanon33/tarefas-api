TRUNCATE TABLE tarefa RESTART IDENTITY CASCADE;

INSERT INTO tarefa (descricao, concluida, prioridade) VALUES ('Estudar Spring Boot', false, 1);
INSERT INTO tarefa (descricao, concluida, prioridade) VALUES ('Fazer o relatório do projeto', false, 2);
INSERT INTO tarefa (descricao, concluida, prioridade) VALUES ('Comprar pão', true, 5);

INSERT INTO tarefa (descricao, concluida, prioridade) VALUES ('Estudar validações com @Valid', false, 3);
INSERT INTO tarefa (descricao, concluida, prioridade) VALUES ('Testar endpoint PATCH /{id}/concluir', false, 4);