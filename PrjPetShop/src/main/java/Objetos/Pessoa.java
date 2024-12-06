package Objetos;

public class Pessoa {
    private int idpessoa;
    private String nome;
    private String data;
    
    
    // Construtor vazio
    public Pessoa() {}

    // Construtor com ID (caso necessário para associações rápidas)
    public Pessoa(int idPessoa) {
        this.idpessoa = idPessoa;
    }

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
