package com.demande.open.repository;

import com.demande.open.domain.Demande_document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Demande_document entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Demande_documentRepository extends JpaRepository<Demande_document, Long> {

    @Query("SELECT Ddoc FROM Demande_document Ddoc WHERE " +
        "LOWER (Ddoc.demande.id) LIKE LOWER(CONCAT('%',:id,'%'))  ")
    Page<Demande_document> getByDemande(@Param("id") Long id, Pageable pageable);

}
