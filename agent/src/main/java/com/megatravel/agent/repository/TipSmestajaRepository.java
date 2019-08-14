package com.megatravel.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agent.model.TipSmestaja;

@Repository
public interface TipSmestajaRepository extends JpaRepository<TipSmestaja, Long> {

}
