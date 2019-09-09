package com.megatravel.agent.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.megatravel.agent.dto.PorukaDTO;

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
    
    @ManyToOne
    private Korisnik korisnik;
    
    @ManyToOne
    private Agent agent;
    
    public Poruka() { }

	public Poruka(PorukaDTO porukaDTO) {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	this.id = porukaDTO.getId();
    	try {
			this.vreme = new SimpleDateFormat("yyyy-MM-dd").parse(porukaDTO.getVreme());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	this.sadrzaj = porukaDTO.getSadrzaj();
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

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
    
}
