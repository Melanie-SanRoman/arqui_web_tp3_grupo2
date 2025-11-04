package com.arqui_web.tp_integrador3.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arqui_web.tp_integrador3.dto.EstudianteCarreraDTO;
import com.arqui_web.tp_integrador3.dto.ReporteCarrerasDTO;
import com.arqui_web.tp_integrador3.model.Carrera;
import com.arqui_web.tp_integrador3.model.Estudiante;
import com.arqui_web.tp_integrador3.model.EstudianteCarrera;
import com.arqui_web.tp_integrador3.model.EstudianteCarreraId;
import com.arqui_web.tp_integrador3.repository.CarreraRepository;
import com.arqui_web.tp_integrador3.repository.EstudianteCarreraRepository;
import com.arqui_web.tp_integrador3.repository.EstudianteRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EstudianteCarreraService {

	private final EstudianteCarreraRepository repository;
	private final EstudianteRepository estudianteRepo;
	private final CarreraRepository carreraRepo;

	public EstudianteCarreraService(EstudianteCarreraRepository repository, EstudianteRepository estudianteRepo,
			CarreraRepository carreraRepo) {
		super();
		this.repository = repository;
		this.estudianteRepo = estudianteRepo;
		this.carreraRepo = carreraRepo;
	}

	@Transactional
	public EstudianteCarreraDTO createInscripcion(EstudianteCarreraDTO dto) {
		// buscar entidades existentes
		Estudiante estudiante = estudianteRepo.findById(dto.estudianteId())
				.orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado: " + dto.estudianteId()));
		Carrera carrera = carreraRepo.findById(dto.carreraId())
				.orElseThrow(() -> new EntityNotFoundException("Carrera no encontrada: " + dto.carreraId()));

		// parsear fechas
		LocalDate fechaIngreso = LocalDate.parse(dto.fecha_ingreso());
		LocalDate fechaEgreso = dto.fecha_egreso() == null ? null : LocalDate.parse(dto.fecha_egreso());

		// construir id embebido
		EstudianteCarreraId id = new EstudianteCarreraId();
		id.setEstudianteId(estudiante.getId());
		id.setCarreraId(carrera.getId());

		// construir entidad
		EstudianteCarrera ec = new EstudianteCarrera();
		ec.setId(id);
		ec.setEstudiante(estudiante);
		ec.setCarrera(carrera);
		ec.setFecha_ingreso(fechaIngreso);
		ec.setFecha_egreso(fechaEgreso);

		// guardar
		repository.save(ec);

		return ec.toInscripcionDTO();
	}

	public Optional<EstudianteCarreraDTO> obtenerInscripcionById(Long carreraId, Long estudianteId) {
		EstudianteCarreraId id = new EstudianteCarreraId();
		id.setEstudianteId(estudianteId);
		id.setCarreraId(carreraId);
		return repository.findById(id).map(i -> i.toInscripcionDTO());
	}

	public Iterable<EstudianteCarreraDTO> obtenerInscripciones() {
		return repository.findAll().stream().map(i -> i.toInscripcionDTO()).toList();
	}

	@Transactional
	public EstudianteCarreraDTO updateInscripcion(EstudianteCarreraDTO dto) {
		EstudianteCarreraId id = new EstudianteCarreraId(dto.carreraId(), dto.estudianteId());
		EstudianteCarrera existente = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("La relación estudiante-carrera no existe"));

		LocalDate fechaIngreso = LocalDate.parse(dto.fecha_ingreso());
		LocalDate fechaEgreso = dto.fecha_egreso() == null ? null : LocalDate.parse(dto.fecha_egreso());
		existente.setFecha_ingreso(fechaIngreso);
		existente.setFecha_egreso(fechaEgreso);

		repository.save(existente);
		return existente.toInscripcionDTO();
	}

	@Transactional
	public boolean deleteInscripcion(Long carreraId, Long estudianteId) {
		int filasEliminadas = repository.deleteByIds(carreraId, estudianteId);
		return filasEliminadas > 0;
	}

	public void probarQuery() {
		List<Object[]> resultados = repository.testQuery();
		for (Object[] fila : resultados) {
			System.out.println(Arrays.toString(fila));
			System.out.println("Tipos: " + fila[0].getClass() + ", " + fila[1].getClass() + ", " + fila[2].getClass()
					+ ", " + fila[3].getClass());
		}
	}

	public List<ReporteCarrerasDTO> getReporte() {
		List<ReporteCarrerasDTO> inscriptos = repository.getInscriptosPorCarrera();
		List<ReporteCarrerasDTO> egresados = repository.getGraduadosPorCarrera();

		Map<String, ReporteCarrerasDTO> reporteMap = new HashMap<>();

		for (ReporteCarrerasDTO r : inscriptos) {
			String clave = r.getNombreCarrera() + "_" + r.getYear();
			reporteMap.put(clave, r);
		}

		for (ReporteCarrerasDTO r : egresados) {
			String clave = r.getNombreCarrera() + "_" + r.getYear();

			if (reporteMap.containsKey(clave)) {
				ReporteCarrerasDTO existente = reporteMap.get(clave);
				existente.setEgresados(r.getEgresados());
			} else {
				reporteMap.put(clave, r);
			}
		}

		List<ReporteCarrerasDTO> reporteFinal = new ArrayList<>(reporteMap.values());

		reporteFinal.sort(
				Comparator.comparing(ReporteCarrerasDTO::getNombreCarrera).thenComparing(ReporteCarrerasDTO::getYear));

		return reporteFinal;
	}
}
