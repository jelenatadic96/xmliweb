package com.megatravel.utisakservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.utisakservice.model.Utisak;

@Repository
public interface UtisakRepository extends JpaRepository<Utisak, Long> {

}
