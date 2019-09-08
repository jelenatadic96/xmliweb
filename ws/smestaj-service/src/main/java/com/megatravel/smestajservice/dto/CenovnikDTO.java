package com.megatravel.smestajservice.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.megatravel.smestajservice.model.Cenovnik;

public class CenovnikDTO {

    private Long id;
    private double cenaPoNoci;
	private LocalDate prviDanVazenja;
	private LocalDate poslednjiDanVazenja;
	
	public CenovnikDTO() { }
	
	public CenovnikDTO(Cenovnik cenovnik) {
		this.id = cenovnik.getId();
		this.cenaPoNoci = cenovnik.getCenaPoNoci();
		this.prviDanVazenja = cenovnik.getPrviDanVazenja();
		this.poslednjiDanVazenja = cenovnik.getPoslednjiDanVazenja();
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

	public LocalDate getPrviDanVazenja() {
		return prviDanVazenja;
	}

	public void setPrviDanVazenja(LocalDate prviDanVazenja) {
		this.prviDanVazenja = prviDanVazenja;
	}

	public LocalDate getPoslednjiDanVazenja() {
		return poslednjiDanVazenja;
	}

	public void setPoslednjiDanVazenja(LocalDate poslednjiDanVazenja) {
		this.poslednjiDanVazenja = poslednjiDanVazenja;
	}

	public static List<CenovnikDTO> transformisi(List<Cenovnik> cenovnici) {
		List<CenovnikDTO> rezultat = new ArrayList<CenovnikDTO>();
		for(Cenovnik cenovnik : cenovnici) {
			rezultat.add(new CenovnikDTO(cenovnik));
		}
		return rezultat;
	}
	
}
