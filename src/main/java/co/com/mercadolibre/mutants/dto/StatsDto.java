package co.com.mercadolibre.mutants.dto;

/**
 * Esta clase define el objeto de las estadisticas de las verificaciones
 *
 * @author Daniel Botero
 * @version 08/08/2021
 */
public class StatsDto {
    int count_mutant_dna;
    int count_human_dna;
    float ratio;

    public StatsDto() {
    }

    public StatsDto(int count_mutant_dna, int count_human_dna, float ratio) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = ratio;
    }

    public int getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(int count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public int getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(int count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

}
