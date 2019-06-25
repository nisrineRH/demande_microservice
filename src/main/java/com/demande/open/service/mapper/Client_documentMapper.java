package com.demande.open.service.mapper;

import com.demande.open.domain.*;
import com.demande.open.service.dto.Client_documentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Client_document and its DTO Client_documentDTO.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class, Category_documentMapper.class})
public interface Client_documentMapper extends EntityMapper<Client_documentDTO, Client_document> {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "category_document.id", target = "category_documentId")
    Client_documentDTO toDto(Client_document client_document);

    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "category_documentId", target = "category_document")
    Client_document toEntity(Client_documentDTO client_documentDTO);

    default Client_document fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client_document client_document = new Client_document();
        client_document.setId(id);
        return client_document;
    }
}
