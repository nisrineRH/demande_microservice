package com.demande.open.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "client_nom")
    private String client_nom;

    @Column(name = "client_numero")
    private Integer client_numero;

    @Column(name = "fait_sa_mise_en_production")
    private Boolean faitSaMiseEnProduction;

    @OneToMany(mappedBy = "client")
    private Set<Demande> demandes = new HashSet<>();
    @OneToMany(mappedBy = "client")
    private Set<Client_document> client_documents = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient_nom() {
        return client_nom;
    }

    public Client client_nom(String client_nom) {
        this.client_nom = client_nom;
        return this;
    }

    public void setClient_nom(String client_nom) {
        this.client_nom = client_nom;
    }

    public Integer getClient_numero() {
        return client_numero;
    }

    public Client client_numero(Integer client_numero) {
        this.client_numero = client_numero;
        return this;
    }

    public void setClient_numero(Integer client_numero) {
        this.client_numero = client_numero;
    }

    public Boolean isFaitSaMiseEnProduction() {
        return faitSaMiseEnProduction;
    }

    public Client faitSaMiseEnProduction(Boolean faitSaMiseEnProduction) {
        this.faitSaMiseEnProduction = faitSaMiseEnProduction;
        return this;
    }

    public void setFaitSaMiseEnProduction(Boolean faitSaMiseEnProduction) {
        this.faitSaMiseEnProduction = faitSaMiseEnProduction;
    }

    public Set<Demande> getDemandes() {
        return demandes;
    }

    public Client demandes(Set<Demande> demandes) {
        this.demandes = demandes;
        return this;
    }

    public Client addDemande(Demande demande) {
        this.demandes.add(demande);
        demande.setClient(this);
        return this;
    }

    public Client removeDemande(Demande demande) {
        this.demandes.remove(demande);
        demande.setClient(null);
        return this;
    }

    public void setDemandes(Set<Demande> demandes) {
        this.demandes = demandes;
    }

    public Set<Client_document> getClient_documents() {
        return client_documents;
    }

    public Client client_documents(Set<Client_document> client_documents) {
        this.client_documents = client_documents;
        return this;
    }

    public Client addClient_document(Client_document client_document) {
        this.client_documents.add(client_document);
        client_document.setClient(this);
        return this;
    }

    public Client removeClient_document(Client_document client_document) {
        this.client_documents.remove(client_document);
        client_document.setClient(null);
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
        Client client = (Client) o;
        if (client.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", client_nom='" + getClient_nom() + "'" +
            ", client_numero=" + getClient_numero() +
            ", faitSaMiseEnProduction='" + isFaitSaMiseEnProduction() + "'" +
            "}";
    }
}
