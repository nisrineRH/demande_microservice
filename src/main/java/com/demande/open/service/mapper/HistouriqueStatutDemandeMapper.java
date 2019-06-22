package com.demande.open.service.mapper;

import com.demande.open.domain.*;
import com.demande.open.service.dto.HistouriqueStatutDemandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity HistouriqueStatutDemande and its DTO HistouriqueStatutDemandeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HistouriqueStatutDemandeMapper extends EntityMapper<HistouriqueStatutDemandeDTO, HistouriqueStatutDemande> {


    @Mapping(target = "demande", ignore = true)
    HistouriqueStatutDemande toEntity(HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO);

    default HistouriqueStatutDemande fromId(Long id) {
        if (id == null) {
            return null;
        }
        HistouriqueStatutDemande histouriqueStatutDemande = new HistouriqueStatutDemande();
        histouriqueStatutDemande.setId(id);
        return histouriqueStatutDemande;
    }
}
