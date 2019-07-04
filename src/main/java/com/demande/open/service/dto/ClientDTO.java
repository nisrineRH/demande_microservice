package com.demande.open.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Client entity.
 */
public class ClientDTO implements Serializable {

    private Long id;

    private String client_nom;

    private Integer client_numero;

    private Boolean faitSaMiseEnProduction;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient_nom() {
        return client_nom;
    }

    public void setClient_nom(String client_nom) {
        this.client_nom = client_nom;
    }

    public Integer getClient_numero() {
        return client_numero;
    }

    public void setClient_numero(Integer client_numero) {
        this.client_numero = client_numero;
    }

    public Boolean isFaitSaMiseEnProduction() {
        return faitSaMiseEnProduction;
    }

    public void setFaitSaMiseEnProduction(Boolean faitSaMiseEnProduction) {
        this.faitSaMiseEnProduction = faitSaMiseEnProduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientDTO clientDTO = (ClientDTO) o;
        if (clientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + getId() +
            ", client_nom='" + getClient_nom() + "'" +
            ", client_numero=" + getClient_numero() +
            ", faitSaMiseEnProduction='" + isFaitSaMiseEnProduction() + "'" +
            "}";
    }
}
