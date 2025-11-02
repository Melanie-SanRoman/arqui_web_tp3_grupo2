package com.arqui_web.tp_integrador3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arqui_web.tp_integrador3.model.EstudianteCarrera;
import com.arqui_web.tp_integrador3.model.EstudianteCarreraId;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, EstudianteCarreraId> {

}
