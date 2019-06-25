package com.demande.open.service.mapper;

import com.demande.open.domain.*;
import com.demande.open.service.dto.Category_documentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Category_document and its DTO Category_documentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Category_documentMapper extends EntityMapper<Category_documentDTO, Category_document> {


    @Mapping(target = "client_documents", ignore = true)
    Category_document toEntity(Category_documentDTO category_documentDTO);

    default Category_document fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category_document category_document = new Category_document();
        category_document.setId(id);
        return category_document;
    }
}
