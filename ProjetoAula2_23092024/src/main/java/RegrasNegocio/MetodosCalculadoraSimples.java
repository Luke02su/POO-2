package RegrasNegocio;

import Objetos.DadosCalculadoraSimples;

public class MetodosCalculadoraSimples {
    public DadosCalculadoraSimples somar(DadosCalculadoraSimples dcs) {
        dcs.setResultado(dcs.getValor1() + dcs.getValor2());
        return dcs;
    }
    
    public DadosCalculadoraSimples subtrair(DadosCalculadoraSimples dcs) {
        dcs.setResultado(dcs.getValor1() - dcs.getValor2());
        return dcs;
    }
    
    public DadosCalculadoraSimples multiplicar(DadosCalculadoraSimples dcs) {
        dcs.setResultado(dcs.getValor1() * dcs.getValor2());
        return dcs;
    }
        
    public DadosCalculadoraSimples dividir(DadosCalculadoraSimples dcs) {
        dcs.setResultado(dcs.getValor1() / dcs.getValor2());
        return dcs;
    }
}
