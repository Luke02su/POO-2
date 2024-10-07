package RegrasNegocio;

import Objetos.DadosCalculadoraEqSegGrauEntrada;
import Objetos.DadosCalculadoraEqSegGrauSaida;

public class MetodosCalculadoraEqSegGrau {

    public DadosCalculadoraEqSegGrauSaida delta(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = new DadosCalculadoraEqSegGrauSaida();
        s.setDelta((float) Math.pow(e.getB(), 2.00) - (4 * e.getA() * e.getC()));
        return s;
    }

    public DadosCalculadoraEqSegGrauSaida x1L(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = delta(e);  // Passa a entrada 'e' para calcular o delta
        s.setX1L((float) ((-e.getB() + Math.sqrt(s.getDelta())) / (2 * e.getA())));  // Aplica a fórmula de Bhaskara para x1
        return s;
    }

    public DadosCalculadoraEqSegGrauSaida x2L(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = delta(e);  // Calcula delta novamente
        s.setX2L((float) ((-e.getB() - Math.sqrt(s.getDelta())) / (2 * e.getA())));  // Aplica a fórmula de Bhaskara para x2
        return s;
    }

    public DadosCalculadoraEqSegGrauSaida xv(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = new DadosCalculadoraEqSegGrauSaida();  // Não recursivo
        s.setXv((float) (-e.getB() / (2 * e.getA())));  // Aplica a fórmula para xv
        return s;
    }

    public DadosCalculadoraEqSegGrauSaida yv(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = delta(e);  // Usa delta para calcular yv
        s.setYv((float) (-s.getDelta() / (4 * e.getA())));  // Aplica a fórmula correta para yv
        return s;
    }
}
