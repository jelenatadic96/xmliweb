package com.megatravel.agent.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.megatravel.agent.dto.AdresaDTO;

@Entity
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64, nullable = false, updatable = false)
    private String zemlja;
    @Column(length = 64, nullable = false, updatable = false)
    private String grad;
    @Column(nullable = false, updatable = false)
    @Min(-90)
    @Max(90)
    private double geografskaDuzina;
    @Column(nullable = false, updatable = false)
    @Min(-180)
    @Max(180)
    private double geografskaSirina;
    
    @OneToMany(mappedBy = "adresa")
    private List<SmestajnaJedinica> smestajneJedinice;
    
    @OneToMany(mappedBy = "adresa")
    private List<Agent> agenti;
    
    public Adresa() { }

	public Adresa(AdresaDTO adresaDTO) {
		this.id = adresaDTO.getId();
		this.zemlja = adresaDTO.getZemlja();
		this.grad = adresaDTO.getGrad();
		this.geografskaDuzina = adresaDTO.getGeografskaDuzina();
		this.geografskaSirina = adresaDTO.getGeografskaSirina();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZemlja() {
		return zemlja;
	}

	public void setZemlja(String zemlja) {
		this.zemlja = zemlja;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public double getGeografskaDuzina() {
		return geografskaDuzina;
	}

	public void setGeografskaDuzina(double geografskaDuzina) {
		this.geografskaDuzina = geografskaDuzina;
	}

	public double getGeografskaSirina() {
		return geografskaSirina;
	}

	public void setGeografskaSirina(double geografskaSirina) {
		this.geografskaSirina = geografskaSirina;
	}

	public List<SmestajnaJedinica> getSmestajneJedinice() {
		return smestajneJedinice;
	}

	public void setSmestajneJedinice(List<SmestajnaJedinica> smestajneJedinice) {
		this.smestajneJedinice = smestajneJedinice;
	}

	public List<Agent> getAgenti() {
		return agenti;
	}

	public void setAgenti(List<Agent> agenti) {
		this.agenti = agenti;
	}
    
}
