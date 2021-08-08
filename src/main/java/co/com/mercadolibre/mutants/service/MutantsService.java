package co.com.mercadolibre.mutants.service;

import co.com.mercadolibre.mutants.dto.StatsDto;

public interface MutantsService {
    boolean analyzeAdn(String[] dna);
    StatsDto stats();
}
