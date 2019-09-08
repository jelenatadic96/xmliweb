package com.megatravel.korisniciservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.korisniciservice.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

	Optional<Administrator> findByMejl(String mejl);

}
