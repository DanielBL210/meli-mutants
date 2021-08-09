package co.com.mercadolibre.mutants.dto;

/**
 * Esta clase define el objeto que se recibe para verificar un adn
 *
 * @author Daniel Botero
 * @version 08/08/2021
 */
public class MutantDto {

    String[] dna;

    public MutantDto() {
    }


    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
