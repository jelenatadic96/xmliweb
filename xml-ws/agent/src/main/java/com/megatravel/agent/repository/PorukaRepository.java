package com.megatravel.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agent.model.Poruka;

@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long>{
	
}
