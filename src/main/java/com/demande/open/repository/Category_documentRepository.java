package com.demande.open.repository;

import com.demande.open.domain.Category_document;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Category_document entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Category_documentRepository extends JpaRepository<Category_document, Long> {

}
