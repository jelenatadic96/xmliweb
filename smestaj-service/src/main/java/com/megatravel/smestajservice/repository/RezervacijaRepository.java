package com.megatravel.smestajservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.smestajservice.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

	List<Rezervacija> findAllByKorisnikId(Long id);

}
