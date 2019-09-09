package com.megatravel.porukeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.porukeservice.model.Poruka;

@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long> {

	List<Poruka> findAllByAgentId(Long id);

	List<Poruka> findAllByKorisnikId(Long id);

	List<Poruka> findAllByAgentIdAndKorisnikId(Long id, Long korisnikId);
	
}
