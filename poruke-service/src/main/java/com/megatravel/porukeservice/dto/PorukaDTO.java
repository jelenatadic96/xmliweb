package com.megatravel.porukeservice.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.megatravel.porukeservice.model.Poruka;
import com.megatravel.porukeservice.model.PorukaComparator;

public class PorukaDTO {

    private Long id;
	private Date vreme;
    private String sadrzaj;
    private Long korisnikId;
    private Long agentId;
	
    public PorukaDTO() { }
    
    public PorukaDTO(Poruka poruka) {
    	this.id = poruka.getId();
    	this.vreme = poruka.getVreme();
    	this.sadrzaj = poruka.getSadrzaj();
    	this.korisnikId = poruka.getKorisnikId();
    	this.agentId = poruka.getAgentId();
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
    
	public static List<PorukaDTO> transformisi(List<Poruka> poruke) {
		List<PorukaDTO> rezultat = new ArrayList<PorukaDTO>();
		for(Poruka poruka : poruke) {
			rezultat.add(new PorukaDTO(poruka));
		}
		rezultat.sort(new PorukaComparator());
		return rezultat;
	}
	
}
