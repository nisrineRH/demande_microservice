package com.demande.open.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Demande entity.
 */
public class DemandeDTO implements Serializable {

    private Long id;

    private Integer dm_numero;

    private String dm_libelle;

    private String dm_statu;

    private String dm_type;

    private String dm_priorite;

    private Integer intervenant;

    private String description;

    private Boolean visibleSurInternet;

    private LocalDate dateLivraisonSouhaitee;

    private LocalDate dateAccordDevis;


    private Long clientId;

    private Long histouriqueStatutDemandeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDm_numero() {
        return dm_numero;
    }

    public void setDm_numero(Integer dm_numero) {
        this.dm_numero = dm_numero;
    }

    public String getDm_libelle() {
        return dm_libelle;
    }

    public void setDm_libelle(String dm_libelle) {
        this.dm_libelle = dm_libelle;
    }

    public String getDm_statu() {
        return dm_statu;
    }

    public void setDm_statu(String dm_statu) {
        this.dm_statu = dm_statu;
    }

    public String getDm_type() {
        return dm_type;
    }

    public void setDm_type(String dm_type) {
        this.dm_type = dm_type;
    }

    public String getDm_priorite() {
        return dm_priorite;
    }

    public void setDm_priorite(String dm_priorite) {
        this.dm_priorite = dm_priorite;
    }

    public Integer getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Integer intervenant) {
        this.intervenant = intervenant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isVisibleSurInternet() {
        return visibleSurInternet;
    }

    public void setVisibleSurInternet(Boolean visibleSurInternet) {
        this.visibleSurInternet = visibleSurInternet;
    }

    public LocalDate getDateLivraisonSouhaitee() {
        return dateLivraisonSouhaitee;
    }

    public void setDateLivraisonSouhaitee(LocalDate dateLivraisonSouhaitee) {
        this.dateLivraisonSouhaitee = dateLivraisonSouhaitee;
    }

    public LocalDate getDateAccordDevis() {
        return dateAccordDevis;
    }

    public void setDateAccordDevis(LocalDate dateAccordDevis) {
        this.dateAccordDevis = dateAccordDevis;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getHistouriqueStatutDemandeId() {
        return histouriqueStatutDemandeId;
    }

    public void setHistouriqueStatutDemandeId(Long histouriqueStatutDemandeId) {
        this.histouriqueStatutDemandeId = histouriqueStatutDemandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DemandeDTO demandeDTO = (DemandeDTO) o;
        if (demandeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demandeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DemandeDTO{" +
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
            ", dateAccordDevis='" + getDateAccordDevis() + "'" +
            ", client=" + getClientId() +
            ", histouriqueStatutDemande=" + getHistouriqueStatutDemandeId() +
            "}";
    }
}
