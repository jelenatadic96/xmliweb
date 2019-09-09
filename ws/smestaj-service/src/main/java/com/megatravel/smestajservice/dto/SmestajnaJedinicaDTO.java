package com.megatravel.smestajservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.smestajservice.model.Kategorija;
import com.megatravel.smestajservice.model.SmestajnaJedinica;
import com.megatravel.smestajservice.model.SpojUslugaJedinica;
import com.megatravel.smestajservice.model.Usluga;

public class SmestajnaJedinicaDTO {

    private Long id;
    private String opis;
    private int kapacitet;
    private int brojDanaZaOtkazivanje;
    private double ocena;
    private Kategorija kategorija;
    private AdresaDTO adresaDTO;
    private List<CenovnikDTO> cenovnici;
    private TipSmestajaDTO tipDTO;
    private List<UslugaDTO> usluge;
    private double trenutnaCena;
    
    public SmestajnaJedinicaDTO() { }
    
    public SmestajnaJedinicaDTO(SmestajnaJedinica smestajnaJedinica) {
    	this.id = smestajnaJedinica.getId();
    	this.opis = smestajnaJedinica.getOpis();
    	this.kapacitet = smestajnaJedinica.getKapacitet();
    	this.brojDanaZaOtkazivanje = smestajnaJedinica.getBrojDanaZaOtkazivanje();
    	this.ocena = smestajnaJedinica.getOcena();
    	if(smestajnaJedinica.getAdresa() != null) this.adresaDTO = new AdresaDTO(smestajnaJedinica.getAdresa());
    	this.cenovnici = CenovnikDTO.transformisi(smestajnaJedinica.getCenovnici());
    	this.tipDTO = new TipSmestajaDTO(smestajnaJedinica.getTip());
    	this.kategorija = smestajnaJedinica.getKategorija();
    	this.usluge = UslugaDTO.transformisi(this.preuzmiUsluge(smestajnaJedinica.getSpojeviSaUslugama()));
    	this.trenutnaCena = smestajnaJedinica.getTrenutnaCenaPoNoci();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public int getBrojDanaZaOtkazivanje() {
		return brojDanaZaOtkazivanje;
	}

	public void setBrojDanaZaOtkazivanje(int brojDanaZaOtkazivanje) {
		this.brojDanaZaOtkazivanje = brojDanaZaOtkazivanje;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public AdresaDTO getAdresaDTO() {
		return adresaDTO;
	}

	public void setAdresaDTO(AdresaDTO adresaDTO) {
		this.adresaDTO = adresaDTO;
	}

	public List<CenovnikDTO> getCenovnici() {
		return cenovnici;
	}

	public void setCenovnici(List<CenovnikDTO> cenovnici) {
		this.cenovnici = cenovnici;
	}

	public TipSmestajaDTO getTipDTO() {
		return tipDTO;
	}

	public void setTipDTO(TipSmestajaDTO tipDTO) {
		this.tipDTO = tipDTO;
	}
	
	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	
	public List<UslugaDTO> getUsluge() {
		return usluge;
	}

	public void setUsluge(List<UslugaDTO> usluge) {
		this.usluge = usluge;
	}
	
	public static List<SmestajnaJedinicaDTO> transformisi(List<SmestajnaJedinica> jedinice) {
		List<SmestajnaJedinicaDTO> rezultat = new ArrayList<>();
		for(SmestajnaJedinica jedinica : jedinice) {
			rezultat.add(new SmestajnaJedinicaDTO(jedinica));
		}
		return rezultat;
	}
	
	private List<Usluga> preuzmiUsluge(List<SpojUslugaJedinica> spojevi) {
		List<Usluga> rezultat = new ArrayList<>();
		if(spojevi != null) {
			for(SpojUslugaJedinica spoj : spojevi) {
				rezultat.add(spoj.getUsluga());
			}
		}
		return rezultat;
	}

	public double getTrenutnaCena() {
		return trenutnaCena;
	}

	public void setTrenutnaCena(double trenutnaCena) {
		this.trenutnaCena = trenutnaCena;
	}
	
}
