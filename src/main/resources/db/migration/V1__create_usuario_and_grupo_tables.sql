-- Criação da tabela GRUPO
CREATE TABLE grupo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    tecnico VARCHAR(255),
    faixa_etaria VARCHAR(255)
);

-- Criação da tabela USUARIO
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
    prioritario BOOLEAN,
    group_id BIGINT,
    CONSTRAINT fk_usuario_grupo FOREIGN KEY (group_id) REFERENCES grupo(id)
);
