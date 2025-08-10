-- Inserindo grupos
INSERT INTO grupo (id, nome, tecnico, faixa_etaria) VALUES
(1, 'Grupo Esportivo', 'Ana Martins', '15-20 anos'),
(2, 'Grupo Cultural', 'Carlos Souza', '10-15 anos');

-- Inserindo usuários
INSERT INTO usuario (
    id, nome, data_nascimento, cpf, nis, rg, sexo, telefone, nome_mae, nome_responsavel, prioritario, group_id
) VALUES
(1, 'Lucas Pereira', '2005-04-10', '12345678901', '98765432100', 'MG1234567', 'M', '31999998888', 'Maria Pereira', 'João Pereira', true, 1),
(2, 'Fernanda Silva', '2006-08-22', '10987654321', '12345098765', 'SP7654321', 'F', '31988887777', 'Ana Silva', 'Carlos Silva', false, 1),
(3, 'Marcos Oliveira', '2010-01-15', '32165498700', '56789012345', 'RJ1122334', 'M', '21977776666', 'Clara Oliveira', 'Pedro Oliveira', false, 2),
(4, 'Juliana Mendes', '2011-03-30', '45678912300', '67890123456', 'BA5566778', 'F', '71966665555', 'Lucia Mendes', 'Rafael Mendes', true, 2);
