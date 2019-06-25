package com.demande.open.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Client_document entity.
 */
public class Client_documentDTO implements Serializable {

    private Long id;

    private String doc_chemin;

    private String doc_libelle;

    private LocalDate doc_date;


    private Long clientId;

    private Long category_documentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoc_chemin() {
        return doc_chemin;
    }

    public void setDoc_chemin(String doc_chemin) {
        this.doc_chemin = doc_chemin;
    }

    public String getDoc_libelle() {
        return doc_libelle;
    }

    public void setDoc_libelle(String doc_libelle) {
        this.doc_libelle = doc_libelle;
    }

    public LocalDate getDoc_date() {
        return doc_date;
    }

    public void setDoc_date(LocalDate doc_date) {
        this.doc_date = doc_date;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getCategory_documentId() {
        return category_documentId;
    }

    public void setCategory_documentId(Long category_documentId) {
        this.category_documentId = category_documentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Client_documentDTO client_documentDTO = (Client_documentDTO) o;
        if (client_documentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), client_documentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Client_documentDTO{" +
            "id=" + getId() +
            ", doc_chemin='" + getDoc_chemin() + "'" +
            ", doc_libelle='" + getDoc_libelle() + "'" +
            ", doc_date='" + getDoc_date() + "'" +
            ", client=" + getClientId() +
            ", category_document=" + getCategory_documentId() +
            "}";
    }
}
