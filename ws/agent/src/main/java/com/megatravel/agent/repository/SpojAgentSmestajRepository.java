package com.megatravel.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agent.model.SpojAgentSmestaj;

@Repository
public interface SpojAgentSmestajRepository extends JpaRepository<SpojAgentSmestaj, Long> {

}
