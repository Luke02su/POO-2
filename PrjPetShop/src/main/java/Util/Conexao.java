package Util; // Define o pacote onde esta classe está localizada.

import java.sql.Connection; // Importa a classe Connection para estabelecer uma conexão com o banco de dados.
import java.sql.DriverManager; // Importa DriverManager para gerenciar a conexão com o banco de dados.
import java.sql.SQLException; // Importa SQLException para lidar com possíveis erros na conexão.

public class Conexao { // Declara a classe Conexao, responsável por criar a conexão com o banco de dados.
    
    final private String driver="org.postgresql.Driver"; // Define o driver do PostgreSQL, necessário para a conexão.
    final private String url="jdbc:postgresql://localhost:5432/" + "bd_petshop"; // Define a URL do banco de dados incluindo o nome do banco.
    
    final private String usuario="postgres"; // Nome de usuário para conectar ao banco de dados.
    final private String senha="123456"; // Senha para o usuário do banco de dados.

    // Método que tenta estabelecer e retornar uma conexão com o banco de dados.
    public Connection conectar() {
        Connection conn = null; // Declara a variável conn, inicializando-a como null.

        try {
            Class.forName(driver); // Carrega o driver do PostgreSQL. Necessário para algumas versões de JDBC.
            
            // Estabelece a conexão com o banco de dados usando a URL, usuário e senha definidos.
            conn = DriverManager.getConnection(url, usuario, senha);
        } catch(ClassNotFoundException ex) { // Captura exceções de classe não encontrada, caso o driver não seja encontrado.
            ex.printStackTrace(); // Imprime o stack trace do erro para ajudar no diagnóstico.
        } catch(SQLException ex) { // Captura exceções de SQL, caso ocorra algum erro de conexão.
            ex.printStackTrace(); // Imprime o stack trace do erro SQL para ajudar no diagnóstico.
        }
        
        // Retorna a variável "conn", que contém a conexão com o banco de dados, ou null se houve erro.
        return conn;   
    }
}
