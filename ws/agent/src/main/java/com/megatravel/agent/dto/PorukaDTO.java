package com.megatravel.agent.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.megatravel.agent.model.Poruka;
import com.megatravel.agent.model.PorukaComparator;

public class PorukaDTO {

    private Long id;
	private Date vreme;
    private String sadrzaj;
    private KorisnikDTO korisnikDTO;
    private AgentDTO agentDTO;
	
    public PorukaDTO() { }
    
    public PorukaDTO(Poruka poruka) {
    	this.id = poruka.getId();
    	this.vreme = poruka.getVreme();
    	this.sadrzaj = poruka.getSadrzaj();
    	this.korisnikDTO = new KorisnikDTO(poruka.getKorisnik());
    	this.agentDTO = new AgentDTO(poruka.getAgent());
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
    
	public static List<PorukaDTO> transformisi(List<Poruka> poruke) {
		List<PorukaDTO> rezultat = new ArrayList<PorukaDTO>();
		for(Poruka poruka : poruke) {
			rezultat.add(new PorukaDTO(poruka));
		}
		rezultat.sort(new PorukaComparator());
		return rezultat;
	}

	public KorisnikDTO getKorisnikDTO() {
		return korisnikDTO;
	}

	public void setKorisnikDTO(KorisnikDTO korisnikDTO) {
		this.korisnikDTO = korisnikDTO;
	}

	public AgentDTO getAgentDTO() {
		return agentDTO;
	}

	public void setAgentDTO(AgentDTO agentDTO) {
		this.agentDTO = agentDTO;
	}
	
}
