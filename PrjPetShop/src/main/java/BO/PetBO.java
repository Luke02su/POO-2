package BO;

import DAO.PetDAO;
import Objetos.Pet;
import java.util.List;

public class PetBO {
    PetDAO petDAO;
    
    public PetBO() {
        petDAO = new PetDAO();
    }
    
    public void salvar(Pet p) {
        petDAO.salvar(p);
    }
    
    public void editar(Pet p) {
        petDAO.editar(p);
    }
        
    public void excluir(Pet p) {
        petDAO.excluir(p);
    }
    
    public List<Pet> getPets() {
        return petDAO.getPets();
    }
    
    public List<Pet> getPets(Pet p) {
        return petDAO.getPets(p);
    }
    
    public List<Pet> getPets(String nome) {
        return petDAO.getPets(nome);
    }
    
    public List<Pet> getPets(String nome, String dataInicio, String dataFim) {
        return petDAO.getPets(nome, dataInicio, dataFim);
    }
    
    public List<Pet> getPessoas(String dataInicio, String dataFim) {
        return petDAO.getPets(dataInicio, dataFim);
    }
}
