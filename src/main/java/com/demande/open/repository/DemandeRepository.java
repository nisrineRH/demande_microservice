package com.demande.open.repository;

import java.util.List;
import java.util.Optional;

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

    @Query("SELECT d FROM Demande d WHERE " +
    "LOWER(d.dm_numero) LIKE LOWER(CONCAT('%',:query,'%')) OR " +
    "LOWER(d.dm_libelle) LIKE LOWER(CONCAT('%',:query,'%') ) ")
    Page<Demande> getByNum(@Param ("query") String query,Pageable pageable);

}
