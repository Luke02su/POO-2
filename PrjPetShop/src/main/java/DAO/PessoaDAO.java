package DAO; // Define o pacote onde a classe está localizada.

import Objetos.Pessoa; // Importa a classe Pessoa que representa a entidade.
import Util.Conexao; // Importa a classe Conexao, que contém o método para conectar ao banco de dados.
import Util.ManipulaData; // Importa a classe ManipulaData, que ajuda a formatar datas.
import java.sql.Connection; // Importa Connection para gerenciar a conexão com o banco de dados.
import java.sql.PreparedStatement; // Importa PreparedStatement para preparar e executar instruções SQL.
import java.sql.ResultSet; // Importa ResultSet para manipular o resultado de consultas SQL.
import java.sql.SQLException; // Importa SQLException para tratar erros relacionados ao banco de dados.
import java.util.ArrayList; // Importa ArrayList para criar listas.
import java.util.List; // Importa List para definir o tipo da lista.

public class PessoaDAO { // Declara a classe PessoaDAO que é responsável pelas operações de banco de dados da entidade Pessoa.
    Connection conn; // Declara a variável conn para armazenar a conexão com o banco de dados.
    ManipulaData md; // Declara a variável md para manipular datas.

    // Construtor da classe PessoaDAO, inicializando a conexão e o manipulador de datas.
    public PessoaDAO() {
        conn = new Conexao().conectar(); // Estabelece a conexão com o banco usando a classe Conexao.
        md = new ManipulaData(); // Inicializa o objeto ManipulaData.
    }
 
    // Método para salvar um novo registro de Pessoa no banco de dados.
    public Pessoa salvar(Pessoa p) {
        try {
            // Prepara o comando SQL para inserir uma nova pessoa na tabela.
            System.out.println(md.string2Date(p.getData())); // Converte e exibe a data no formato SQL.
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO pessoa (nome, data_nascimento) VALUES (?, ?)");
            stmt.setString(1, p.getNome()); // Atribui o valor do nome ao primeiro parâmetro.
            stmt.setDate(2, md.string2Date(p.getData())); // Converte e atribui a data de nascimento ao segundo parâmetro.
            stmt.execute(); // Executa o comando SQL.

            ResultSet rs = stmt.getGeneratedKeys(); // Obtém as chaves geradas pelo banco (caso de chave primária autoincrementada).
            if (rs.next()) {
                p.setIdpessoa(rs.getInt("idpessoa")); // Define o ID da pessoa com o valor gerado.
            } else {
                p.setIdpessoa(-1); // Define o ID como -1 se nenhuma chave foi gerada.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro para facilitar a depuração.
        }
        return p; // Retorna o objeto Pessoa com o ID atualizado.
    }
    
    // Método para atualizar informações de uma Pessoa existente no banco de dados.
    public void editar(Pessoa p) {
        try {
            // Prepara o comando SQL para atualizar o nome e a data de nascimento de uma pessoa específica.
            PreparedStatement stmt = conn.prepareStatement("UPDATE pessoa SET nome = ?, data_nascimento = ? WHERE idpessoa = ?");
            stmt.setString(1, p.getNome()); // Define o valor do nome.
            stmt.setDate(2, md.string2Date(p.getData())); // Define a data de nascimento.
            stmt.setInt(3, p.getIdpessoa()); // Define o ID da pessoa a ser atualizada.
            stmt.executeUpdate(); // Executa o comando de atualização.
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
    }
    
    // Método para excluir uma Pessoa do banco de dados.
    public int excluir(Pessoa p) {
        int verif = 0; // Variável para verificar se a exclusão foi bem-sucedida.
        try {
            // Prepara o comando SQL para excluir uma pessoa específica.
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM pessoa WHERE idpessoa = ?");
            stmt.setInt(1, p.getIdpessoa()); // Define o ID da pessoa a ser excluída.
            verif = stmt.executeUpdate(); // Executa o comando de exclusão e retorna o número de linhas afetadas.
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return verif; // Retorna o número de linhas excluídas (0 se não houver nenhuma).
    }
    
    // Método para retornar uma lista de todas as pessoas no banco de dados.
    public List<Pessoa> getPessoas() {
        List<Pessoa> lstP = new ArrayList<>(); // Cria uma lista para armazenar as pessoas.
        ResultSet rs; // Declara o ResultSet para armazenar o resultado da consulta.

        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pessoa"); // Prepara o comando SQL de consulta.
            rs = ppStmt.executeQuery(); // Executa a consulta e armazena o resultado no ResultSet.
            
            while (rs.next()) { // Itera sobre o resultado da consulta.
                lstP.add(getPessoa(rs)); // Adiciona cada pessoa na lista.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return lstP; // Retorna a lista de pessoas.
    }
    
    // Método sobrecarregado para retornar uma lista de pessoas que correspondem a um filtro.
    public List<Pessoa> getPessoas(Pessoa p) {
        List<Pessoa> lstP = new ArrayList<>(); // Cria uma lista para armazenar as pessoas filtradas.
        ResultSet rs; // Declara o ResultSet para armazenar o resultado da consulta.

        try {
            // Prepara o comando SQL para buscar pessoas com nome que começam com o nome informado.
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pessoa WHERE nome ILIKE ?");
            ppStmt.setString(1, p.getNome() + "%"); // Define o valor para o filtro de nome.
            rs = ppStmt.executeQuery(); // Executa a consulta e armazena o resultado no ResultSet.
            
            while (rs.next()) { // Itera sobre o resultado da consulta.
                lstP.add(getPessoa(rs)); // Adiciona cada pessoa na lista.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return lstP; // Retorna a lista de pessoas filtradas.
    }
    
    
    public List<Pessoa> getPessoas(String nome) {
        List<Pessoa> lstP = new ArrayList<>(); // Cria uma lista para armazenar as pessoas filtradas.
        ResultSet rs; // Declara o ResultSet para armazenar o resultado da consulta.

        try {
            // Prepara o comando SQL para buscar pessoas com nome que começam com o nome informado.
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pessoa WHERE nome ILIKE ?");
            ppStmt.setString(1, nome+ "%"); // Define o valor para o filtro de nome.
            rs = ppStmt.executeQuery(); // Executa a consulta e armazena o resultado no ResultSet.
            while (rs.next()) { // Itera sobre o resultado da consulta.
                lstP.add(getPessoa(rs)); // Adiciona cada pessoa na lista.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return lstP; // Retorna a lista de pessoas filtradas.
    }
    
        public List<Pessoa> getPessoas(String nome, String dataInicio, String dataFim) {
        List<Pessoa> lstP = new ArrayList<>(); // Cria uma lista para armazenar as pessoas filtradas.
        ResultSet rs; // Declara o ResultSet para armazenar o resultado da consulta.

        try {
            // Prepara o comando SQL para buscar pessoas com nome que começam com o nome informado.
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pessoa WHERE nome ILIKE ? AND data_nascimento BETWEEN ? AND ?");
            ppStmt.setString(1, nome+ "%"); // Define o valor para o filtro de nome.
            ppStmt.setDate(2, md.string2Date(dataInicio));
            ppStmt.setDate(3, md.string2Date(dataFim));
            rs = ppStmt.executeQuery(); // Executa a consulta e armazena o resultado no ResultSet.
            while (rs.next()) { // Itera sobre o resultado da consulta.
                lstP.add(getPessoa(rs)); // Adiciona cada pessoa na lista.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return lstP; // Retorna a lista de pessoas filtradas.
    }
        
    public List<Pessoa> getPessoas(String dataInicio, String dataFim) {
        List<Pessoa> lstP = new ArrayList<>(); // Cria uma lista para armazenar as pessoas filtradas.
        ResultSet rs; // Declara o ResultSet para armazenar o resultado da consulta.

        try {
            // Prepara o comando SQL para buscar pessoas com nome que começam com o nome informado.
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pessoa WHERE data_nascimento BETWEEN ? AND ?");
            ppStmt.setDate(1, md.string2Date(dataInicio));
            ppStmt.setDate(2, md.string2Date(dataFim));
            rs = ppStmt.executeQuery(); // Executa a consulta e armazena o resultado no ResultSet.
            while (rs.next()) { // Itera sobre o resultado da consulta.
                lstP.add(this.getPessoa(rs)); // Adiciona cada pessoa na lista.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return lstP; // Retorna a lista de pessoas filtradas.
    }
    
    // Método auxiliar para criar um objeto Pessoa a partir de um ResultSet.
    public Pessoa getPessoa(ResultSet rs) throws SQLException {
        Pessoa p = new Pessoa(); // Cria um novo objeto Pessoa.
        
        p.setIdpessoa(rs.getInt("idpessoa")); // Define o ID da pessoa com o valor do ResultSet.
        p.setNome(rs.getString("nome")); // Define o nome da pessoa.
        p.setData(rs.getString("data_nascimento")); // Define a data de nascimento da pessoa.
        
        return p; // Retorna o objeto Pessoa preenchido.
    }
}
