package com.megatravel.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agent.model.Usluga;

@Repository
public interface UslugaRepository extends JpaRepository<Usluga, Long> {

}
