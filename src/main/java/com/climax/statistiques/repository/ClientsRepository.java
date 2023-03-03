package com.climax.statistiques.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.climax.statistiques.entities.clients;

public interface ClientsRepository extends JpaRepository<clients, Integer> {

}
