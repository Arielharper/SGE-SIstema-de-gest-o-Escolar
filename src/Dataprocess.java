import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dataprocess {
    private Connection conexao;
    private PreparedStatement preparedStatement;

    public Dataprocess() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void iniciar() {
        try {
            preparedStatement = conexao.prepareStatement("CREATE DATABASE IF NOT EXISTS SGE");
            preparedStatement.executeUpdate();
            preparedStatement.execute("USE SGE");

            criarTabelaDiscente();
            criarTabelaProfessor();
            criarTabelaDisciplina();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void criarTabelaDiscente() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS Discente (id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(50), idade INT)";
        executarQuery(query);
    }

    private void criarTabelaProfessor() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS Professor (id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(50), cpf VARCHAR(15), departamento VARCHAR(50))";
        executarQuery(query);
    }

    private void criarTabelaDisciplina() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS Disciplina (id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(50), professor_id INT, " +
                "FOREIGN KEY (professor_id) REFERENCES Professor(id))";
        executarQuery(query);
    }

    private void executarQuery(String query) throws SQLException {
        preparedStatement = conexao.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public void setNewAluno(String nome, int idade) {
        try {
            String query = "INSERT INTO Discente (nome, idade) VALUES (?, ?)";
            preparedStatement = conexao.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, idade);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setNewProfessor(String nome, String cpf, String departamento) {
        try {
            String query = "INSERT INTO Professor (nome, cpf, departamento) VALUES (?, ?, ?)";
            preparedStatement = conexao.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cpf);
            preparedStatement.setString(3, departamento);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setNewDisciplina(String nome, int professorId) {
        try {
            String query = "INSERT INTO Disciplina (nome, professor_id) VALUES (?, ?)";
            preparedStatement = conexao.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, professorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String consultarPorCodigo(int codigo) {
        StringBuilder resultado = new StringBuilder();
        try {
            preparedStatement = conexao.prepareStatement("USE SGE");
    
            String queryDiscente = "SELECT * FROM Discente WHERE id = ?";
            String queryProfessor = "SELECT * FROM Professor WHERE id = ?";
            String queryDisciplina = "SELECT * FROM Disciplina WHERE id = ?";
    
            // Consultar tabela Discente
            consultarRegistro(resultado, queryDiscente, codigo, "Aluno");
    
            // Consultar tabela Professor
            consultarRegistro(resultado, queryProfessor, codigo, "Professor");
    
            // Consultar tabela Disciplina
            consultarRegistro(resultado, queryDisciplina, codigo, "Disciplina");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }
    
    private void consultarRegistro(StringBuilder resultado, String query, int codigo, String tipo) throws SQLException {
        preparedStatement = conexao.prepareStatement(query);
        preparedStatement.setInt(1, codigo);
        ResultSet rs = preparedStatement.executeQuery();
    
        while (rs.next()) {
            resultado.append(tipo).append(":\n");
            resultado.append(rs.getInt("id")).append(" - ").append(rs.getString("nome"));
    
            if (tipo.equals("Aluno")) {
                resultado.append(" - ").append(rs.getInt("idade"));
            } else if (tipo.equals("Professor")) {
                resultado.append(" - ").append(rs.getString("cpf")).append(" - ").append(rs.getString("departamento"));
            } else if (tipo.equals("Disciplina")) {
                resultado.append(" - Professor ID: ").append(rs.getInt("professor_id"));
            }
    
            resultado.append("\n");
        }
    }
    

    public String consultarPorNome(String nome) {
        StringBuilder resultado = new StringBuilder();
        try {
            preparedStatement = conexao.prepareStatement("USE SGE");
    
            String queryDiscente = "SELECT * FROM Discente WHERE nome LIKE ?";
            String queryProfessor = "SELECT * FROM Professor WHERE nome LIKE ?";
            String queryDisciplina = "SELECT * FROM Disciplina WHERE nome LIKE ?";
    
            // Consultar tabela Discente
            consultarRegistrosPorNome(resultado, queryDiscente, nome, "Aluno");
    
            // Consultar tabela Professor
            consultarRegistrosPorNome(resultado, queryProfessor, nome, "Professor");
    
            // Consultar tabela Disciplina
            consultarRegistrosPorNome(resultado, queryDisciplina, nome, "Disciplina");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }
    
    private void consultarRegistrosPorNome(StringBuilder resultado, String query, String nome, String tipo) throws SQLException {
        preparedStatement = conexao.prepareStatement(query);
        preparedStatement.setString(1, "%" + nome + "%");
        ResultSet rs = preparedStatement.executeQuery();
    
        while (rs.next()) {
            resultado.append(tipo).append(":\n");
            resultado.append(rs.getInt("id")).append(" - ").append(rs.getString("nome"));
    
            if (tipo.equals("Aluno")) {
                resultado.append(" - ").append(rs.getInt("idade"));
            } else if (tipo.equals("Professor")) {
                resultado.append(" - ").append(rs.getString("cpf")).append(" - ").append(rs.getString("departamento"));
            } else if (tipo.equals("Disciplina")) {
                resultado.append(" - Professor ID: ").append(rs.getInt("professor_id"));
            }
    
            resultado.append("\n");
        }
    }
    

    public String getAlunos() {
        StringBuilder resultado = new StringBuilder();
        try {
            String query = "SELECT * FROM Discente";
            preparedStatement = conexao.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            resultado.append("Alunos:\n");
            while (rs.next()) {
                resultado.append(rs.getInt("id")).append(" - ").append(rs.getString("nome")).append(" - ").append(rs.getInt("idade")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }

    public String getProfessores() {
        StringBuilder resultado = new StringBuilder();
        try {
            String query = "SELECT * FROM Professor";
            preparedStatement = conexao.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            resultado.append("Professores:\n");
            while (rs.next()) {
                resultado.append(rs.getInt("id")).append(" - ").append(rs.getString("nome")).append(" - ").append(rs.getString("cpf"))
                        .append(" - ").append(rs.getString("departamento")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }

    public String getDisciplinas() {
        StringBuilder resultado = new StringBuilder();
        try {
            String query = "SELECT * FROM Disciplina";
            preparedStatement = conexao.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            resultado.append("Disciplinas:\n");
            while (rs.next()) {
                resultado.append(rs.getInt("id")).append(" - ").append(rs.getString("nome")).append(" - Professor ID: ").append(rs.getInt("professor_id")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }

    public void fecharConexao() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
