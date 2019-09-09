package com.megatravel.agent.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
	
	@Autowired
	private SmestajnaJedinicaService smestajnaJedinicaService;
	
	public List<Cenovnik> kreirajCenovnike(SmestajnaJedinica smestaj, List<CenovnikDTO> cenovnici) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Cenovnik> rezultat = new ArrayList<>();
		for(CenovnikDTO cenovnik : cenovnici) {
			LocalDate poslednjiDan = LocalDate.parse(cenovnik.getPoslednjiDanVazenja(), formatter);
			cenovnik.setPoslednjiDanVazenja(poslednjiDan.withDayOfMonth(poslednjiDan.lengthOfMonth()).toString());
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

	public void dodajZaSinhronizaciju(com.megatravel.agent.soap.generated.CenovnikDTO cenovnikDTO) {
		if(!this.cenovnikRepository.findById(cenovnikDTO.getId()).isPresent()) {
			Cenovnik cenovnik = new Cenovnik();
			cenovnik.setCenaPoNoci(cenovnikDTO.getCenaPoNoci());
			cenovnik.setPrviDanVazenja(cenovnikDTO.getPrviDanVazenja().toGregorianCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			cenovnik.setPoslednjiDanVazenja(cenovnikDTO.getPoslednjiDanVazenja().toGregorianCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			cenovnik.setSmestajnaJedinica(this.smestajnaJedinicaService.preuzmiJednu(cenovnikDTO.getSmestaj()));
			this.cenovnikRepository.save(cenovnik);
		}
	}
	
}
