package com.arqui_web.tp_integrador3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arqui_web.tp_integrador3.model.Carrera;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

	@Query("SELECT c FROM Carrera c JOIN c.estudiantes ec GROUP BY c ORDER BY COUNT(ec) DESC")
	public List<Carrera> getCarrerasMayorInscripciones();
}
