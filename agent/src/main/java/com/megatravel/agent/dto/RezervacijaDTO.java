package com.megatravel.agent.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.megatravel.agent.model.Rezervacija;

public class RezervacijaDTO {

    private Long id;
    private double ukupnaCena;
	private LocalDate prviDanRezervacije;
	private LocalDate poslednjiDanRezervacije;
    private Long utisakId;
    private KorisnikDTO korisnikDTO;
    private boolean realizovana;
    private SmestajnaJedinicaDTO smestajnaJedinicaDTO;
	
    public RezervacijaDTO() { }
    
    public RezervacijaDTO(Rezervacija rezervacija) {
    	this.id = rezervacija.getId();
    	this.ukupnaCena = rezervacija.getUkupnaCena();
    	this.prviDanRezervacije = rezervacija.getPrviDanRezervacije();
    	this.poslednjiDanRezervacije = rezervacija.getPoslednjiDanRezervacije();
    	if(rezervacija.getUtisak() != null) {
    		this.utisakId = rezervacija.getUtisak().getId();
    	}
    	if(rezervacija.getKorisnik() != null) {
    		this.korisnikDTO = new KorisnikDTO(rezervacija.getKorisnik());
    	}
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

	public KorisnikDTO getKorisnikDTO() {
		return korisnikDTO;
	}

	public void setKorisnikDTO(KorisnikDTO korisnikDTO) {
		this.korisnikDTO = korisnikDTO;
	}
    
}
