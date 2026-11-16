IF DB_ID(N'aulajava') IS NULL
    CREATE DATABASE aulajava;
GO

use aulajava;
GO

CREATE TABLE tbcargos (
    cd_cargo INTEGER PRIMARY KEY,
    ds_cargo VARCHAR(100) NOT NULL
);
GO


CREATE TABLE tbfuncs (
    cod_func INTEGER PRIMARY KEY,
    nome_func VARCHAR(100) NOT NULL,
    sal_func NUMERIC(10,2) NOT NULL,
    cod_cargo INTEGER NOT NULL,
    CONSTRAINT fk_func_cargo
        FOREIGN KEY (cod_cargo)
        REFERENCES tbcargos(cd_cargo)
);
GO


INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES
(1, 'Gerente'),
(2, 'Analista'),
(3, 'Programador'),
(4, 'Estagiario'),
(5, 'Assistente');
GO

INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES
(1001, 'Joao Silva', 5500.00, 1),
(1002, 'Maria Santos', 4200.00, 2),
(1003, 'Carlos Oliveira', 3800.00, 3),
(1004, 'Ana Souza', 1800.00, 4),
(1005, 'Pedro Costa', 2500.00, 5),
(1006, 'Lucas Ferreira', 4000.00, 3),
(1007, 'Juliana Lima', 4500.00, 2),
(1008, 'Rafael Mendes', 6000.00, 1);
GO

SELECT * FROM tbcargos;
GO

SELECT * FROM tbfuncs;
GO

SELECT
    f.cod_func,
    f.nome_func,
    f.sal_func,
    c.ds_cargo
FROM tbfuncs f
INNER JOIN tbcargos c
    ON f.cod_cargo = c.cd_cargo
ORDER BY f.cod_func;
GO

