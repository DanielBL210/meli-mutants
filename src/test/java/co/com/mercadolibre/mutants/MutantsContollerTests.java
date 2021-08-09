package co.com.mercadolibre.mutants;

import co.com.mercadolibre.mutants.controller.MutantsContoller;
import co.com.mercadolibre.mutants.dto.MutantDto;
import co.com.mercadolibre.mutants.dto.StatsDto;
import co.com.mercadolibre.mutants.service.MutantsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class MutantsContollerTests {

    @Autowired
    private MutantsService mutantsService;


    @Test
    void isMutant() {
        MutantsContoller contoller = new MutantsContoller(mutantsService);
        MutantDto dto = new MutantDto();
        String[] dna = {"ATGCGA", "CAAAAC", "ATATTT", "AGACGG", "ACACCA", "TCACTG"};
        dto.setDna(dna);
        Assertions.assertEquals(contoller.isMutant(dto), ResponseEntity.ok().build());

        String[] dna2 = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        dto.setDna(dna2);
        Assertions.assertEquals(contoller.isMutant(dto), ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @Test
    void stats() {
        MutantsContoller contoller = new MutantsContoller(mutantsService);
        StatsDto statsDto = new StatsDto();
        statsDto.setCount_human_dna(7);
        statsDto.setCount_mutant_dna(5);
        statsDto.setRatio(0.71428573f);
        StatsDto statsDtoDB = (StatsDto) contoller.stats().getBody();
        int status = contoller.stats().getStatusCodeValue();

        Assertions.assertEquals(statsDto.getCount_human_dna(), statsDtoDB.getCount_human_dna());
        Assertions.assertEquals(statsDto.getCount_mutant_dna(), statsDtoDB.getCount_mutant_dna());
        Assertions.assertEquals(statsDto.getRatio(), statsDtoDB.getRatio());
        Assertions.assertEquals(status, 200);
    }
}
