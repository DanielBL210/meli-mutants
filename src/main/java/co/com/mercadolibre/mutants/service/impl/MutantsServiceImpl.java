package co.com.mercadolibre.mutants.service.impl;

import co.com.mercadolibre.mutants.dto.StatsDto;
import co.com.mercadolibre.mutants.model.MutantModel;
import co.com.mercadolibre.mutants.repository.MutantRepository;
import co.com.mercadolibre.mutants.service.MutantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase define la logica de los servicios expuestaos en la aplicacion de mutantes
 *
 * @author Daniel Botero
 * @version 08/08/2021
 */
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

    /**
     * Metodo que verifica si un humono es mutante o no
     *
     * @param dna que contiene el adn a verificar
     * @return boolean true si es mutante, false si NO es mutante
     */
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

    /**
     * Metodo que recibe un vector y lo pasa a una matriz
     *
     * @param vector a transformar
     * @return matrix resultante de la transformacion del vector
     */
    String[][] convertVectorToMatrix(String[] vector) {
        int rows = vector.length;
        int columns = vector[0].length();
        String[][] matrix = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            matrix[i] = vector[i].split("");
        }
        return matrix;
    }

    /**
     * Metodo que cuenta el numero de resultados positivos de la verificacion
     *
     * @param result, vector de resultados
     * @return seqCount, cantidad de resultados positivos encontrados
     */
    int sequenceCount(boolean[] result) {
        int seqCount = 0;
        for (int i = 0; i < result.length; i++) {
            seqCount = seqCount + ((result[i]) ? 1 : 0);
        }
        return seqCount;
    }

    /**
     * Metodo que verifica secuencias de 4 letras iguales seguiidas en direccion
     * vertical, horizontal, diagonal inferior derecha y diagonal inferior izquierda
     *
     * @param dna   que contiene el adn a verificar
     * @param i,    fila de la letra desde la cual se esta haciendo la verificacion
     * @param j,    columna de la letra desde la cual se esta haciendo la verificacion
     * @param rows, cantidad de filas de la matriz
     * @param cols, cantidad de columnas de la matriz
     * @return result, vector que contiene el resultado de las verificaciones en el siguiente orden:
     * [0]=vertical, [1]=horizontal, [2]=Diagonal Izquierda, [3]=Diagonal Derecha
     */
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

    /**
     * Metodo que guarda el adn verificado y su resultado en la base de datos
     *
     * @param dna      que contiene el adn verificado
     * @param isMutant que contiene el resultado de la verificacion
     */
    void saveAdn(String[] adn, boolean isMutant) {
        MutantModel mutantModel = new MutantModel(convertVectorToString(adn), (isMutant) ? 1 : 0);
        mutantRepository.save(mutantModel);
    }

    /**
     * Metodo que recibe un vector lo pasa a un string concatenando cada valor del vector de forma consecutiva
     *
     * @param vector que se va pasar a string
     * @return s string con los valores del vector concatenados
     */
    public String convertVectorToString(String[] vector) {
        String s = "";
        for (int i = 0; i < vector.length; i++) {
            s = s + vector[i];
        }
        return s;
    }

}
