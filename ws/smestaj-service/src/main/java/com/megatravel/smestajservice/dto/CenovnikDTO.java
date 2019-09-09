package com.megatravel.smestajservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.megatravel.smestajservice.model.Cenovnik;

public class CenovnikDTO {

	private Long id;
    private double cenaPoNoci;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String prviDanVazenja;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String poslednjiDanVazenja;
	
	public CenovnikDTO() { }
	
	public CenovnikDTO(Cenovnik cenovnik) {
		this.id = cenovnik.getId();
		this.cenaPoNoci = cenovnik.getCenaPoNoci();
		this.prviDanVazenja = cenovnik.getPrviDanVazenja().toString();
		this.poslednjiDanVazenja = cenovnik.getPoslednjiDanVazenja().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCenaPoNoci() {
		return cenaPoNoci;
	}

	public void setCenaPoNoci(double cenaPoNoci) {
		this.cenaPoNoci = cenaPoNoci;
	}

	public static List<CenovnikDTO> transformisi(List<Cenovnik> cenovnici) {
		List<CenovnikDTO> rezultat = new ArrayList<CenovnikDTO>();
		for(Cenovnik cenovnik : cenovnici) {
			rezultat.add(new CenovnikDTO(cenovnik));
		}
		return rezultat;
	}

	public String getPrviDanVazenja() {
		return prviDanVazenja;
	}

	public void setPrviDanVazenja(String prviDanVazenja) {
		this.prviDanVazenja = prviDanVazenja;
	}

	public String getPoslednjiDanVazenja() {
		return poslednjiDanVazenja;
	}

	public void setPoslednjiDanVazenja(String poslednjiDanVazenja) {
		this.poslednjiDanVazenja = poslednjiDanVazenja;
	}
}
