package com.demande.open.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Demande_document.
 */
@Entity
@Table(name = "demande_document")
public class Demande_document implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dm_doc_libelle")
    private String dm_doc_libelle;

    @Column(name = "dm_doc_type")
    private String dm_doc_type;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JsonIgnoreProperties("demande_documents")
    private Demande demande;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDm_doc_libelle() {
        return dm_doc_libelle;
    }

    public Demande_document dm_doc_libelle(String dm_doc_libelle) {
        this.dm_doc_libelle = dm_doc_libelle;
        return this;
    }

    public void setDm_doc_libelle(String dm_doc_libelle) {
        this.dm_doc_libelle = dm_doc_libelle;
    }

    public String getDm_doc_type() {
        return dm_doc_type;
    }

    public Demande_document dm_doc_type(String dm_doc_type) {
        this.dm_doc_type = dm_doc_type;
        return this;
    }

    public void setDm_doc_type(String dm_doc_type) {
        this.dm_doc_type = dm_doc_type;
    }

    public String getUrl() {
        return url;
    }

    public Demande_document url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Demande getDemande() {
        return demande;
    }

    public Demande_document demande(Demande demande) {
        this.demande = demande;
        return this;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Demande_document demande_document = (Demande_document) o;
        if (demande_document.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demande_document.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Demande_document{" +
            "id=" + getId() +
            ", dm_doc_libelle='" + getDm_doc_libelle() + "'" +
            ", dm_doc_type='" + getDm_doc_type() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
