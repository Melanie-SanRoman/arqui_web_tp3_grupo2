
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

		return new EstudianteDTO(e.getId(), e.getNombre(), e.getApellido(), e.getFechaNacimiento(), e.getGenero(), e.getDni(),
				e.getCiudad(), e.getNumLibreta());
	}

	public Optional<Estudiante> obtenerEstudiante(Long id) {
		return repository.findById(id);
	}

	public Iterable<EstudianteDTO> obtenerEstudiantes() {
		return repository.findAll().stream().map(e -> new EstudianteDTO(e.getId(), e.getNombre(), e.getApellido(),
				e.getFechaNacimiento(), e.getGenero(), e.getDni(), e.getCiudad(), e.getNumLibreta())).toList();
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

			Estudiante actualizado = repository.save(e);
			log.info("Estudiante con ID {} actualizado correctamente", id);

			return new EstudianteDTO(actualizado.getId(), actualizado.getNombre(), actualizado.getApellido(),
					actualizado.getFechaNacimiento(), actualizado.getGenero(), actualizado.getDni(),
					actualizado.getCiudad(), actualizado.getNumLibreta());
		});
	}

	public Boolean deleteEstudiante(Long id) {
		try {
			repository.deleteById(id);
			log.info("Estudiante con ID {} eliminado correctamente", id);
			return true;
		} catch (Exception e) {
			log.error("Error eliminando estudiante con id {}", id, e);
			return false;
		}
	}
}
