package com.megatravel.utisakservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.utisakservice.model.Utisak;

@Repository
public interface UtisakRepository extends JpaRepository<Utisak, Long> {

	Optional<Utisak> findByRezervacijaId(Long id);
	
}
