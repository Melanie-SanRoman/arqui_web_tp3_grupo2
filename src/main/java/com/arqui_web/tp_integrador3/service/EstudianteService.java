
package com.arqui_web.tp_integrador3.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arqui_web.tp_integrador3.model.Estudiante;
import com.arqui_web.tp_integrador3.repository.EstudianteRepository;

@Service
public class EstudianteService {
	private final EstudianteRepository repository;
	
	public EstudianteService(EstudianteRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Estudiante createEstudiante(Estudiante e) {
		return repository.save(e);
	}
	
	public Optional<Estudiante> obtenerEstudiante(Long id) {
		return repository.findById(id);
	}
	
	public Iterable<Estudiante> obtenerEstudiantes(){
		return repository.findAll();
	}
	
	public Boolean updateEstudiante(Estudiante e, Long id) {
//		return repository.
		return false; 
	}
	
	public Boolean deleteEstudiante(Long id) {
		 try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
