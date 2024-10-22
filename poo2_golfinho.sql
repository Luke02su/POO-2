CREATE SCHEMA bd_petshop;
USE bd_petshop;

CREATE TABLE Pessoa (
	idpessoa INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(35) NOT NULL,
    data_nascimento DATE NOT NULL
);

CREATE TABLE Pet (
	idpet INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    raca VARCHAR(50) NOT NULL,
	data_nascimento DATE NOT NULL,
    cor VARCHAR(50) NOT NULL,
    idpessoa INT NOT NULL,
    
    INDEX idpessoa_idx (idpessoa),
    
    CONSTRAINT idpessoa_fk FOREIGN KEY (idpessoa) REFERENCES Pessoa(idpessoa) ON UPDATE RESTRICT ON DELETE RESTRICT
);

INSERT INTO pessoa VALUES (NULL, 'Lucas', '2004-08-16');
INSERT INTO pet VALUES (NULL, 'Toto', 'Shitzu', '2020-09-18', 'Branco com preto', 1);

SELECT * FROM pessoa;
SELECT * FROM pet;

SELECT * 
FROM pessoa
INNER JOIN pet
ON pet.idpessoa = pessoa.idpessoa;