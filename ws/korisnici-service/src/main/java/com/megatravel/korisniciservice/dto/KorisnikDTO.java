package com.megatravel.korisniciservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.korisniciservice.model.Korisnik;
import com.megatravel.korisniciservice.model.StatusKorisnika;

public class KorisnikDTO {

    private Long id;
	private String ime;
	private String prezime;
	private String mejl;
	private String lozinka;
	private StatusKorisnika status;
	private String role;
	
	public KorisnikDTO() { }

	public KorisnikDTO(Korisnik korisnik) {
		this.id = korisnik.getId();
		this.ime = korisnik.getIme();
		this.prezime = korisnik.getPrezime();
		this.mejl = korisnik.getMejl();
		this.lozinka = korisnik.getLozinka();
		this.status = korisnik.getStatusKorisnika();
		this.role = korisnik.getRole();
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

	public StatusKorisnika getStatus() {
		return status;
	}

	public void setStatus(StatusKorisnika status) {
		this.status = status;
	}
	
	public static List<KorisnikDTO> transformisi(List<Korisnik> korisnici) {
		List<KorisnikDTO> rezultat = new ArrayList<>();
		for(Korisnik korisnik : korisnici) {
			rezultat.add(new KorisnikDTO(korisnik));
		}
		return rezultat;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
