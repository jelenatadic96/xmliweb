package com.megatravel.korisniciservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.megatravel.korisniciservice.dto.AgentDTO;

@Entity
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(length = 32, nullable = false)
	private String ime;
	@Column(length = 32, nullable = false)
	private String prezime;
	@Column(length = 32, nullable = false, unique = true)
	private String mejl;
	@Column(length = 32, nullable = false)
	private String lozinka;
	@Column(length = 32, nullable = false)
	private String poslovniMaticniBroj;
	
	@OneToMany(mappedBy = "agent")
	private List<SpojAgentSmestaj> smestaji;
	
	public Agent() { }

	public Agent(AgentDTO agentDTO) {
		this.id = agentDTO.getId();
		this.ime = agentDTO.getIme();
		this.prezime = agentDTO.getPrezime();
		this.mejl = agentDTO.getMejl();
		this.lozinka = agentDTO.getLozinka();
		this.poslovniMaticniBroj = agentDTO.getPoslovniMaticniBroj();
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

	public List<SpojAgentSmestaj> getSmestaji() {
		return smestaji;
	}

	public void setSmestaji(List<SpojAgentSmestaj> smestaji) {
		this.smestaji = smestaji;
	}
	
}
