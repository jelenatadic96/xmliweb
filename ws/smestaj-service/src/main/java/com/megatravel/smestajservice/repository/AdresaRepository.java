package com.megatravel.smestajservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.smestajservice.model.Adresa;

@Repository
public interface AdresaRepository extends JpaRepository<Adresa, Long> {

}
