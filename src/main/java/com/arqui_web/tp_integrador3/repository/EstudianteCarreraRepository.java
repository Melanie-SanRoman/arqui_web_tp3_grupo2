package com.arqui_web.tp_integrador3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arqui_web.tp_integrador3.dto.ReporteCarrerasDTO;
import com.arqui_web.tp_integrador3.model.EstudianteCarrera;
import com.arqui_web.tp_integrador3.model.EstudianteCarreraId;

import jakarta.transaction.Transactional;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, EstudianteCarreraId> {

	@Modifying
	@Transactional
	@Query("DELETE FROM EstudianteCarrera ec WHERE ec.carrera.id = :carreraId AND ec.estudiante.id = :estudianteId")
	int deleteByIds(@Param("carreraId") Long carreraId, @Param("estudianteId") Long estudianteId);

	@Query("SELECT new com.arqui_web.tp_integrador3.dto.ReporteCarrerasDTO("
			+ "c.nombre, COUNT(ec), 0L, YEAR(ec.fecha_ingreso)) " + "FROM EstudianteCarrera ec " + "JOIN ec.carrera c "
			+ "WHERE ec.fecha_ingreso IS NOT NULL " + "GROUP BY c.nombre, YEAR(ec.fecha_ingreso)")
	List<ReporteCarrerasDTO> getInscriptosPorCarrera();

	@Query("SELECT new com.arqui_web.tp_integrador3.dto.ReporteCarrerasDTO("
			+ "c.nombre, 0L, COUNT(ec), YEAR(ec.fecha_egreso)) " + "FROM EstudianteCarrera ec " + "JOIN ec.carrera c "
			+ "WHERE ec.fecha_egreso IS NOT NULL " + "GROUP BY c.nombre, YEAR(ec.fecha_egreso)")
	List<ReporteCarrerasDTO> getGraduadosPorCarrera();

	@Query("""
			    SELECT c.nombre, COUNT(ec), 0L, FUNCTION('YEAR', ec.fecha_ingreso)
			    FROM EstudianteCarrera ec
			    JOIN ec.carrera c
			    WHERE ec.fecha_ingreso IS NOT NULL
			    GROUP BY c.nombre, FUNCTION('YEAR', ec.fecha_ingreso)
			""")
	List<Object[]> testQuery();
}
