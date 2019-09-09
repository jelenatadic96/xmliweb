package com.megatravel.agent.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private double ukupnaCena;
    @Column(nullable = false, updatable = false)
	private LocalDate prviDanRezervacije;
    @Column(nullable = false, updatable = false)
	private LocalDate poslednjiDanRezervacije;
    @Column(nullable = false)
    private boolean realizovana;
    
    @ManyToOne
    private Korisnik korisnik;
    
    @OneToOne(mappedBy = "rezervacija")
    private Utisak utisak;
    
    @ManyToOne
    private SmestajnaJedinica smestajnaJedinica;
    
    public Rezervacija() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public LocalDate getPrviDanRezervacije() {
		return prviDanRezervacije;
	}

	public void setPrviDanRezervacije(LocalDate prviDanRezervacije) {
		this.prviDanRezervacije = prviDanRezervacije;
	}

	public LocalDate getPoslednjiDanRezervacije() {
		return poslednjiDanRezervacije;
	}

	public void setPoslednjiDanRezervacije(LocalDate poslednjiDanRezervacije) {
		this.poslednjiDanRezervacije = poslednjiDanRezervacije;
	}

	public SmestajnaJedinica getSmestajnaJedinica() {
		return smestajnaJedinica;
	}

	public void setSmestajnaJedinica(SmestajnaJedinica smestajnaJedinica) {
		this.smestajnaJedinica = smestajnaJedinica;
	}

	public boolean isRealizovana() {
		return realizovana;
	}

	public void setRealizovana(boolean realizovana) {
		this.realizovana = realizovana;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Utisak getUtisak() {
		return utisak;
	}

	public void setUtisak(Utisak utisak) {
		this.utisak = utisak;
	}
    
}
