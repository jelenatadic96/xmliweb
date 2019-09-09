package com.megatravel.smestajservice.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.megatravel.smestajservice.model.Rezervacija;

public class RezervacijaDTO {

    private Long id;
    private double ukupnaCena;
	private LocalDate prviDanRezervacije;
	private LocalDate poslednjiDanRezervacije;
    private Long utisakId;
    private Long korisnikId;
    private boolean realizovana;
    private SmestajnaJedinicaDTO smestajnaJedinicaDTO;
	
    public RezervacijaDTO() { }
    
    public RezervacijaDTO(Rezervacija rezervacija) {
    	this.id = rezervacija.getId();
    	this.ukupnaCena = rezervacija.getUkupnaCena();
    	this.prviDanRezervacije = rezervacija.getPrviDanRezervacije();
    	this.poslednjiDanRezervacije = rezervacija.getPoslednjiDanRezervacije();
    	this.utisakId = rezervacija.getUtisakId();
    	this.korisnikId = rezervacija.getKorisnikId();
    	this.realizovana = rezervacija.isRealizovana();
    	this.smestajnaJedinicaDTO = new SmestajnaJedinicaDTO(rezervacija.getSmestajnaJedinica());
    	this.smestajnaJedinicaDTO.setCenovnici(null);
    }

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

	public SmestajnaJedinicaDTO getSmestajnaJedinicaDTO() {
		return smestajnaJedinicaDTO;
	}

	public void setSmestajnaJedinicaDTO(SmestajnaJedinicaDTO smestajnaJedinicaDTO) {
		this.smestajnaJedinicaDTO = smestajnaJedinicaDTO;
	}
	
	public static List<RezervacijaDTO> transformisi(List<Rezervacija> rezervacije) {
		List<RezervacijaDTO> rezultat = new ArrayList<>();
		for(Rezervacija rezervacija : rezervacije) {
			rezultat.add(new RezervacijaDTO(rezervacija));
		}
		return rezultat;
	}
    
}
