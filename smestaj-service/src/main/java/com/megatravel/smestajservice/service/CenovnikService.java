package com.megatravel.smestajservice.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.megatravel.smestajservice.model.Cenovnik;
import com.megatravel.smestajservice.model.SmestajnaJedinica;

@Service
public class CenovnikService {
	
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
