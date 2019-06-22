package com.demande.open.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A HistouriqueStatutDemande.
 */
@Entity
@Table(name = "histourique_statut_demande")
public class HistouriqueStatutDemande implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_modification")
    private LocalDate userModification;

    @Column(name = "msg_mail")
    private String msgMail;

    @Column(name = "desinataire_du_mail")
    private String desinataireDuMail;

    @Column(name = "dm_statut")
    private String dm_statut;

    @OneToOne(mappedBy = "histouriqueStatutDemande")
    @JsonIgnore
    private Demande demande;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getUserModification() {
        return userModification;
    }

    public HistouriqueStatutDemande userModification(LocalDate userModification) {
        this.userModification = userModification;
        return this;
    }

    public void setUserModification(LocalDate userModification) {
        this.userModification = userModification;
    }

    public String getMsgMail() {
        return msgMail;
    }

    public HistouriqueStatutDemande msgMail(String msgMail) {
        this.msgMail = msgMail;
        return this;
    }

    public void setMsgMail(String msgMail) {
        this.msgMail = msgMail;
    }

    public String getDesinataireDuMail() {
        return desinataireDuMail;
    }

    public HistouriqueStatutDemande desinataireDuMail(String desinataireDuMail) {
        this.desinataireDuMail = desinataireDuMail;
        return this;
    }

    public void setDesinataireDuMail(String desinataireDuMail) {
        this.desinataireDuMail = desinataireDuMail;
    }

    public String getDm_statut() {
        return dm_statut;
    }

    public HistouriqueStatutDemande dm_statut(String dm_statut) {
        this.dm_statut = dm_statut;
        return this;
    }

    public void setDm_statut(String dm_statut) {
        this.dm_statut = dm_statut;
    }

    public Demande getDemande() {
        return demande;
    }

    public HistouriqueStatutDemande demande(Demande demande) {
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
        HistouriqueStatutDemande histouriqueStatutDemande = (HistouriqueStatutDemande) o;
        if (histouriqueStatutDemande.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), histouriqueStatutDemande.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HistouriqueStatutDemande{" +
            "id=" + getId() +
            ", userModification='" + getUserModification() + "'" +
            ", msgMail='" + getMsgMail() + "'" +
            ", desinataireDuMail='" + getDesinataireDuMail() + "'" +
            ", dm_statut='" + getDm_statut() + "'" +
            "}";
    }
}
