package com.megatravel.smestajservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.smestajservice.model.Usluga;

public class UslugaDTO {

    private Long id;
    private String naziv;
    private String opis;
	
    public UslugaDTO() { }
    
    public UslugaDTO(Usluga usluga) {
    	this.id = usluga.getId();
    	this.naziv = usluga.getNaziv();
    	this.opis = usluga.getOpis();
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

	public static List<UslugaDTO> transformisi(List<Usluga> usluge) {
		List<UslugaDTO> rezultat = new ArrayList<>();
		for(Usluga usluga : usluge) {
			rezultat.add(new UslugaDTO(usluga));
		}
		return rezultat;
	}
    
}
