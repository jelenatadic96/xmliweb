package com.megatravel.smestajservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.smestajservice.model.SmestajnaJedinica;

@Repository
public interface SmestajnaJedinicaRepository extends JpaRepository<SmestajnaJedinica, Long> {

}
