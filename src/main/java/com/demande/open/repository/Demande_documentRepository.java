package com.demande.open.repository;

import com.demande.open.domain.Demande_document;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Demande_document entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Demande_documentRepository extends JpaRepository<Demande_document, Long> {

}
