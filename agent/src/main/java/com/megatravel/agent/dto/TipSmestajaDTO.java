package com.megatravel.agent.dto;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.agent.model.TipSmestaja;

public class TipSmestajaDTO {

    private Long id;
    private String naziv;
    private String opis;
	
    public TipSmestajaDTO() { }
    
    public TipSmestajaDTO(TipSmestaja tipSmestaja) {
    	this.id = tipSmestaja.getId();
    	this.naziv = tipSmestaja.getNaziv();
    	this.opis = tipSmestaja.getOpis();
    }

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

	public static List<TipSmestajaDTO> transformisi(List<TipSmestaja> tipovi) {
		List<TipSmestajaDTO> rezultat = new ArrayList<>();
		for(TipSmestaja tip : tipovi) {
			rezultat.add(new TipSmestajaDTO(tip));
		}
		return rezultat;
	}
    
}
