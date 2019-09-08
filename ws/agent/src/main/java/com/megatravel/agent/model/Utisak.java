package com.megatravel.agent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Utisak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 512, nullable = true, updatable = false)
    private String komentar;
    @Column(nullable = false)
	private boolean komentarOdobren;
    @Column(nullable = false, updatable = false)
    @Min(1)
    @Max(5)
    private int ocena;
    
    @OneToOne
    private Rezervacija rezervacija;
    
    public Utisak() { }

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

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}
    
}
