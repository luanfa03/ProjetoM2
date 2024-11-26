package model.dao;

// Importa bibliotecas necessárias para manipulação de banco de dados e o modelo de Paciente
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Paciente;

// Classe que realiza operações no banco de dados relacionadas ao modelo Paciente
public class PacienteDAO {

    // Configurações de conexão com o banco de dados
    private String jdbcURL = "jdbc:mysql://localhost:3306/gerenciamento_pacientes?useTimezone=true&serverTimezone=America/Sao_Paulo";
    private String jdbcUsername = "root"; // Nome de usuário do banco de dados
    private String jdbcPassword = "";     // Senha do banco de dados

    // Comandos SQL usados pela classe
    private static final String INSERT_PACIENTE_SQL = "INSERT INTO paciente (cpf, nome, idade, telefone, descricao) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PACIENTE_BY_CPF_SQL = "SELECT * FROM paciente WHERE cpf = ?";
    private static final String DELETE_PACIENTE_BY_CPF_SQL = "DELETE FROM paciente WHERE cpf = ?";

    // Método para inserir um novo paciente no banco de dados
    public void inserir(Paciente paciente) throws Exception {
        // Carrega o driver JDBC do MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Tenta estabelecer uma conexão e executar a operação
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PACIENTE_SQL);
            preparedStatement.setString(1, paciente.getCpf());  
            preparedStatement.setString(2, paciente.getNome());
            preparedStatement.setInt(3, paciente.getIdade());
            preparedStatement.setString(4, paciente.getTelefone());
            preparedStatement.setString(5, paciente.getDescricao());  
            preparedStatement.executeUpdate(); // Executa o comando SQL
        }
    }

    // Método para consultar um paciente pelo CPF
    public Paciente consultarPorCpf(Paciente paciente) throws Exception {
        // Carrega o driver JDBC do MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Tenta estabelecer uma conexão e executar a consulta
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PACIENTE_BY_CPF_SQL);
            preparedStatement.setString(1, paciente.getCpf());
            ResultSet rs = preparedStatement.executeQuery(); // Executa a consulta SQL
            
            // Verifica se houve resultado
            if (rs.next()) {
                paciente.setNome(rs.getString("nome"));
                paciente.setIdade(rs.getInt("idade"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setDescricao(rs.getString("descricao"));
                return paciente; // Retorna o paciente encontrado
            } else {
                return null; // Retorna null se o paciente não for encontrado
            }
        }
    }

    // Método para excluir um paciente pelo CPF
    public boolean excluirPorCpf(Paciente paciente) throws ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean sucesso = false;

        try {
            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Verifica se o paciente com o CPF informado existe
            String consultaSQL = "SELECT * FROM paciente WHERE cpf = ?";
            stmt = conexao.prepareStatement(consultaSQL);
            stmt.setString(1, paciente.getCpf());
            rs = stmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Paciente não encontrado com o CPF: " + paciente.getCpf());
                return false;
            }

            // Prepara a instrução SQL para deletar o paciente baseado no CPF
            String deleteSQL = "DELETE FROM paciente WHERE cpf = ?";
            stmt = conexao.prepareStatement(deleteSQL);
            stmt.setString(1, paciente.getCpf());

            // Executa a instrução DELETE
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Paciente excluído com sucesso! CPF: " + paciente.getCpf());
                sucesso = true;
            } else {
                System.out.println("Erro ao tentar excluir o paciente com CPF: " + paciente.getCpf());
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao excluir paciente com CPF " + paciente.getCpf() + ": " + ex.getMessage());
        } finally {
            // Fecha os recursos abertos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar os recursos: " + e.getMessage());
            }
        }

        return sucesso;
    }

    // Método para atualizar os dados de um paciente
    public boolean atualizarPaciente(Paciente paciente) throws ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement stmt = null;
        boolean sucesso = false;

        try {
            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Verifica se o paciente com o CPF informado existe
            String consultaSQL = "SELECT * FROM paciente WHERE cpf = ?";
            stmt = conexao.prepareStatement(consultaSQL);
            stmt.setString(1, paciente.getCpf());
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Paciente não encontrado com o CPF: " + paciente.getCpf());
                return false;
            }

            // Prepara a instrução SQL para atualizar o paciente
            String updateSQL = "UPDATE paciente SET nome = ?, idade = ?, telefone = ?, descricao = ? WHERE cpf = ?";
            stmt = conexao.prepareStatement(updateSQL);
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.setString(3, paciente.getTelefone());
            stmt.setString(4, paciente.getDescricao());
            stmt.setString(5, paciente.getCpf());

            // Executa a instrução UPDATE
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Paciente atualizado com sucesso! CPF: " + paciente.getCpf());
                sucesso = true;
            } else {
                System.out.println("Erro ao tentar atualizar o paciente com CPF: " + paciente.getCpf());
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar paciente com CPF " + paciente.getCpf() + ": " + ex.getMessage());
        } finally {
            // Fecha os recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar os recursos: " + e.getMessage());
            }
        }

        return sucesso;
    }
}
