package RegrasNegocio;

//Importando o pacote Objetos
import Objetos.DadosCalculadoraEqSegGrauEntrada; //Importando dados de entrada
import Objetos.DadosCalculadoraEqSegGrauSaida; // Importando dados de saída
import javax.swing.JOptionPane; //Importantando para usar mensagem

public class MetodosCalculadoraEqSegGrau {
    
    private boolean mostrarMensagem = true; // Foi criado pois delta é chemado em outros métodos fazndo a mensagem de "Não existem raaízes aparecer mais de uma vez em razão do x1, x2, xv e xy
    
    private DadosCalculadoraEqSegGrauSaida delta(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = new DadosCalculadoraEqSegGrauSaida();
        s.setDelta((float)Math.pow(e.getB(), 2.0) - (4 * e.getA() * e.getC()));
            if (s.getDelta() <= 0) {
                if(mostrarMensagem) { // Mostra a mensagem apenas uma vez, se for verdadeiro.
                    JOptionPane.showMessageDialog(null, "Não existem raízes reais."); // Mensagem 
                   mostrarMensagem = false; // Recebe falso para não mostar mais.
                }
            }
        return s;
    }
    
    public DadosCalculadoraEqSegGrauSaida x1L(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = delta(e);  // Passa a entrada 'e' para calcular o delta
        s.setX1L((float)((-e.getB() + Math.sqrt(s.getDelta())) / (2.0 * e.getA())));  // Aplica a fórmula de Bhaskara para x1
        return s;
    }

    public DadosCalculadoraEqSegGrauSaida x2L(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = delta(e);  // Calcula delta novamente
        s.setX2L((float)((-e.getB() - Math.sqrt(s.getDelta())) / (2.0 * e.getA())));  // Aplica a fórmula de Bhaskara para x2
        return s;
    }

    public DadosCalculadoraEqSegGrauSaida xv(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = new DadosCalculadoraEqSegGrauSaida();  // Não recursivo
        s.setXv((float)(-e.getB() / (2.0 * e.getA())));  // Aplica a fórmula para xv
        return s;
    }

    public DadosCalculadoraEqSegGrauSaida yv(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = delta(e);  // Usa delta para calcular yv
        s.setYv((float)(-s.getDelta() / (4.0 * e.getA())));  // Aplica a fórmula correta para yv
        return s;
    }
    
    public DadosCalculadoraEqSegGrauSaida[] efetuarCalculo(DadosCalculadoraEqSegGrauEntrada e) { // Método vetorial de efetuarCalculo 
        DadosCalculadoraEqSegGrauSaida[] dadosSaida = new DadosCalculadoraEqSegGrauSaida[5]; // instanciando objeto vetorial dadosSaida
        
        dadosSaida[0] = delta(e); // Passando index para cada objeto que receberá os respectivos valores.
        dadosSaida[1] = x1L(e);
        dadosSaida[2] = x2L(e);
        dadosSaida[3] = xv(e);
        dadosSaida[4] = yv(e);
        
        return dadosSaida;
    }
}
