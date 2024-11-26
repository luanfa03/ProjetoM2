CREATE DATABASE gerenciamento_pacientes;

USE gerenciamento_pacientes;

CREATE TABLE Paciente (
    cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT NOT NULL,
    telefone VARCHAR(15),
    descricao TEXT
);
