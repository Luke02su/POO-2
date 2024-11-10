package BO;

import DAO.PessoaDAO;
import Objetos.Pessoa;
import java.util.List;

public class PessoaBO {
    PessoaDAO pDAO;
    
    public PessoaBO() {
        pDAO = new PessoaDAO();
    }
    
    public void salvar(Pessoa p) {
        pDAO.salvar(p);
    }
    
    public void editar(Pessoa p) {
        pDAO.editar(p);
    }
    
    public void excluir(Pessoa p) {
        pDAO.excluir(p);
    }
    
    public List<Pessoa> getPessoas() {
        return pDAO.getPessoas();
    }
    
    public List<Pessoa> getPessoas(Pessoa p) {
        return pDAO.getPessoas(p);
    }
    
    public List<Pessoa> getPessoas(String nome) {
        return pDAO.getPessoas(nome);
    }
    
    public List<Pessoa> getPessoas(String nome, String dataInicio, String dataFim) {
        return pDAO.getPessoas(nome, dataInicio, dataFim);
    }
    
    public List<Pessoa> getPessoas(String dataInicio, String dataFim) {
        return pDAO.getPessoas(dataInicio, dataFim);
    }
}
