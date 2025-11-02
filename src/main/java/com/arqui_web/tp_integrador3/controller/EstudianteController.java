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

import com.arqui_web.tp_integrador3.model.Estudiante;
import com.arqui_web.tp_integrador3.service.EstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
	
	private EstudianteService service;

	public EstudianteController(EstudianteService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	public Estudiante createEstudiante(@RequestBody Estudiante e) {
		return service.createEstudiante(e);
	}
	
	@GetMapping("/{id}")
	public Optional<Estudiante> obtenerEstudianteById(@PathVariable Long id) {
		return service.obtenerEstudiante(id);
	}
	
	@GetMapping
	public Iterable<Estudiante> obtenerEstudiantes() {
		return service.obtenerEstudiantes();
	}
	
	@PutMapping
	public Boolean updateEstudiante(@RequestBody Estudiante newEstudiante, @PathVariable Long id) {
		return service.updateEstudiante(newEstudiante, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEstudiante(@PathVariable Long id) {
		service.deleteEstudiante(id);
	}
}
