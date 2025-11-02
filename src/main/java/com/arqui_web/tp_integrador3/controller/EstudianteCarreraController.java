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

import com.arqui_web.tp_integrador3.model.EstudianteCarrera;
import com.arqui_web.tp_integrador3.model.EstudianteCarreraId;
import com.arqui_web.tp_integrador3.service.EstudianteCarreraService;

@RestController
@RequestMapping("/inscripciones")
public class EstudianteCarreraController {
	
	private EstudianteCarreraService service;

	public EstudianteCarreraController(EstudianteCarreraService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public EstudianteCarrera createInscripcion(@RequestBody EstudianteCarrera ec) {
		return service.createInscripcion(ec);
	}
	
	@GetMapping("/{id}")
	public Optional<EstudianteCarrera> obtenerInscripcionById(@PathVariable EstudianteCarreraId id) {
		return service.obtenerInscripcionById(id);
	}
	
	@GetMapping
	public Iterable<EstudianteCarrera> obtenerInscripciones() {
		return service.obtenerInscripciones();
	}
	
	@PutMapping
	public Boolean updateInscripcion(@RequestBody EstudianteCarrera newInscripcion, @PathVariable EstudianteCarreraId id) {
		return service.updateInscripcion(newInscripcion, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteInscripcion(@PathVariable EstudianteCarreraId id) {
		service.deleteInscripcion(id);
	}
}
