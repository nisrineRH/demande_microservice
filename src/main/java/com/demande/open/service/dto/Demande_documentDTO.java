package com.demande.open.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Demande_document entity.
 */
public class Demande_documentDTO implements Serializable {

    private Long id;

    private String dm_doc_libelle;

    private String dm_doc_type;


    private Long demandeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDm_doc_libelle() {
        return dm_doc_libelle;
    }

    public void setDm_doc_libelle(String dm_doc_libelle) {
        this.dm_doc_libelle = dm_doc_libelle;
    }

    public String getDm_doc_type() {
        return dm_doc_type;
    }

    public void setDm_doc_type(String dm_doc_type) {
        this.dm_doc_type = dm_doc_type;
    }

    public Long getDemandeId() {
        return demandeId;
    }

    public void setDemandeId(Long demandeId) {
        this.demandeId = demandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Demande_documentDTO demande_documentDTO = (Demande_documentDTO) o;
        if (demande_documentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demande_documentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Demande_documentDTO{" +
            "id=" + getId() +
            ", dm_doc_libelle='" + getDm_doc_libelle() + "'" +
            ", dm_doc_type='" + getDm_doc_type() + "'" +
            ", demande=" + getDemandeId() +
            "}";
    }
}
