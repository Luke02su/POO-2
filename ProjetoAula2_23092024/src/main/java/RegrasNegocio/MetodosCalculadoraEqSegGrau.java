package RegrasNegocio;

import Objetos.DadosCalculadoraEqSegGrauEntrada;
import Objetos.DadosCalculadoraEqSegGrauSaida;

public class MetodosCalculadoraEqSegGrau {

    private DadosCalculadoraEqSegGrauSaida delta(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = new DadosCalculadoraEqSegGrauSaida();
        s.setDelta((float)Math.pow(e.getB(), 2.00) - (4 * e.getA() * e.getC()));
        return s;
    }

    private DadosCalculadoraEqSegGrauSaida x1L(DadosCalculadoraEqSegGrauEntrada e) {
        DadosCalculadoraEqSegGrauSaida s = delta(e);  // Passa a entrada 'e' para calcular o delta
        s.setX1L((float)((-e.getB() + Math.sqrt(s.getDelta())) / (2 * e.getA())));  // Corrige a fórmula
        return s;
    }
}

// 3 entradas e 5 saidas
