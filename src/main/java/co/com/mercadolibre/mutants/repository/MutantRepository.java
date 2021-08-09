package co.com.mercadolibre.mutants.repository;

import co.com.mercadolibre.mutants.model.MutantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Esta interfaz define metodos para el acceso a la tabla mutants de la base de datos
 *
 * @author Daniel Botero
 * @version 08/08/2021
 */
@Repository
public interface MutantRepository extends JpaRepository<MutantModel, Long> {

    /**
     * Metodo que define consulta para contar todos los registros de la base de datos
     *
     *
     * @return countAll la cantidad de registros en la base de datos
     */
    @Query(value = "SELECT count(*) FROM mutants.mutants", nativeQuery = true)
    int countAll();

    /**
     * Metodo que define consulta para contar los registros que sean o no mutantes, basado en el parametro enviado
     *
     * @param is_mutant parametro que define si se cuentan el numero de registros de los mutantes o de los no muntantes
     * @return countAllByIsMutant la cantidad de registros encontrada en la base de datos teniendo en cuenta el parametro recibido
     */
    @Query(value = "SELECT count(*) FROM mutants.mutants where is_mutant=:is_mutant", nativeQuery = true)
    int countAllByIsMutant(@Param("is_mutant") int is_mutant);
}

