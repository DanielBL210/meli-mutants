package co.com.mercadolibre.mutants.service;

import co.com.mercadolibre.mutants.dto.StatsDto;
/**
 * Esta interfaz define metodos para la implementacion de la logica de la aplicacion
 *
 * @author Daniel Botero
 * @version 08/08/2021
 */
public interface MutantsService {
    /**
     * Metodo que analiza el adn de un humano para saber si es mutante o no
     *
     * @param dna que contiene el adn a analizar
     * @return boolean true si es mutante, false si NO es mutante
     */
    boolean analyzeAdn(String[] dna);

    /**
     * Servicio que devuelve las estadisticas de las verificaciones de adn
     *
     * @return StatsDto, el dto contiene:
     * - count_human_dna, el numero total de verificaciones
     * - count_mutant_dna, el numero de mutantes encontrados
     * - ratio, proporcion de mutantes con realcion al total de verificaciones
     */
    StatsDto stats();
}
