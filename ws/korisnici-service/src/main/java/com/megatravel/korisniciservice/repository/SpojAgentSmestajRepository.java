package com.megatravel.korisniciservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.korisniciservice.model.SpojAgentSmestaj;

@Repository
public interface SpojAgentSmestajRepository extends JpaRepository<SpojAgentSmestaj, Long> {

}
