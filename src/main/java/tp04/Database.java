package tp04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Dupla: João Victor Lima Venceslau - Rayssa Silva de Oliveira
 * 
 1. Elabore um programa em Java que apresente um frame semelhante ao que
se segue:

2. Deverá criar um banco SQL Server chamado aulajava com as tabelas
conforme esquema abaixo:

3. Acrescente alguns registros, respeitando as chaves e o relacionamento;
4. Estabeleça a conexão utilizando o JDBC;
5. Ao clicar no botão Pesquisar, deverá ser efetuado o select (utilize like) para
“preencher” um recordset e PreparedStatement para fazer o SQL.
6. Os botões Próximo e Anterior devem permitir a navegação pelo recordset
até os limites inicial e final.
 */

public final class Database {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=aulajava;encrypt=true;trustServerCertificate=true";
    private static final String USER = "teste";
    private static final String PASSWORD = "Teste@12345";

    private Database() {
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
