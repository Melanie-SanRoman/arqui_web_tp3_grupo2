package com.arqui_web.tp_integrador3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arqui_web.tp_integrador3.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{

}
