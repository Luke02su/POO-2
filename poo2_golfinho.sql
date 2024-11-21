Luke02su
/
POO-2

Type / to search
Code
Issues
Pull requests
Actions
Projects
Wiki
Security
Insights
Settings
Commit fbeb7cc
Preview
Give feedback
Luke02su
Luke02su
authored
8 minutes ago
Verified
Update poo2_golfinho.sql
main
1 parent 
9169601
 commit 
fbeb7cc
File tree
Filter files…
poo2_golfinho.sql
1 file changed
+10
-12
lines changed
Search within code
 
‎poo2_golfinho.sql
+10
-12
Original file line number	Original file line	Diff line number	Diff line change
@@ -1,32 +1,30 @@
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
	porte VARCHAR(50) NOT NULL,
    idpessoa INT NOT NULL,

    INDEX idpessoa_idx (idpessoa),
    
    CONSTRAINT idpessoa_fk FOREIGN KEY (idpessoa) REFERENCES Pessoa(idpessoa) ON UPDATE RESTRICT ON DELETE RESTRICT
);

INSERT INTO pessoa VALUES (NULL, 'Lucas', '2004-08-16');
INSERT INTO pet VALUES (NULL, 'Toto', 'Shitzu', '2020-09-18', 'Branco com preto', 'Pequeno', 1);

SELECT * FROM pessoa;
SELECT * FROM pet;

SELECT * 
FROM pessoa
INNER JOIN pet
ON pet.idpessoa = pessoa.idpessoa;
