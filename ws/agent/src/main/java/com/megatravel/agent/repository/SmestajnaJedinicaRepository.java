package com.megatravel.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agent.model.SmestajnaJedinica;

@Repository
public interface SmestajnaJedinicaRepository extends JpaRepository<SmestajnaJedinica, Long> {

}
