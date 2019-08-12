package com.megatravel.smestajservice.dto;

import com.megatravel.smestajservice.model.Adresa;

public class AdresaDTO {

    private Long id;
    private String zemlja;
    private String grad;
    private double geografskaDuzina;
    private double geografskaSirina;
	
	public AdresaDTO() { }
	
	public AdresaDTO(Adresa adresa) {
		this.id = adresa.getId();
		this.zemlja = adresa.getZemlja();
		this.grad = adresa.getGrad();
		this.geografskaDuzina = adresa.getGeografskaDuzina();
		this.geografskaSirina = adresa.getGeografskaSirina();
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

}
