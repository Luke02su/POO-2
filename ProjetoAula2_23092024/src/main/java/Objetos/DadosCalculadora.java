package Objetos;

public class DadosCalculadora {
    private float valor1;
    private float valor2;
    private float resultado;
    
    public DadosCalculadora(float valor1, float valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public float somar() {
        resultado =  valor1 + valor2;
        return resultado;
    }
    
    public float subtrair() {
        resultado = valor1 - valor2;
        return resultado;
    }
    
    public float multiplicar() {
        resultado = valor1 * valor2;
        return resultado;
    }
    
    public float dividir() {
        resultado = valor1 / valor2;
        return resultado;
    }
}
