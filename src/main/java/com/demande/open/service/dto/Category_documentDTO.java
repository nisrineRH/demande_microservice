package com.demande.open.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Category_document entity.
 */
public class Category_documentDTO implements Serializable {

    private Long id;

    private Integer cat_numero;

    private String cat_nom;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCat_numero() {
        return cat_numero;
    }

    public void setCat_numero(Integer cat_numero) {
        this.cat_numero = cat_numero;
    }

    public String getCat_nom() {
        return cat_nom;
    }

    public void setCat_nom(String cat_nom) {
        this.cat_nom = cat_nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category_documentDTO category_documentDTO = (Category_documentDTO) o;
        if (category_documentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), category_documentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Category_documentDTO{" +
            "id=" + getId() +
            ", cat_numero=" + getCat_numero() +
            ", cat_nom='" + getCat_nom() + "'" +
            "}";
    }
}
