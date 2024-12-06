package DAO;

import Objetos.Pessoa;
import Objetos.Pet;
import Util.Conexao;
import Util.ManipulaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    Connection conn;
    ManipulaData md; // Declarando o objeto ManipulaData
    
    public PetDAO() {
        conn = new Conexao().conectar();
        md = new ManipulaData(); // Inicializando o objeto ManipulaData
    }
    
    public Pet salvar(Pet p) {
        try {
            // Prepara o comando SQL para inserir uma nova pessoa na tabela.
            System.out.println(md.string2Date(p.getData())); // Converte e exibe a data no formato SQL.
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO pet (nome, raca, data_nascimento, cor, porte, idpessoa) VALUES (?, ?, ?, ?, ?, ?)", 
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getNome()); // Atribui o valor do nome ao primeiro parâmetro.
            stmt.setString(2, p.getRaca());
            stmt.setDate(3, md.string2Date(p.getData())); // Converte e atribui a data de nascimento ao segundo parâmetro.
            stmt.setString(4, p.getCor());
            stmt.setString(5, p.getPorte());
            stmt.setInt(6, p.getDono().getIdpessoa());
            stmt.execute(); // Executa o comando SQL.

            ResultSet rs = stmt.getGeneratedKeys(); // Obtém as chaves geradas pelo banco (caso de chave primária autoincrementada).
            if (rs.next()) {
                p.setIdPet(rs.getInt("idpet")); // Define o ID do pet com o valor gerado.
            } else {
                p.setIdPet(-1); // Define o ID como -1 se nenhuma chave foi gerada.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro para facilitar a depuração.
        }
        return p; // Retorna o objeto Pet com o ID atualizado.
    }
    
    
        // Método para atualizar informações de uma Pessoa existente no banco de dados.
    public void editar(Pet p) {
        try {
            // Prepara o comando SQL para atualizar o nome e a data de nascimento de uma pessoa específica.
            PreparedStatement stmt = conn.prepareStatement("UPDATE pet SET nome = ?, raca = ?, data_nascimento = ?, cor = ?,  porte = ?, idpessoa = ? WHERE idpet = ?");
            stmt.setString(1, p.getNome()); // Atribui o valor do nome ao primeiro parâmetro.
            stmt.setString(2, p.getRaca());
            stmt.setDate(3, md.string2Date(p.getData())); // Converte e atribui a data de nascimento ao segundo parâmetro.
            stmt.setString(4, p.getCor());
            stmt.setString(5, p.getPorte());
            stmt.setInt(6, p.getDono().getIdpessoa());
            stmt.setInt(7, p.getIdPet()); // Define o ID da pessoa a ser atualizada.
            stmt.executeUpdate(); // Executa o comando de atualização.
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
    }
    
    public int excluir(Pet p) {
        int verif = 0; // Variável para verificar se a exclusão foi bem-sucedida.
        try {
            // Prepara o comando SQL para excluir uma pessoa específica.
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM pet WHERE idpet = ?");
            stmt.setInt(1, p.getIdPet()); // Define o ID da pessoa a ser excluída.
            verif = stmt.executeUpdate(); // Executa o comando de exclusão e retorna o número de linhas afetadas.
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return verif; // Retorna o número de linhas excluídas (0 se não houver nenhuma).
    }

        // Método para retornar uma lista de todas as pessoas no banco de dados.
    public List<Pet> getPets() {
        List<Pet> lstP = new ArrayList<>(); // Cria uma lista para armazenar as pessoas.
        ResultSet rs; // Declara o ResultSet para armazenar o resultado da consulta.

        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pet"); // Prepara o comando SQL de consulta.
            rs = ppStmt.executeQuery(); // Executa a consulta e armazena o resultado no ResultSet.
            
            while (rs.next()) { // Itera sobre o resultado da consulta.
                lstP.add(getPet(rs)); // Adiciona cada pessoa na lista.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return lstP; // Retorna a lista de pessoas.
    }
    
        // Método sobrecarregado para retornar uma lista de pessoas que correspondem a um filtro.
    public List<Pet> getPets(Pet p) {
        List<Pet> lstP = new ArrayList<>(); // Cria uma lista para armazenar as pessoas filtradas.
        ResultSet rs; // Declara o ResultSet para armazenar o resultado da consulta.

        try {
            // Prepara o comando SQL para buscar pessoas com nome que começam com o nome informado.
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pet WHERE nome ILIKE ?");
            ppStmt.setString(1, p.getNome() + "%"); // Define o valor para o filtro de nome.
            rs = ppStmt.executeQuery(); // Executa a consulta e armazena o resultado no ResultSet.
            
            while (rs.next()) { // Itera sobre o resultado da consulta.
                lstP.add(getPet(rs)); // Adiciona cada pessoa na lista.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return lstP; // Retorna a lista de pessoas filtradas.
    }
    
    public List<Pet> getPets(String nome) {
        List<Pet> lstP = new ArrayList<>(); // Cria uma lista para armazenar as pessoas filtradas.
        ResultSet rs; // Declara o ResultSet para armazenar o resultado da consulta.

        try {
            // Prepara o comando SQL para buscar pessoas com nome que começam com o nome informado.
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pet WHERE nome ILIKE ?");
            System.out.println(nome);
            ppStmt.setString(1, nome+ "%"); // Define o valor para o filtro de nome.
            rs = ppStmt.executeQuery(); // Executa a consulta e armazena o resultado no ResultSet.
            while (rs.next()) { // Itera sobre o resultado da consulta.
                lstP.add(getPet(rs)); // Adiciona cada pessoa na lista.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return lstP; // Retorna a lista de pessoas filtradas.
    }
        
    public List<Pet> getPets(String nome, String dataInicio, String dataFim) {
        List<Pet> lstP = new ArrayList<>(); // Cria uma lista para armazenar as pessoas filtradas.
        ResultSet rs; // Declara o ResultSet para armazenar o resultado da consulta.

        try {
            // Prepara o comando SQL para buscar pessoas com nome que começam com o nome informado.
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pet WHERE nome ILIKE ? AND data_nascimento BETWEEN ? AND ?");
            ppStmt.setString(1, nome+ "%"); // Define o valor para o filtro de nome.
            ppStmt.setDate(2, md.string2Date(dataInicio));
            ppStmt.setDate(3, md.string2Date(dataFim));
            rs = ppStmt.executeQuery(); // Executa a consulta e armazena o resultado no ResultSet.
            while (rs.next()) { // Itera sobre o resultado da consulta.
                lstP.add(getPet(rs)); // Adiciona cada pessoa na lista.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return lstP; // Retorna a lista de pessoas filtradas.
    }
    
        public List<Pet> getPets(String dataInicio, String dataFim) {
        List<Pet> lstP = new ArrayList<>(); // Cria uma lista para armazenar as pessoas filtradas.
        ResultSet rs; // Declara o ResultSet para armazenar o resultado da consulta.

        try {
            // Prepara o comando SQL para buscar pessoas com nome que começam com o nome informado.
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM pet WHERE data_nascimento BETWEEN ? AND ?");
            ppStmt.setDate(1, md.string2Date(dataInicio));
            ppStmt.setDate(2, md.string2Date(dataFim));
            rs = ppStmt.executeQuery(); // Executa a consulta e armazena o resultado no ResultSet.
            while (rs.next()) { // Itera sobre o resultado da consulta.
                lstP.add(getPet(rs)); // Adiciona cada pessoa na lista.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime o stack trace do erro.
        }
        return lstP; // Retorna a lista de pessoas filtradas.
    }
    
    // Método auxiliar para criar um objeto Pessoa a partir de um ResultSet.
    public Pet getPet(ResultSet rs) throws SQLException {
        Pet p = new Pet(); // Cria um novo objeto Pessoa.
        Pessoa dono = new Pessoa();
        
        p.setIdPet(rs.getInt("idpet")); // Define o ID da pessoa com o valor do ResultSet.
        p.setNome(rs.getString("nome")); // Define o nome da pessoa.
        p.setRaca(rs.getString("raca")); // Define o nome da pessoa.
        p.setData(rs.getString("data_nascimento")); // Define a data de nascimento da pessoa.
        p.setCor(rs.getString("cor")); // Define o nome da pessoa.
        p.setPorte(rs.getString("porte")); // Define o nome da pessoa.
        dono.setIdpessoa(rs.getInt("idpessoa"));
        p.setDono(dono);
        
        return p; // Retorna o objeto Pessoa preenchido.
    }
}
