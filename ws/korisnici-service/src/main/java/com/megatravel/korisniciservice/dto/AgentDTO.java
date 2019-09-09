package com.megatravel.korisniciservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.korisniciservice.model.Agent;

public class AgentDTO {

    private Long id;
	private String ime;
	private String prezime;
	private String mejl;
	private String lozinka;
	private String poslovniMaticniBroj;
	private AdresaDTO adresaDTO;
	
	public AgentDTO() { }
	
	public AgentDTO(Agent agent) {
		this.id = agent.getId();
		this.ime = agent.getIme();
		this.prezime = agent.getPrezime();
		this.mejl = agent.getMejl();
		this.lozinka = agent.getLozinka();
		this.poslovniMaticniBroj = agent.getPoslovniMaticniBroj();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getMejl() {
		return mejl;
	}

	public void setMejl(String mejl) {
		this.mejl = mejl;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPoslovniMaticniBroj() {
		return poslovniMaticniBroj;
	}

	public void setPoslovniMaticniBroj(String poslovniMaticniBroj) {
		this.poslovniMaticniBroj = poslovniMaticniBroj;
	}

	public static List<AgentDTO> transformisi(List<Agent> agenti) {
		List<AgentDTO> rezultat = new ArrayList<>();
		for(Agent agent : agenti) {
			rezultat.add(new AgentDTO(agent));
		}
		return rezultat;
	}

	public AdresaDTO getAdresaDTO() {
		return adresaDTO;
	}

	public void setAdresaDTO(AdresaDTO adresaDTO) {
		this.adresaDTO = adresaDTO;
	}
	
}
