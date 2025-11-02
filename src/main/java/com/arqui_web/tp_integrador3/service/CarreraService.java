package com.arqui_web.tp_integrador3.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arqui_web.tp_integrador3.model.Carrera;
import com.arqui_web.tp_integrador3.repository.CarreraRepository;

@Service
public class CarreraService {
	
	private final CarreraRepository repository;

	public CarreraService(CarreraRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Carrera createCarrera(Carrera c) {
		return repository.save(c);
	}
	
	public Optional<Carrera> obtenerCarrera(Long id) {
		return repository.findById(id);
	}
	
	public Iterable<Carrera> obtenerCarreras(){
		return repository.findAll();
	}
	
	public Boolean updateCarrera(Carrera c, Long id) {
//		return repository.
		return false; 
	}
	
	public Boolean deleteCarrera(Long id) {
		 try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
