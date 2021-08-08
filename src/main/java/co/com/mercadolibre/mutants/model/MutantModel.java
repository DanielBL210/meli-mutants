package co.com.mercadolibre.mutants.model;

import javax.persistence.*;

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
