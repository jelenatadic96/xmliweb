package com.megatravel.utisakservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;

import com.megatravel.utisakservice.dto.UtisakDTO;

@Entity
public class Utisak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 512, nullable = true)
    private String komentar;
    @Column(nullable = false)
	private boolean komentarOdobren;
    @Column(nullable = false)
    @Max(5)
    private int ocena;
    @Column(nullable = false, updatable = false, unique = true)
    private Long rezervacijaId;
    
    public Utisak() { }

	public Utisak(UtisakDTO utisakDTO) {
    	this.id = utisakDTO.getId();
    	this.komentar = utisakDTO.getKomentar();
    	this.komentarOdobren = utisakDTO.isKomentarOdobren();
    	this.ocena = utisakDTO.getOcena();
    	this.rezervacijaId = utisakDTO.getRezervacijaId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public boolean isKomentarOdobren() {
		return komentarOdobren;
	}

	public void setKomentarOdobren(boolean komentarOdobren) {
		this.komentarOdobren = komentarOdobren;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Long getRezervacijaId() {
		return rezervacijaId;
	}

	public void setRezervacijaId(Long rezervacijaId) {
		this.rezervacijaId = rezervacijaId;
	}
    
}
