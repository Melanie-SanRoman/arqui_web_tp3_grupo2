
package com.arqui_web.tp_integrador3.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.arqui_web.tp_integrador3.dto.EstudianteDTO;
import com.arqui_web.tp_integrador3.model.Estudiante;
import com.arqui_web.tp_integrador3.repository.EstudianteRepository;

import jakarta.transaction.Transactional;

@Service
public class EstudianteService {
	private final EstudianteRepository repository;
	private static final Logger log = LoggerFactory.getLogger(EstudianteService.class);

	public EstudianteService(EstudianteRepository repository) {
		super();
		this.repository = repository;
	}

	public EstudianteDTO createEstudiante(EstudianteDTO dto) {
		Estudiante e = new Estudiante(dto.getNombre(), dto.getApellido(), dto.getFechaNacimiento(), dto.getGenero(),
				dto.getDni(), dto.getCiudad(), dto.getNumLibreta());

		Estudiante guardado = repository.save(e);
		log.info("Estudiante creado con ID {}", guardado.getId());

		return e.toEstudianteDTO();
	}

	public Optional<EstudianteDTO> obtenerEstudiante(Long id) {
		return repository.findById(id).map(e -> e.toEstudianteDTO());
	}

	public Iterable<EstudianteDTO> obtenerEstudiantes() {
		return repository.findAll().stream().map(e -> e.toEstudianteDTO()).toList();
	}

	@Transactional
	public Optional<EstudianteDTO> updateEstudiante(EstudianteDTO dto, Long id) {
		return repository.findById(id).map(e -> {
			e.setNombre(dto.getNombre());
			e.setApellido(dto.getApellido());
			e.setFechaNacimiento(dto.getFechaNacimiento());
			e.setGenero(dto.getGenero());
			e.setDni(dto.getDni());
			e.setCiudad(dto.getCiudad());
			e.setNumLibreta(dto.getNumLibreta());

			repository.save(e);
			log.info("Estudiante con ID {} actualizado correctamente", id);

			return e.toEstudianteDTO();
		});
	}

	public Boolean deleteEstudiante(Long id) {
//		Estudiante estudiante2 = repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No se ah encontrado un Estudiante con el id: ${id}"));

		Optional<Estudiante> estudiante = repository.findById(id);

		try {
			if (estudiante != null) {
				log.error("Error, no se encontro estudiante con id {}", id);
				return false;
			} else {
				repository.deleteById(id);
				log.info("Estudiante con ID {} eliminado correctamente", id);
				return true;
			}
		} catch (Exception e) {
			log.error("Error eliminando estudiante con id {}", id);
			return false;
		}
	}
}
