package com.arqui_web.tp_integrador3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arqui_web.tp_integrador3.model.Estudiante;
import com.arqui_web.tp_integrador3.model.TipoGenero;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

	@Query("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
	public List<Estudiante> getEstudiantesOrderByApellido();

	@Query("SELECT e FROM Estudiante e WHERE e.num_libreta = :numLibreta")
	Estudiante getEstudianteByNumLibreta(@Param("numLibreta") int num_libreta);

	@Query("SELECT e FROM Estudiante e WHERE e.genero = :tipoGenero")
	List<Estudiante> getEstudiantesGenero(@Param("tipoGenero") TipoGenero genero);

	@Query("SELECT ec.estudiante FROM EstudianteCarrera ec WHERE ec.carrera.id = :carreraId")
	List<Estudiante> getEstudiantesByCarrera(@Param("carreraId") Long carreraId);
}
