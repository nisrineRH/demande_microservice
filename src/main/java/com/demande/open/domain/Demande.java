package com.demande.open.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Demande.
 */
@Entity
@Table(name = "demande")
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dm_numero")
    private Integer dm_numero;

    @Column(name = "dm_libelle")
    private String dm_libelle;

    @Column(name = "dm_statu")
    private String dm_statu;

    @Column(name = "dm_type")
    private String dm_type;

    @Column(name = "dm_priorite")
    private String dm_priorite;

    @Column(name = "intervenant")
    private Integer intervenant;

    @Column(name = "description")
    private String description;

    @Column(name = "visible_sur_internet")
    private Boolean visibleSurInternet;

    @Column(name = "date_livraison_souhaitee")
    private LocalDate dateLivraisonSouhaitee;

    @ManyToOne
    @JsonIgnoreProperties("demandes")
    private Client client;

    @OneToOne
    @JoinColumn(unique = true)
    private HistouriqueStatutDemande histouriqueStatutDemande;

    @OneToMany(mappedBy = "demande")
    private Set<Demande_document> demande_documents = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDm_numero() {
        return dm_numero;
    }

    public Demande dm_numero(Integer dm_numero) {
        this.dm_numero = dm_numero;
        return this;
    }

    public void setDm_numero(Integer dm_numero) {
        this.dm_numero = dm_numero;
    }

    public String getDm_libelle() {
        return dm_libelle;
    }

    public Demande dm_libelle(String dm_libelle) {
        this.dm_libelle = dm_libelle;
        return this;
    }

    public void setDm_libelle(String dm_libelle) {
        this.dm_libelle = dm_libelle;
    }

    public String getDm_statu() {
        return dm_statu;
    }

    public Demande dm_statu(String dm_statu) {
        this.dm_statu = dm_statu;
        return this;
    }

    public void setDm_statu(String dm_statu) {
        this.dm_statu = dm_statu;
    }

    public String getDm_type() {
        return dm_type;
    }

    public Demande dm_type(String dm_type) {
        this.dm_type = dm_type;
        return this;
    }

    public void setDm_type(String dm_type) {
        this.dm_type = dm_type;
    }

    public String getDm_priorite() {
        return dm_priorite;
    }

    public Demande dm_priorite(String dm_priorite) {
        this.dm_priorite = dm_priorite;
        return this;
    }

    public void setDm_priorite(String dm_priorite) {
        this.dm_priorite = dm_priorite;
    }

    public Integer getIntervenant() {
        return intervenant;
    }

    public Demande intervenant(Integer intervenant) {
        this.intervenant = intervenant;
        return this;
    }

    public void setIntervenant(Integer intervenant) {
        this.intervenant = intervenant;
    }

    public String getDescription() {
        return description;
    }

    public Demande description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isVisibleSurInternet() {
        return visibleSurInternet;
    }

    public Demande visibleSurInternet(Boolean visibleSurInternet) {
        this.visibleSurInternet = visibleSurInternet;
        return this;
    }

    public void setVisibleSurInternet(Boolean visibleSurInternet) {
        this.visibleSurInternet = visibleSurInternet;
    }

    public LocalDate getDateLivraisonSouhaitee() {
        return dateLivraisonSouhaitee;
    }

    public Demande dateLivraisonSouhaitee(LocalDate dateLivraisonSouhaitee) {
        this.dateLivraisonSouhaitee = dateLivraisonSouhaitee;
        return this;
    }

    public void setDateLivraisonSouhaitee(LocalDate dateLivraisonSouhaitee) {
        this.dateLivraisonSouhaitee = dateLivraisonSouhaitee;
    }

    public Client getClient() {
        return client;
    }

    public Demande client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public HistouriqueStatutDemande getHistouriqueStatutDemande() {
        return histouriqueStatutDemande;
    }

    public Demande histouriqueStatutDemande(HistouriqueStatutDemande histouriqueStatutDemande) {
        this.histouriqueStatutDemande = histouriqueStatutDemande;
        return this;
    }

    public void setHistouriqueStatutDemande(HistouriqueStatutDemande histouriqueStatutDemande) {
        this.histouriqueStatutDemande = histouriqueStatutDemande;
    }

    public Set<Demande_document> getDemande_documents() {
        return demande_documents;
    }

    public Demande demande_documents(Set<Demande_document> demande_documents) {
        this.demande_documents = demande_documents;
        return this;
    }

    public Demande addDemande_document(Demande_document demande_document) {
        this.demande_documents.add(demande_document);
        demande_document.setDemande(this);
        return this;
    }

    public Demande removeDemande_document(Demande_document demande_document) {
        this.demande_documents.remove(demande_document);
        demande_document.setDemande(null);
        return this;
    }

    public void setDemande_documents(Set<Demande_document> demande_documents) {
        this.demande_documents = demande_documents;
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
        Demande demande = (Demande) o;
        if (demande.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demande.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Demande{" +
            "id=" + getId() +
            ", dm_numero=" + getDm_numero() +
            ", dm_libelle='" + getDm_libelle() + "'" +
            ", dm_statu='" + getDm_statu() + "'" +
            ", dm_type='" + getDm_type() + "'" +
            ", dm_priorite='" + getDm_priorite() + "'" +
            ", intervenant=" + getIntervenant() +
            ", description='" + getDescription() + "'" +
            ", visibleSurInternet='" + isVisibleSurInternet() + "'" +
            ", dateLivraisonSouhaitee='" + getDateLivraisonSouhaitee() + "'" +
            "}";
    }
}
