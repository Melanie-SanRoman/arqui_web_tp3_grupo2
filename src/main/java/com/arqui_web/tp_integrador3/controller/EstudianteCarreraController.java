package com.arqui_web.tp_integrador3.controller;

import java.util.List;
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

import com.arqui_web.tp_integrador3.dto.EstudianteCarreraDTO;
import com.arqui_web.tp_integrador3.dto.ReporteCarrerasDTO;
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
	public ResponseEntity<EstudianteCarreraDTO> createInscripcion(@RequestBody EstudianteCarreraDTO dto) {
		EstudianteCarreraDTO creado = service.createInscripcion(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}

	@GetMapping("/{carreraId}/{estudianteId}")
	public ResponseEntity<EstudianteCarreraDTO> obtenerInscripcionById(@PathVariable Long carreraId,
			@PathVariable Long estudianteId) {
		Optional<EstudianteCarreraDTO> encontrado = service.obtenerInscripcionById(carreraId, estudianteId);
		return encontrado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<Iterable<EstudianteCarreraDTO>> obtenerInscripciones() {
		Iterable<EstudianteCarreraDTO> lista = service.obtenerInscripciones();
		return ResponseEntity.ok(lista);
	}

	@PutMapping
	public ResponseEntity<EstudianteCarreraDTO> updateInscripcion(@RequestBody EstudianteCarreraDTO dto) {
		EstudianteCarreraDTO actualizado = service.updateInscripcion(dto);
		return ResponseEntity.ok(actualizado);
	}

	@DeleteMapping("/{carreraId}/{estudianteId}")
	public ResponseEntity<Void> deleteInscripcion(@PathVariable Long carreraId, @PathVariable Long estudianteId) {
		boolean eliminado = service.deleteInscripcion(carreraId, estudianteId);
		return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

	@GetMapping("/reporte")
	public ResponseEntity<List<ReporteCarrerasDTO>> getReporte() {
		List<ReporteCarrerasDTO> reporte = service.getReporte();
		return ResponseEntity.ok(reporte);
	}
}
