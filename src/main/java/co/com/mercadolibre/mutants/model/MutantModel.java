package co.com.mercadolibre.mutants.model;

import javax.persistence.*;

/**
 * Esta clase define la entidad para guardar y consultas las verificaciones de adn
 *
 * @author Daniel Botero
 * @version 08/08/2021
 */
@Entity
@Table(name = "mutants")
public class MutantModel {

    @Id
    String dna;

    @Column(nullable = false)
    int isMutant;

    public MutantModel() {
    }

    public MutantModel(String dna, int isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public int getIsMutant() {
        return isMutant;
    }

    public void setIsMutant(int isMutant) {
        this.isMutant = isMutant;
    }
}
