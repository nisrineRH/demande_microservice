package com.demande.open.repository;

import com.demande.open.domain.Client_document;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Client_document entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Client_documentRepository extends JpaRepository<Client_document, Long> {

}
