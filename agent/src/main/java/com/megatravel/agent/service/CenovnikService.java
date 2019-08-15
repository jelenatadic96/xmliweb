package com.megatravel.agent.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.agent.dto.CenovnikDTO;
import com.megatravel.agent.model.Cenovnik;
import com.megatravel.agent.model.SmestajnaJedinica;
import com.megatravel.agent.repository.CenovnikRepository;

@Service
public class CenovnikService {

	@Autowired
	private CenovnikRepository cenovnikRepository;
	
	public List<Cenovnik> kreirajCenovnike(SmestajnaJedinica smestaj, List<CenovnikDTO> cenovnici) {
		List<Cenovnik> rezultat = new ArrayList<>();
		for(CenovnikDTO cenovnik : cenovnici) {
			LocalDate poslednjiDan = cenovnik.getPoslednjiDanVazenja();
			cenovnik.setPoslednjiDanVazenja(poslednjiDan.withDayOfMonth(poslednjiDan.lengthOfMonth()));
			Cenovnik trenutni = new Cenovnik(cenovnik);
			trenutni.setSmestajnaJedinica(smestaj);
			rezultat.add(trenutni);
			this.cenovnikRepository.save(trenutni);
		}
		return rezultat;
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
	
}
