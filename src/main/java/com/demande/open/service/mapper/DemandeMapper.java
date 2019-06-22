package com.demande.open.service.mapper;

import com.demande.open.domain.*;
import com.demande.open.service.dto.DemandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Demande and its DTO DemandeDTO.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class, HistouriqueStatutDemandeMapper.class})
public interface DemandeMapper extends EntityMapper<DemandeDTO, Demande> {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "histouriqueStatutDemande.id", target = "histouriqueStatutDemandeId")
    DemandeDTO toDto(Demande demande);

    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "histouriqueStatutDemandeId", target = "histouriqueStatutDemande")
    Demande toEntity(DemandeDTO demandeDTO);

    default Demande fromId(Long id) {
        if (id == null) {
            return null;
        }
        Demande demande = new Demande();
        demande.setId(id);
        return demande;
    }
}
