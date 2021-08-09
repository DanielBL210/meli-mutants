package co.com.mercadolibre.mutants;

import co.com.mercadolibre.mutants.controller.MutantsContoller;
import co.com.mercadolibre.mutants.dto.MutantDto;
import co.com.mercadolibre.mutants.dto.StatsDto;
import co.com.mercadolibre.mutants.service.MutantsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
public class MutantServiceTests {

    @Autowired
    private MutantsService mutantsService;

    @Test
    void isMutant() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        Assertions.assertEquals(mutantsService.analyzeAdn(dna), true);

        String[] dna2 = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        Assertions.assertEquals(mutantsService.analyzeAdn(dna2), false);

        String[] dna3 = {"ATGCGA", "CAAAAC", "TTATTT", "AGACGG", "GCCCCA", "TCACTG"};
        Assertions.assertEquals(mutantsService.analyzeAdn(dna3), true);

        String[] dna4 = {"ATGCGA", "CAAAAC", "TTATTT", "TGACGG", "TCTCCA", "TCACTG"};
        Assertions.assertEquals(mutantsService.analyzeAdn(dna4), true);

        String[] dna5 = {"ATGCGA", "CAAAAC", "ATATTT", "AGACGG", "ACTCCA", "TCACTG"};
        Assertions.assertEquals(mutantsService.analyzeAdn(dna5), false);

        String[] dna6 = {"ATACGA", "CAAAAC", "ATATTT", "TGACGG", "ACTCCA", "TCACTG"};
        Assertions.assertEquals(mutantsService.analyzeAdn(dna6), true);

        String[] dna7 = {"ATGCGA", "CAAAAC", "ATATTT", "AGACGG", "ACACCA", "TCACTG"};
        Assertions.assertEquals(mutantsService.analyzeAdn(dna7), true);

    }

    @Test
    void stats() {
        StatsDto statsDto = new StatsDto(5,7,0.71428573f);
        StatsDto statsDtoDB = mutantsService.stats();
        Assertions.assertEquals(statsDto.getCount_human_dna(), statsDtoDB.getCount_human_dna());
        Assertions.assertEquals(statsDto.getCount_mutant_dna(), statsDtoDB.getCount_mutant_dna());
        Assertions.assertEquals(statsDto.getRatio(), statsDtoDB.getRatio());
    }


}
