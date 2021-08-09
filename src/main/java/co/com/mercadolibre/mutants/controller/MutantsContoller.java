package co.com.mercadolibre.mutants.controller;


import co.com.mercadolibre.mutants.dto.MutantDto;
import co.com.mercadolibre.mutants.dto.StatsDto;
import co.com.mercadolibre.mutants.service.MutantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Esta clase define los servicios que se exponen en la aplicacion de mutantes
 *
 * @author Daniel Botero
 * @version 08/08/2021
 */

@RestController
@RequestMapping(path = "")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class MutantsContoller {

    @Autowired
    private MutantsService mutantsService;

    public MutantsContoller() {
    }

    public MutantsContoller(MutantsService mutantsService) {
        this.mutantsService = mutantsService;
    }

    /**
     * Servicio que verifica si un humono es mutante o no
     *
     * @param dto que contiene el adn a verificar
     * @return HttpStatus 200 si es mutante, 403 si NO es mutante
     */
    @PostMapping("/mutant")
    public ResponseEntity isMutant(@RequestBody MutantDto dto) {
        boolean isMutant = mutantsService.analyzeAdn(dto.getDna());
        if (isMutant) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    /**
     * Servicio que devuelve las estadisticas de las verificaciones de adn
     *
     * @return ResponseEntity, con el dto que contiene:
     * - count_human_dna, el numero total de verificaciones
     * - count_mutant_dna, el numero de mutantes encontrados
     * - ratio, proporcion de mutantes con realcion al total de verificaciones
     */
    @GetMapping("/stats")
    public ResponseEntity stats() {
        StatsDto dto = mutantsService.stats();
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
