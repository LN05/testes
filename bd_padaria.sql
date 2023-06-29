USE MASTER IF EXISTS(SELECT * FROM SYS.databases WHERE NAME = 'bd_padaria3x')
DROP DATABASE bd_padaria3x
GO

CREATE DATABASE bd_padaria3x
GO

USE bd_padaria3x
GO

CREATE TABLE Usuario
( 
   id            INT IDENTITY,
   nome          VARCHAR(100)	NOT NULL,
   email         VARCHAR(100)	NOT NULL,
   senha         VARCHAR(100)	NOT NULL,
   nivelAcesso   VARCHAR(10)    NULL, -- ADMIN, TECNICO ou USER
   foto			 VARBINARY(MAX) NULL,
   statusUsuario VARCHAR(20)    NOT NULL, -- ATIVO ou INATIVO ou TROCAR_SENHA

   PRIMARY KEY (id)
)

INSERT Usuario (nome, email, senha, nivelAcesso, foto, statusUsuario)
VALUES 
('Ana Sá', 'ana@email.com', '12345678', 'ADMIN', null, 'ATIVO')

INSERT Usuario (nome, email, senha, nivelAcesso, foto, statusUsuario)
VALUES 
('Ordnael Zurc', 'ordnael@email.com', '12345678', 'USER', null, 'ATIVO')

select * from Usuario
