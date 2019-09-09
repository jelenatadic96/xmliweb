package com.megatravel.korisniciservice.dto;

public class ZahtevZaLoginDTO {

	private String mejl;
	private String lozinka;
	
	public ZahtevZaLoginDTO() { }

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
	
}
