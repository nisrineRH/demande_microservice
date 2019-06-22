package com.demande.open.service.mapper;

import com.demande.open.domain.*;
import com.demande.open.service.dto.DemandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Demande and its DTO DemandeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DemandeMapper extends EntityMapper<DemandeDTO, Demande> {



    default Demande fromId(Long id) {
        if (id == null) {
            return null;
        }
        Demande demande = new Demande();
        demande.setId(id);
        return demande;
    }
}
