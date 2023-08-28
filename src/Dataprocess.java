// Importar as classes necessárias
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Dataprocess{
    
    // Criar os objetos Connection e Statement como atributos da classe
    private Connection conexao;
    private Statement stmt;

    // Criar um construtor para a classe Dataprocess
    public Dataprocess() {
        try {
            // Conectar ao servidor MySQL
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");

            // Criar um objeto Statement para executar comandos SQL
            stmt = conexao.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void iniciar()
    {
        try {
            // Criar um banco de dados chamado "SGE"
            stmt.executeUpdate("CREATE DATABASE SGE");

            // Selecionar o banco de dados "SGE"
            stmt.execute("USE SGE");

            // Criar uma tabela chamada "Discente" com três colunas: id, nome e idade
            // Usar a opção AUTO_INCREMENT para gerar um id automático para cada aluno
            stmt.executeUpdate("CREATE TABLE Discente (id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(50), idade INT)");

        } catch (Exception e) 
            {
        
                e.printStackTrace();
            }


    } 


    public void setNewaluno(String nome)
    {
        
        try {
            // Selecionar o banco de dados "SGE"
            stmt.execute("USE SGE");
            
            // Inserir o novo aluno na tabela Discente com o nome e a idade
            // Usar a palavra-chave NULL para o id, pois ele será gerado automaticamente pelo MySQL
            stmt.executeUpdate("INSERT INTO Discente VALUES (NULL, '"+nome+"', 25)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAluno()
    {
        try {
            // Selecionar o banco de dados "SGE"
            stmt.execute("USE SGE");
            
            // Consultar os dados da tabela Discente
            ResultSet rs = stmt.executeQuery("SELECT * FROM Discente");
            
            // Exibir os dados da tabela Discente
            System.out.println("Dados da tabela Discente:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nome") + " - " + rs.getInt("idade"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 

