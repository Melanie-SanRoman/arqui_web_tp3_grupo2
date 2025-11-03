package com.arqui_web.tp_integrador3.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arqui_web.tp_integrador3.dto.EstudianteCarreraDTO;
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

//	public EstudianteCarrera createInscripcion(EstudianteCarrera ec) {
//		return repository.save(ec);
//	}

	@Transactional
	public EstudianteCarrera crearDesdeDto(EstudianteCarreraDTO dto) {
		// 1) buscar entidades existentes
		Estudiante estudiante = estudianteRepo.findById(dto.estudianteId())
				.orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado: " + dto.estudianteId()));
		Carrera carrera = carreraRepo.findById(dto.carreraId())
				.orElseThrow(() -> new EntityNotFoundException("Carrera no encontrada: " + dto.carreraId()));

		// 2) parsear fechas
		LocalDate fechaIngreso = LocalDate.parse(dto.fecha_ingreso());
		LocalDate fechaEgreso = dto.fecha_egreso() == null ? null : LocalDate.parse(dto.fecha_egreso());

		// 3) construir id embebido
		EstudianteCarreraId id = new EstudianteCarreraId();
		id.setEstudianteId(estudiante.getId());
		id.setCarreraId(carrera.getId());

		// 4) construir entidad
		EstudianteCarrera ec = new EstudianteCarrera();
		ec.setId(id);
		ec.setEstudiante(estudiante);
		ec.setCarrera(carrera);
		ec.setFecha_ingreso(fechaIngreso);
		ec.setFecha_egreso(fechaEgreso);

		// 5) guardar (sin cascades necesarios)
		return repository.save(ec);
	}

	public Optional<EstudianteCarrera> obtenerInscripcionById(Long estudianteId, Long carreraId) {
		EstudianteCarreraId id = new EstudianteCarreraId();
        id.setEstudianteId(estudianteId);
        id.setCarreraId(carreraId);
        return repository.findById(id);
	}

	public Iterable<EstudianteCarrera> obtenerInscripciones() {
		return repository.findAll();
	}

	public Boolean updateInscripcion(EstudianteCarrera ec, EstudianteCarreraId id) {
//		return repository.
		return false;
	}

	public Boolean deleteInscripcion(EstudianteCarreraId id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
