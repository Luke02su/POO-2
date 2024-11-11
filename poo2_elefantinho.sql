CREATE TABLE Pessoa (
	idpessoa SERIAL PRIMARY KEY,
    nome VARCHAR(35) NOT NULL,
    data_nascimento DATE NOT NULL
);

CREATE TABLE Pet (
    idpet SERIAL PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    raca VARCHAR(50) NOT NULL,
    data_nascimento DATE NOT NULL,
    cor VARCHAR(50) NOT NULL,
    idpessoa INT NOT NULL,
    
    CONSTRAINT fk_idpessoa FOREIGN KEY (idpessoa) REFERENCES Pessoa(idpessoa) ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE INDEX idpessoa_idx ON Pessoa (idpessoa);

INSERT INTO pessoa VALUES (default, 'Lucas', '2004-08-16');
INSERT INTO pet VALUES (default, 'Toto', 'Shitzu', '2020-09-18', 'Branco com preto', 1);

SELECT * FROM pessoa;
SELECT * FROM pet;

SELECT * 
FROM pessoa
INNER JOIN pet
ON pet.idpessoa = pessoa.idpessoa;
