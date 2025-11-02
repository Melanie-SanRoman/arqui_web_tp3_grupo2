package com.arqui_web.tp_integrador3.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arqui_web.tp_integrador3.model.Carrera;
import com.arqui_web.tp_integrador3.service.CarreraService;

@RestController
@RequestMapping("/carreras")
public class CarreraController {
	
	private CarreraService service;

	public CarreraController(CarreraService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	public Carrera createCarrera(@RequestBody Carrera c) {
		return service.createCarrera(c);
	}
	
	@GetMapping("/{id}")
	public Optional<Carrera> obtenerCarreraById(@PathVariable Long id) {
		return service.obtenerCarrera(id);
	}
	
	@GetMapping
	public Iterable<Carrera> obtenerCarreras() {
		return service.obtenerCarreras();
	}
	
	@PutMapping
	public Boolean updateCarrera(@RequestBody Carrera newCarrera, @PathVariable Long id) {
		return service.updateCarrera(newCarrera, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCarrera(@PathVariable Long id) {
		service.deleteCarrera(id);
	}
}
