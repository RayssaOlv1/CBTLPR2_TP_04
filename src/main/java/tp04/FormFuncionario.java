package tp04;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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

public class FormFuncionario extends JFrame {

    private final JTextField campoPesquisa = new JTextField();
    private final JTextField campoNome = new JTextField();
    private final JTextField campoSalario = new JTextField();
    private final JTextField campoCargo = new JTextField();
    private final JButton botaoAnterior = new JButton("Anterior");
    private final JButton botaoProximo = new JButton("Próximo");
    private ResultSet resultado;
    private PreparedStatement consulta;
    private Connection conexao;

    public FormFuncionario() {
        super("TRABALHO PRÁTICO 04");
        criarInterface();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 260);
        setResizable(false);
        setLocationRelativeTo(null);
        botaoAnterior.setEnabled(false);
        botaoProximo.setEnabled(false);
    }

    private void criarInterface() {
        JPanel pesquisa = new JPanel(new BorderLayout(5, 5));
        pesquisa.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        pesquisa.add(new JLabel("Nome:"), BorderLayout.WEST);
        pesquisa.add(campoPesquisa, BorderLayout.CENTER);
        JButton botaoPesquisar = new JButton("Pesquisar");
        pesquisa.add(botaoPesquisar, BorderLayout.EAST);

        JPanel dados = new JPanel(new GridLayout(3, 2, 5, 5));
        dados.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        campoNome.setEditable(false);
        campoSalario.setEditable(false);
        campoCargo.setEditable(false);
        dados.add(new JLabel("Nome:"));
        dados.add(campoNome);
        dados.add(new JLabel("Salário:"));
        dados.add(campoSalario);
        dados.add(new JLabel("Cargo:"));
        dados.add(campoCargo);

        JPanel navegacao = new JPanel(new GridLayout(1, 2));
        navegacao.add(botaoAnterior);
        navegacao.add(botaoProximo);

        JPanel principal = new JPanel(new BorderLayout());
        principal.add(pesquisa, BorderLayout.NORTH);
        principal.add(dados, BorderLayout.CENTER);
        principal.add(navegacao, BorderLayout.SOUTH);
        add(principal);

        botaoPesquisar.addActionListener(evento -> pesquisar());
        botaoAnterior.addActionListener(evento -> anterior());
        botaoProximo.addActionListener(evento -> proximo());
    }

    private void pesquisar() {
        fecharConsulta();

        String sql = "SELECT f.nome_func, f.sal_func, c.ds_cargo "
                + "FROM dbo.tbfuncs f "
                + "INNER JOIN dbo.tbcargos c ON c.cd_cargo = f.cod_cargo "
                + "WHERE f.nome_func LIKE ? "
                + "ORDER BY f.cod_func";

        try {
            conexao = Database.conectar();

            consulta = conexao.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            consulta.setString(1, "%" + campoPesquisa.getText().trim() + "%");

            resultado = consulta.executeQuery();

            if (resultado.next()) {
                exibirRegistro();
                atualizarBotoes();
            } else {
                limparDados();
                desabilitarBotoes();

                JOptionPane.showMessageDialog(
                        this,
                        "Nenhum funcionário encontrado.",
                        "Pesquisa",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (SQLException erro) {
            limparDados();
            desabilitarBotoes();

            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível consultar o banco:\n" + erro.getMessage(),
                    "Erro de conexão",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void anterior() {
        try {
            if (resultado != null && resultado.previous()) {
                exibirRegistro();
            }
            atualizarBotoes();
        } catch (SQLException erro) {
            mostrarErro(erro);
        }
    }

    private void proximo() {
        try {
            if (resultado != null && resultado.next()) {
                exibirRegistro();
            }
            atualizarBotoes();
        } catch (SQLException erro) {
            mostrarErro(erro);
        }
    }

    private void exibirRegistro() throws SQLException {
        campoNome.setText(resultado.getString("nome_func"));
        BigDecimal salario = resultado.getBigDecimal("sal_func");
        campoSalario.setText(String.format("R$ %.2f", salario));
        campoCargo.setText(resultado.getString("ds_cargo"));
    }

    private void atualizarBotoes() throws SQLException {
        if (resultado == null || resultado.isBeforeFirst() || resultado.isAfterLast()) {
            desabilitarBotoes();
            return;
        }
        botaoAnterior.setEnabled(!resultado.isFirst());
        botaoProximo.setEnabled(!resultado.isLast());
    }

    private void desabilitarBotoes() {
        botaoAnterior.setEnabled(false);
        botaoProximo.setEnabled(false);
    }

    private void limparDados() {
        campoNome.setText("");
        campoSalario.setText("");
        campoCargo.setText("");
    }

    private void mostrarErro(SQLException erro) {
        JOptionPane.showMessageDialog(this, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void fecharConsulta() {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (consulta != null) {
                consulta.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException erro) {
            mostrarErro(erro);
        } finally {
            resultado = null;
            consulta = null;
            conexao = null;
        }
    }

    @Override
    public void dispose() {
        fecharConsulta();
        super.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormFuncionario().setVisible(true));
    }
}
