package com.megatravel.korisniciservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.megatravel.korisniciservice.dto.KorisnikDTO;

@Entity
public class Korisnik {

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
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusKorisnika statusKorisnika;
	
	public Korisnik() { }

	public Korisnik(KorisnikDTO korisnikDTO) {
		this.id = korisnikDTO.getId();
		this.ime = korisnikDTO.getIme();
		this.prezime = korisnikDTO.getPrezime();
		this.mejl = korisnikDTO.getMejl();
		this.lozinka = korisnikDTO.getLozinka();
		this.statusKorisnika = korisnikDTO.getStatus();
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

	public StatusKorisnika getStatusKorisnika() {
		return statusKorisnika;
	}

	public void setStatusKorisnika(StatusKorisnika statusKorisnika) {
		this.statusKorisnika = statusKorisnika;
	}
	
}
