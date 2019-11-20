package com.justmehr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justmehr.backend.domain.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long>{

}
