package com.demande.open.repository;

import com.demande.open.domain.HistouriqueStatutDemande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HistouriqueStatutDemande entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistouriqueStatutDemandeRepository extends JpaRepository<HistouriqueStatutDemande, Long> {

    @Query("SELECT hsd FROM HistouriqueStatutDemande hsd WHERE " +
        "LOWER (hsd.demande.id) LIKE LOWER(CONCAT('%',:id,'%'))  ")
    Page<HistouriqueStatutDemande> getHSDByDemande(@Param("id") Long id, Pageable pageable);

}
