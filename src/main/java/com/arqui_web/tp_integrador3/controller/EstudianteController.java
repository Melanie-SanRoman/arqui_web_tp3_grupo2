package com.arqui_web.tp_integrador3.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arqui_web.tp_integrador3.dto.EstudianteDTO;
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
	public ResponseEntity<EstudianteDTO> createEstudiante(@RequestBody EstudianteDTO dto) {
		EstudianteDTO creado = service.createEstudiante(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstudianteDTO> obtenerEstudianteById(@PathVariable Long id) {
		Optional<EstudianteDTO> encontrado = service.obtenerEstudiante(id);
		return encontrado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<Iterable<EstudianteDTO>> obtenerEstudiantes() {
		Iterable<EstudianteDTO> lista = service.obtenerEstudiantes();
		return ResponseEntity.ok(lista);
	}

	@PutMapping
	public ResponseEntity<EstudianteDTO> updateEstudiante(@RequestBody EstudianteDTO dto, @PathVariable Long id) {
		return service.updateEstudiante(dto, id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
		boolean eliminado = service.deleteEstudiante(id);
		if (eliminado) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
