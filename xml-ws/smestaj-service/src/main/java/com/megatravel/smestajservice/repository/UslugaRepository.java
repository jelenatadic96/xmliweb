package com.megatravel.smestajservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.smestajservice.model.Usluga;

@Repository
public interface UslugaRepository extends JpaRepository<Usluga, Long> {

}
