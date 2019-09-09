package com.megatravel.smestajservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.megatravel.smestajservice.dto.SmestajnaJedinicaDTO;

@Entity
public class SmestajnaJedinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 512, nullable = false)
    private String opis;
    @Column(nullable = false)
    @Min(1)
    private int kapacitet;
    @Column(nullable = false)
    @Min(-1)
    @Max(28)
    private int brojDanaZaOtkazivanje;
    @Min(0)
    @Max(5)
    private double ocena;
    @Column(length = 512, nullable = false)
    private String putanjaDoSlike;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Kategorija kategorija;
    
    @OneToMany(mappedBy = "smestajnaJedinica", fetch = FetchType.EAGER)
    private List<Cenovnik> cenovnici;
    
    @ManyToOne
    private TipSmestaja tip;
    
    @OneToMany(mappedBy = "smestajnaJedinica")
    private List<SpojUslugaJedinica> spojeviSaUslugama;
    
    @ManyToOne
    private Adresa adresa;
    
    @OneToMany(mappedBy = "smestajnaJedinica")
    private List<Rezervacija> rezervacije;
    
    @Transient
    private double trenutnaCenaPoNoci;
    
    public SmestajnaJedinica() { }

	public SmestajnaJedinica(SmestajnaJedinicaDTO smestajnaJedinicaDTO) {
    	this.opis = smestajnaJedinicaDTO.getOpis();
    	this.kapacitet = smestajnaJedinicaDTO.getKapacitet();
    	this.brojDanaZaOtkazivanje = smestajnaJedinicaDTO.getBrojDanaZaOtkazivanje();
    	this.ocena = smestajnaJedinicaDTO.getOcena();
    	this.kategorija = smestajnaJedinicaDTO.getKategorija();
	}

	public SmestajnaJedinica(com.megatravel.smestajservice.soap.dto.SmestajnaJedinicaDTO smestajDTO) {
    	this.opis = smestajDTO.getOpis();
    	this.kapacitet = smestajDTO.getKapacitet();
    	this.brojDanaZaOtkazivanje = smestajDTO.getBrojDanaZaOtkazivanje();
    	this.ocena = smestajDTO.getOcena();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public int getBrojDanaZaOtkazivanje() {
		return brojDanaZaOtkazivanje;
	}

	public void setBrojDanaZaOtkazivanje(int brojDanaZaOtkazivanje) {
		this.brojDanaZaOtkazivanje = brojDanaZaOtkazivanje;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public String getPutanjaDoSlike() {
		return putanjaDoSlike;
	}

	public void setPutanjaDoSlike(String putanjaDoSlike) {
		this.putanjaDoSlike = putanjaDoSlike;
	}

	public List<Cenovnik> getCenovnici() {
		return cenovnici;
	}

	public void setCenovnici(List<Cenovnik> cenovnici) {
		this.cenovnici = cenovnici;
	}

	public TipSmestaja getTip() {
		return tip;
	}

	public void setTip(TipSmestaja tip) {
		this.tip = tip;
	}

	public List<SpojUslugaJedinica> getSpojeviSaUslugama() {
		return spojeviSaUslugama;
	}

	public void setSpojeviSaUslugama(List<SpojUslugaJedinica> spojeviSaUslugama) {
		this.spojeviSaUslugama = spojeviSaUslugama;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public double getTrenutnaCenaPoNoci() {
		return trenutnaCenaPoNoci;
	}

	public void setTrenutnaCenaPoNoci(double trenutnaCenaPoNoci) {
		this.trenutnaCenaPoNoci = trenutnaCenaPoNoci;
	}
    
}
