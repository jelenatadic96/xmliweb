package com.megatravel.agent.dto;

import java.time.LocalDate;
import java.util.List;

import com.megatravel.agent.model.Kategorija;

public class UpitPretrageDTO {

	private String grad;
	private String zemlja;
	private LocalDate prviDan;
	private LocalDate poslednjiDan;
	private int brojOsoba;
	private TipSmestajaDTO tipSmestajaDTO;
	private Kategorija kategorija;
	private List<UslugaDTO> uslugeDTO;
	
	public UpitPretrageDTO() { }

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getZemlja() {
		return zemlja;
	}

	public void setZemlja(String zemlja) {
		this.zemlja = zemlja;
	}

	public LocalDate getPrviDan() {
		return prviDan;
	}

	public void setPrviDan(LocalDate prviDan) {
		this.prviDan = prviDan;
	}

	public LocalDate getPoslednjiDan() {
		return poslednjiDan;
	}

	public void setPoslednjiDan(LocalDate poslednjiDan) {
		this.poslednjiDan = poslednjiDan;
	}

	public int getBrojOsoba() {
		return brojOsoba;
	}

	public void setBrojOsoba(int brojOsoba) {
		this.brojOsoba = brojOsoba;
	}

	public TipSmestajaDTO getTipSmestajaDTO() {
		return tipSmestajaDTO;
	}

	public void setTipSmestajaDTO(TipSmestajaDTO tipSmestajaDTO) {
		this.tipSmestajaDTO = tipSmestajaDTO;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public List<UslugaDTO> getUslugeDTO() {
		return uslugeDTO;
	}

	public void setUslugeDTO(List<UslugaDTO> uslugeDTO) {
		this.uslugeDTO = uslugeDTO;
	}
	
}
