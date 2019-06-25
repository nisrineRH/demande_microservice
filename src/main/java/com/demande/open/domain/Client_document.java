package com.demande.open.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Client_document.
 */
@Entity
@Table(name = "client_document")
public class Client_document implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "doc_chemin")
    private String doc_chemin;

    @Column(name = "doc_libelle")
    private String doc_libelle;

    @Column(name = "doc_date")
    private LocalDate doc_date;

    @ManyToOne
    @JsonIgnoreProperties("client_documents")
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties("client_documents")
    private Category_document category_document;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoc_chemin() {
        return doc_chemin;
    }

    public Client_document doc_chemin(String doc_chemin) {
        this.doc_chemin = doc_chemin;
        return this;
    }

    public void setDoc_chemin(String doc_chemin) {
        this.doc_chemin = doc_chemin;
    }

    public String getDoc_libelle() {
        return doc_libelle;
    }

    public Client_document doc_libelle(String doc_libelle) {
        this.doc_libelle = doc_libelle;
        return this;
    }

    public void setDoc_libelle(String doc_libelle) {
        this.doc_libelle = doc_libelle;
    }

    public LocalDate getDoc_date() {
        return doc_date;
    }

    public Client_document doc_date(LocalDate doc_date) {
        this.doc_date = doc_date;
        return this;
    }

    public void setDoc_date(LocalDate doc_date) {
        this.doc_date = doc_date;
    }

    public Client getClient() {
        return client;
    }

    public Client_document client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Category_document getCategory_document() {
        return category_document;
    }

    public Client_document category_document(Category_document category_document) {
        this.category_document = category_document;
        return this;
    }

    public void setCategory_document(Category_document category_document) {
        this.category_document = category_document;
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
        Client_document client_document = (Client_document) o;
        if (client_document.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), client_document.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Client_document{" +
            "id=" + getId() +
            ", doc_chemin='" + getDoc_chemin() + "'" +
            ", doc_libelle='" + getDoc_libelle() + "'" +
            ", doc_date='" + getDoc_date() + "'" +
            "}";
    }
}
