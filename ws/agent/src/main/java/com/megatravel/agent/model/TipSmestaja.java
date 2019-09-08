package com.megatravel.agent.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipSmestaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 32, nullable = false)
    private String naziv;
    @Column(length = 512, nullable = false)
    private String opis;
	
    @OneToMany(mappedBy = "tip")
    private List<SmestajnaJedinica> smestajneJedinice;
    
    public TipSmestaja() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<SmestajnaJedinica> getSmestajneJedinice() {
		return smestajneJedinice;
	}

	public void setSmestajneJedinice(List<SmestajnaJedinica> smestajneJedinice) {
		this.smestajneJedinice = smestajneJedinice;
	}

	public void prekopiraj(TipSmestaja noviTip) {
    	this.naziv = noviTip.getNaziv();
    	this.opis = noviTip.getOpis();
	}
    
}
