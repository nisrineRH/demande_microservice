package com.demande.open.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the HistouriqueStatutDemande entity.
 */
public class HistouriqueStatutDemandeDTO implements Serializable {

    private Long id;

    private LocalDate userModification;

    private String msgMail;

    private String desinataireDuMail;

    private String dm_statut;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getUserModification() {
        return userModification;
    }

    public void setUserModification(LocalDate userModification) {
        this.userModification = userModification;
    }

    public String getMsgMail() {
        return msgMail;
    }

    public void setMsgMail(String msgMail) {
        this.msgMail = msgMail;
    }

    public String getDesinataireDuMail() {
        return desinataireDuMail;
    }

    public void setDesinataireDuMail(String desinataireDuMail) {
        this.desinataireDuMail = desinataireDuMail;
    }

    public String getDm_statut() {
        return dm_statut;
    }

    public void setDm_statut(String dm_statut) {
        this.dm_statut = dm_statut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO = (HistouriqueStatutDemandeDTO) o;
        if (histouriqueStatutDemandeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), histouriqueStatutDemandeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HistouriqueStatutDemandeDTO{" +
            "id=" + getId() +
            ", userModification='" + getUserModification() + "'" +
            ", msgMail='" + getMsgMail() + "'" +
            ", desinataireDuMail='" + getDesinataireDuMail() + "'" +
            ", dm_statut='" + getDm_statut() + "'" +
            "}";
    }
}
