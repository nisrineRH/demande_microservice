package com.demande.open.service.mapper;

import com.demande.open.domain.*;
import com.demande.open.service.dto.Demande_documentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Demande_document and its DTO Demande_documentDTO.
 */
@Mapper(componentModel = "spring", uses = {DemandeMapper.class})
public interface Demande_documentMapper extends EntityMapper<Demande_documentDTO, Demande_document> {

    @Mapping(source = "demande.id", target = "demandeId")
    Demande_documentDTO toDto(Demande_document demande_document);

    @Mapping(source = "demandeId", target = "demande")
    Demande_document toEntity(Demande_documentDTO demande_documentDTO);

    default Demande_document fromId(Long id) {
        if (id == null) {
            return null;
        }
        Demande_document demande_document = new Demande_document();
        demande_document.setId(id);
        return demande_document;
    }
}
