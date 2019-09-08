package com.megatravel.agent.dto;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.agent.model.Utisak;

public class UtisakDTO {
	
    private Long id;
    private String komentar;
	private boolean komentarOdobren;
    private int ocena;
    private RezervacijaDTO rezervacijaDTO;
    
    public UtisakDTO() { }
    
    public UtisakDTO(Utisak utisak) {
    	this.id = utisak.getId();
    	this.komentar = utisak.getKomentar();
    	this.komentarOdobren = utisak.isKomentarOdobren();
    	this.ocena = utisak.getOcena();
    	this.rezervacijaDTO = new RezervacijaDTO(utisak.getRezervacija());
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

	public static List<UtisakDTO> transformisi(List<Utisak> utisci) {
		List<UtisakDTO> rezultat = new ArrayList<>();
		for(Utisak utisak : utisci) {
			rezultat.add(new UtisakDTO(utisak));
		}
		return rezultat;
	}

	public RezervacijaDTO getRezervacijaDTO() {
		return rezervacijaDTO;
	}

	public void setRezervacijaDTO(RezervacijaDTO rezervacijaDTO) {
		this.rezervacijaDTO = rezervacijaDTO;
	}
	
}
