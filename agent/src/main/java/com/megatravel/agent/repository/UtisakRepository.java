package com.megatravel.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agent.model.Utisak;

@Repository
public interface UtisakRepository extends JpaRepository<Utisak, Long> {

}
