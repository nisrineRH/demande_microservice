package com.demande.open.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Category_document.
 */
@Entity
@Table(name = "category_document")
public class Category_document implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cat_numero")
    private Integer cat_numero;

    @Column(name = "cat_nom")
    private String cat_nom;

    @OneToMany(mappedBy = "category_document")
    private Set<Client_document> client_documents = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCat_numero() {
        return cat_numero;
    }

    public Category_document cat_numero(Integer cat_numero) {
        this.cat_numero = cat_numero;
        return this;
    }

    public void setCat_numero(Integer cat_numero) {
        this.cat_numero = cat_numero;
    }

    public String getCat_nom() {
        return cat_nom;
    }

    public Category_document cat_nom(String cat_nom) {
        this.cat_nom = cat_nom;
        return this;
    }

    public void setCat_nom(String cat_nom) {
        this.cat_nom = cat_nom;
    }

    public Set<Client_document> getClient_documents() {
        return client_documents;
    }

    public Category_document client_documents(Set<Client_document> client_documents) {
        this.client_documents = client_documents;
        return this;
    }

    public Category_document addClient_document(Client_document client_document) {
        this.client_documents.add(client_document);
        client_document.setCategory_document(this);
        return this;
    }

    public Category_document removeClient_document(Client_document client_document) {
        this.client_documents.remove(client_document);
        client_document.setCategory_document(null);
        return this;
    }

    public void setClient_documents(Set<Client_document> client_documents) {
        this.client_documents = client_documents;
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
        Category_document category_document = (Category_document) o;
        if (category_document.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), category_document.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Category_document{" +
            "id=" + getId() +
            ", cat_numero=" + getCat_numero() +
            ", cat_nom='" + getCat_nom() + "'" +
            "}";
    }
}
