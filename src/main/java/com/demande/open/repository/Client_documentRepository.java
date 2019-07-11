package com.demande.open.repository;

import com.demande.open.domain.Client_document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Client_document entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Client_documentRepository extends JpaRepository<Client_document, Long> {

    @Query("SELECT cd FROM Client_document cd WHERE " +
    "LOWER (cd.doc_libelle) LIKE LOWER(CONCAT('%',:query,'%'))  ")
    Page<Client_document> getByLibDoc(@Param("query") String query, Pageable pageable);

}
