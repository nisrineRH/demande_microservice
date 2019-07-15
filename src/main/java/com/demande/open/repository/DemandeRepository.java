package com.demande.open.repository;

import com.demande.open.domain.Demande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Demande entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

    @Query("SELECT c FROM Demande c WHERE " +
        "LOWER (c.dm_libelle) LIKE LOWER(CONCAT('%',:query,'%'))  " +
        "")
    Page<Demande> getByNumLibDM(@Param("query") String query, Pageable pageable);

    @Query("SELECT c FROM Demande c WHERE " +
        "LOWER (c.client.id) LIKE LOWER(CONCAT('%',:id,'%'))  ")
    Page<Demande> getByClient(@Param("id") Long id, Pageable pageable);


}
