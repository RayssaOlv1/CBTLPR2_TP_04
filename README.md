# TP04 – Aula Java

**Dupla:** João Victor Lima Venceslau e Rayssa Silva de Oliveira

## Descrição

Projeto desenvolvido em Java utilizando Swing e JDBC para realizar operações de consulta e navegação de registros de funcionários armazenados em um banco de dados SQL Server.

## Tecnologias utilizadas

- Java 8
- Maven
- SQL Server
- JDBC
- Java Swing

## Configuração do banco de dados

1. Abra o SQL Server Management Studio (SSMS).
2. Execute o script `src/main/resources/aulajava.sql`.
3. O script cria o banco de dados `aulajava`, suas tabelas e insere os registros necessários para os testes.

## Configuração da conexão

O projeto utiliza autenticação integrada do Windows.

A conexão está configurada para:

- Servidor: `localhost`
- Porta: `1433`
- Banco de dados: `aulajava`
- Autenticação: Integrada do Windows

Caso necessário, ajuste a URL de conexão no arquivo `Database.java` conforme a configuração do SQL Server.

## Como executar

1. Abra o projeto em uma IDE compatível com Maven, como NetBeans.
2. Aguarde o carregamento das dependências.
3. Verifique se o SQL Server está em execução e se o banco de dados foi criado.
4. Execute a classe `tp04.Main`.

## Funcionalidades

- Pesquisa de funcionários utilizando SQL `LIKE`.
- Consultas com `PreparedStatement`.
- Exibição dos registros em interface gráfica.
- Navegação entre registros pelos botões **Próximo** e **Anterior**.

# Videos Demonstrativos

https://github.com/user-attachments/assets/3ad8cf2e-3c76-4ebb-97f1-a2079f05647b



https://github.com/user-attachments/assets/ef5538fd-8fa1-4dca-a680-9cc15d536f2d

