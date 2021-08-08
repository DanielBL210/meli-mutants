package co.com.mercadolibre.mutants.repository;

import co.com.mercadolibre.mutants.model.MutantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends JpaRepository<MutantModel, Long> {

    @Query(value = "SELECT count(*) FROM mutants.mutants", nativeQuery = true)
    int countAll();

    @Query(value = "SELECT count(*) FROM mutants.mutants where is_mutant=:is_mutant", nativeQuery = true)
    int countAllByIsMutant(@Param("is_mutant") int is_mutant);
}

