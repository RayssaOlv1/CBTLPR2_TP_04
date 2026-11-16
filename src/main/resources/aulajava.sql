IF DB_ID(N'aulajava') IS NULL
    CREATE DATABASE aulajava;
GO

USE aulajava;
GO

IF OBJECT_ID(N'dbo.tblfunc', N'U') IS NOT NULL DROP TABLE dbo.tblfunc;
IF OBJECT_ID(N'dbo.tblcargo', N'U') IS NOT NULL DROP TABLE dbo.tblcargo;
GO

CREATE TABLE dbo.tblcargo (
    cd_cargo SMALLINT NOT NULL PRIMARY KEY,
    ds_cargo VARCHAR(30) NOT NULL
);
GO

CREATE TABLE dbo.tblfunc (
    cd_func INT NOT NULL PRIMARY KEY,
    nome_func VARCHAR(90) NOT NULL,
    sal_func DECIMAL(10, 2) NOT NULL,
    cd_cargo SMALLINT NOT NULL,
    CONSTRAINT fk_tblfunc_tblcargo FOREIGN KEY (cd_cargo) REFERENCES dbo.tblcargo(cd_cargo)
);
GO

INSERT INTO dbo.tblcargo (cd_cargo, ds_cargo) VALUES
(1, 'Administrativo'),
(2, 'Desenvolvedor'),
(3, 'Analista de Sistemas'),
(4, 'Gerente');
GO

INSERT INTO dbo.tblfunc (cd_func, nome_func, sal_func, cd_cargo) VALUES
(1, 'Marcelo Silva', 2000.00, 1),
(2, 'Rayssa Silva', 3500.00, 2),
(3, 'João Victor', 4200.00, 3),
(4, 'Mariana Santos', 5100.00, 4),
(5, 'Marcos Pereira', 2800.00, 1);
GO
