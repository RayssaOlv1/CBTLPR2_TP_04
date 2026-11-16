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

## Driver JDBC (DLL)

O projeto disponibilizará a DLL necessária para autenticação integrada do Windows na pasta lib.

Para configurar:
1. Abra as propriedades do projeto no NetBeans.
2. Acesse a seção Run
3. No campo VM Options, informe o caminho da pasta lib:
```bash
-Djava.library.path="CAMINHO_DA_PASTA_DA_DLL\lib"
```
4. Salve as configurações

**Observações:**
- Substitua `CAMINHO_DA_PASTA_DA_DLL` pelo caminho da pasta onde a DLL está armazenada.
- A arquitetura da DLL deve ser compatível com a versão do Java instalado (x64 ou x86).
- Essa configuração é necessária para que o driver JDBC encontre a biblioteca utilizada na autenticação integrada do Windows.

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

