package com.megatravel.smestajservice.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    @Column(nullable = true, unique = true)
    private Long utisakId;
    @Column(nullable = true)
    private Long korisnikId;
    @Column(nullable = false)
    private boolean realizovana;
    
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

	public Long getUtisakId() {
		return utisakId;
	}

	public void setUtisakId(Long utisakId) {
		this.utisakId = utisakId;
	}

	public boolean isRealizovana() {
		return realizovana;
	}

	public void setRealizovana(boolean realizovana) {
		this.realizovana = realizovana;
	}

	public Long getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}
    
}
