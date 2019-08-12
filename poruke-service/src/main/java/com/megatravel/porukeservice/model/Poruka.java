package com.megatravel.porukeservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.megatravel.porukeservice.dto.PorukaDTO;

@Entity
public class Poruka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
	private Date vreme;
    @Column(length = 512, nullable = false, updatable = false)
    private String sadrzaj;
    
    @Column(name = "korisnik_id", nullable = false, updatable = false)
    private Long korisnikId;
    @Column(name = "agent_id", nullable = false, updatable = false)
    private Long agentId;
    
    public Poruka() { }

	public Poruka(PorukaDTO porukaDTO) {
    	this.id = porukaDTO.getId();
    	this.sadrzaj = porukaDTO.getSadrzaj();
    	this.korisnikId = porukaDTO.getKorisnikId();
    	this.agentId = porukaDTO.getAgentId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getVreme() {
		return vreme;
	}

	public void setVreme(Date vreme) {
		this.vreme = vreme;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public Long getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
    
}
