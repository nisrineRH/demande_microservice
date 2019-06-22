package com.demande.open.domain;



import javax.persistence.*;

import java.io.Serializable;
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
            "}";
    }
}
