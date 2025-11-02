package com.arqui_web.tp_integrador3.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arqui_web.tp_integrador3.model.EstudianteCarrera;
import com.arqui_web.tp_integrador3.model.EstudianteCarreraId;
import com.arqui_web.tp_integrador3.repository.EstudianteCarreraRepository;

@Service
public class EstudianteCarreraService {
	
	private final EstudianteCarreraRepository repository;

	public EstudianteCarreraService(EstudianteCarreraRepository repository) {
		super();
		this.repository = repository;
	}
	
	public EstudianteCarrera createInscripcion(EstudianteCarrera ec) {
		return repository.save(ec);
	}
	
	public Optional<EstudianteCarrera> obtenerInscripcionById(EstudianteCarreraId id) {
		return repository.findById(id);
	}
	
	public Iterable<EstudianteCarrera> obtenerInscripciones(){
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
