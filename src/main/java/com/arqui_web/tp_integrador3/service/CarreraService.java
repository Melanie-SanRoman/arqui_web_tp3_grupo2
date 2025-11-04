package com.arqui_web.tp_integrador3.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.arqui_web.tp_integrador3.dto.CarreraDTO;
import com.arqui_web.tp_integrador3.model.Carrera;
import com.arqui_web.tp_integrador3.repository.CarreraRepository;

import jakarta.transaction.Transactional;

@Service
public class CarreraService {

	private final CarreraRepository repository;
	private static final Logger log = LoggerFactory.getLogger(EstudianteService.class);

	public CarreraService(CarreraRepository repository) {
		super();
		this.repository = repository;
	}

	public CarreraDTO createCarrera(CarreraDTO dto) {
		Carrera c = new Carrera(dto.getNombre());

		Carrera guardado = repository.save(c);
		log.info("Carrera creada con ID {}", guardado.getId());

		return c.toCarreraDTO();
	}

	public Optional<CarreraDTO> obtenerCarrera(Long id) {
		return repository.findById(id).map(c -> c.toCarreraDTO());
	}

	public Iterable<CarreraDTO> obtenerCarreras() {
		return repository.findAll().stream().map(c -> c.toCarreraDTO()).toList();
	}

	@Transactional
	public Optional<CarreraDTO> updateCarrera(CarreraDTO dto, Long id) {
		return repository.findById(id).map(c -> {
			c.setNombre(dto.getNombre());

			repository.save(c);
			log.info("Carrera con ID {} actualizada correctamente", id);

			return c.toCarreraDTO();
		});
	}

	public Boolean deleteCarrera(Long id) {
		Optional<Carrera> carrera = repository.findById(id);

		try {
			if (carrera == null) {
				log.error("Error, no se encontro carrera con id {}", id);
				return false;
			} else {
				repository.deleteById(id);
				log.info("Carrera con ID {} eliminada correctamente", id);
				return true;
			}
		} catch (Exception e) {
			log.error("Error eliminando carrera con id {}", id);
			return false;
		}
	}

	public List<CarreraDTO> listarPorCantInscriptos() {
		return repository.getCarrerasMayorInscripciones().stream().map(c -> c.toCarreraDTO()).toList();
	}
}
