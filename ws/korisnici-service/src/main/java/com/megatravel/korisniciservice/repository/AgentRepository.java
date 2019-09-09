package com.megatravel.korisniciservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.korisniciservice.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

	Optional<Agent> findByMejl(String mejl);

}
