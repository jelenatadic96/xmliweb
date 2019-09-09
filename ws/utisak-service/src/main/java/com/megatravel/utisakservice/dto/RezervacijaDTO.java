package com.megatravel.utisakservice.dto;

import java.time.LocalDate;

public class RezervacijaDTO {

    private Long id;
    private double ukupnaCena;
	private LocalDate prviDanRezervacije;
	private LocalDate poslednjiDanRezervacije;
    private Long utisakId;
    private Long korisnikId;
    private boolean realizovana;

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

	public Long getUtisakId() {
		return utisakId;
	}

	public void setUtisakId(Long utisakId) {
		this.utisakId = utisakId;
	}

	public Long getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}

	public boolean isRealizovana() {
		return realizovana;
	}

	public void setRealizovana(boolean realizovana) {
		this.realizovana = realizovana;
	}
    
}
