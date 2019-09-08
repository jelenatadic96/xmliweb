package com.megatravel.agent.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	@OneToMany(mappedBy = "korisnik")
	private List<Rezervacija> rezervacije;
	
	public Korisnik() { }

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

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
}
