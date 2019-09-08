package com.megatravel.agent.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import com.megatravel.agent.dto.CenovnikDTO;

@Entity
public class Cenovnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    @Min(1)
    private double cenaPoNoci;
    @Column(nullable = false, updatable = false)
	private LocalDate prviDanVazenja;
    @Column(nullable = false, updatable = false)
	private LocalDate poslednjiDanVazenja;
    
    @ManyToOne
    private SmestajnaJedinica smestajnaJedinica;
    
    public Cenovnik() { }

	public Cenovnik(CenovnikDTO cenovnik) {
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

	public SmestajnaJedinica getSmestajnaJedinica() {
		return smestajnaJedinica;
	}

	public void setSmestajnaJedinica(SmestajnaJedinica smestajnaJedinica) {
		this.smestajnaJedinica = smestajnaJedinica;
	}
    
}
