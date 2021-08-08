package co.com.mercadolibre.mutants.service.impl;

import co.com.mercadolibre.mutants.dto.StatsDto;
import co.com.mercadolibre.mutants.model.MutantModel;
import co.com.mercadolibre.mutants.repository.MutantRepository;
import co.com.mercadolibre.mutants.service.MutantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantsServiceImpl implements MutantsService {
    @Autowired
    private MutantRepository mutantRepository;

    @Override
    public boolean analyzeAdn(String[] dna) {
        boolean isMutant = isMutant(dna);
        saveAdn(dna, isMutant);
        return isMutant;
    }

    @Override
    public StatsDto stats() {
        float human_dna_count = mutantRepository.countAll();
        float mutant_dna_count = mutantRepository.countAllByIsMutant(1);
        float ratio = (human_dna_count != 0) ? mutant_dna_count / human_dna_count : 0;
        return new StatsDto((int) mutant_dna_count, (int) human_dna_count, ratio);
    }

    boolean isMutant(String[] dna) {
        int rows = dna.length;
        int columns = dna[0].length();
        int sequenceCount = 0;
        String[][] completeTable = convertVectorToMatrix(dna);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sequenceCount = sequenceCount + sequenceCount(validateSequence(completeTable, i, j, rows, columns));
                if (sequenceCount > 1) {
                    break;
                }
            }
            if (sequenceCount > 1) {
                break;
            }
        }
        return sequenceCount > 1;
    }


    String[][] convertVectorToMatrix(String[] vector) {
        int rows = vector.length;
        int columns = vector[0].length();
        String[][] matrix = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            matrix[i] = vector[i].split("");
        }
        return matrix;
    }

    int sequenceCount(boolean[] result) {
        int seqCount = 0;
        for (int i = 0; i < result.length; i++) {
            seqCount = seqCount + ((result[i]) ? 1 : 0);
        }
        return seqCount;
    }

    boolean[] validateSequence(String[][] dna, int i, int j, int rows, int cols) {
        boolean vertical = true;
        boolean horizontal = true;
        boolean obliqueRight = true;
        boolean obliqueLeft = true;
        for (int aux = 1; aux < 4; aux++) {
            vertical = (j + aux < cols) ? vertical && dna[i][j].equals(dna[i][j + aux]) : false;
            horizontal = (i + aux < rows) ? horizontal && dna[i][j].equals(dna[i + aux][j]) : false;

            obliqueRight = (j + aux < cols && i + aux < rows) ? obliqueRight && dna[i][j].equals(dna[i + aux][j + aux]) : false;
            obliqueLeft = (i + aux < rows && j - aux > -1) ? obliqueLeft && dna[i][j].equals(dna[i + aux][j - aux]) : false;
        }
        boolean[] result = new boolean[4];
        result[0] = vertical;
        result[1] = horizontal;
        result[2] = obliqueLeft;
        result[3] = obliqueRight;
        return result;
    }

    void saveAdn(String[] adn, boolean isMutant) {
        MutantModel mutantModel = new MutantModel(convertVectorToString(adn), (isMutant) ? 1 : 0);
        mutantRepository.save(mutantModel);
    }

    public String convertVectorToString(String[] vector) {
        String s = "";
        for (int i = 0; i < vector.length; i++) {
            s = s + vector[i];
        }
        return s;
    }

}
