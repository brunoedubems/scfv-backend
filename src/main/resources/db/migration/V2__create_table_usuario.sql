CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nis VARCHAR(11) UNIQUE NOT NULL,
    rg VARCHAR(20),
    sexo VARCHAR(10),
    telefone VARCHAR(20),
    nome_mae VARCHAR(100),
    nome_responsavel VARCHAR(100),
    prioritario BOOLEAN NOT NULL DEFAULT FALSE,
    grupo_id BIGINT,
    CONSTRAINT fk_usuario_grupo FOREIGN KEY (grupo_id) REFERENCES grupo(id)
);
