package com.megatravel.smestajservice.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestajservice.model.Cenovnik;
import com.megatravel.smestajservice.model.SmestajnaJedinica;
import com.megatravel.smestajservice.repository.CenovnikRepository;
import com.megatravel.smestajservice.soap.dto.CenovnikDTO;

@Service
public class CenovnikService {
	
	@Autowired
	private CenovnikRepository cenovnikRepository;
	
	public List<Cenovnik> preuzmiSve() {
		return this.cenovnikRepository.findAll();
	}
	
	public double izracunajCenu(SmestajnaJedinica smestaj, LocalDate prviDatum, LocalDate poslednjiDatum) {
		double cena = 0.0;
		LocalDate datum = LocalDate.from(prviDatum);
		long brojDana = ChronoUnit.DAYS.between(prviDatum, poslednjiDatum);
		for(Cenovnik cenovnik : smestaj.getCenovnici()) {
			while(datum.isAfter(cenovnik.getPrviDanVazenja()) && datum.isBefore(cenovnik.getPoslednjiDanVazenja()) && brojDana > 0) {
				datum.plusDays(1);
				brojDana--;
				cena += cenovnik.getCenaPoNoci();
			}
		}
		return cena;
	}
	
	public List<Cenovnik> kreirajCenovnike(SmestajnaJedinica smestaj, List<CenovnikDTO> cenovnici) {
		List<Cenovnik> rezultat = new ArrayList<>();
		for(CenovnikDTO cenovnik : cenovnici) {
			LocalDate poslednjiDan = LocalDate.from(cenovnik.getPoslednjiDanVazenja().toGregorianCalendar().toInstant());
			LocalDate pomerenPoslednjiDan = poslednjiDan.withDayOfMonth(poslednjiDan.lengthOfMonth());
			try {
				cenovnik.setPoslednjiDanVazenja(DatatypeFactory.newInstance().newXMLGregorianCalendar(pomerenPoslednjiDan.toString()));
			} catch (DatatypeConfigurationException e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			Cenovnik trenutni = new Cenovnik(cenovnik);
			trenutni.setSmestajnaJedinica(smestaj);
			rezultat.add(trenutni);
			this.cenovnikRepository.save(trenutni);
		}
		return rezultat;
	}
	
}
