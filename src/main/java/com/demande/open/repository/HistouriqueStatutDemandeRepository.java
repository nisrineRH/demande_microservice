package com.demande.open.repository;

import com.demande.open.domain.HistouriqueStatutDemande;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HistouriqueStatutDemande entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistouriqueStatutDemandeRepository extends JpaRepository<HistouriqueStatutDemande, Long> {

}
