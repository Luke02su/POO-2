package Util;

import java.sql.Date; // Importa a classe Date do pacote java.sql para trabalhar com datas no formato SQL.
import java.text.ParseException; // Importa a classe ParseException para tratar erros de parsing.
import java.text.SimpleDateFormat; // Importa a classe SimpleDateFormat para formatar e analisar datas.
import java.time.LocalDate; // Importa a classe LocalDate para manipular datas sem informações de horário.
import java.time.format.DateTimeFormatter; // Importa DateTimeFormatter para definir o formato de data.

public class ManipulaData {

    // Método que converte uma data no formato String (dd/MM/yyyy) para o tipo Date do SQL.
    public Date string2Date(String data) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Define o formato da data (dd/MM/yyyy).
        Date dataSaida = Date.valueOf(LocalDate.parse(data, formato)); // Converte a String para LocalDate e depois para Date do SQL.
        
        return dataSaida; // Retorna a data no formato SQL.
    }
    
    // Método que converte uma data no formato SQL (yyyy-MM-dd) para uma String (dd/MM/yyyy).
    public String date2String(String data) {
        try {
            // Converte a String 'data' no formato yyyy-MM-dd para java.util.Date.
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            // Formata a data no formato dd/MM/yyyy e armazena novamente na variável 'data'.
            data = new SimpleDateFormat("dd/MM/yyyy").format(date);
        }    
        catch(ParseException e) {
            // Imprime o erro de parsing, caso ocorra um problema ao converter a data.
            System.out.println(e);
        }
        return data; // Retorna a data no novo formato (dd/MM/yyyy).
    }
}
