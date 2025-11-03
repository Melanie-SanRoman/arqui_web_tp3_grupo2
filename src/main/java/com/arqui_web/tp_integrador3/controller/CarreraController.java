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

import com.arqui_web.tp_integrador3.dto.CarreraDTO;
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
	public ResponseEntity<CarreraDTO> createCarrera(@RequestBody CarreraDTO dto) {
		CarreraDTO creada = service.createCarrera(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(creada);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarreraDTO> obtenerCarreraById(@PathVariable Long id) {
		Optional<CarreraDTO> encontrada = service.obtenerCarrera(id);
		return encontrada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<Iterable<CarreraDTO>> obtenerCarreras() {
		Iterable<CarreraDTO> it = service.obtenerCarreras();
		return ResponseEntity.ok(it);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CarreraDTO> updateCarrera(@RequestBody CarreraDTO dto, @PathVariable Long id) {
		return service.updateCarrera(dto, id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCarrera(@PathVariable Long id) {
		boolean eliminado = service.deleteCarrera(id);
		if (eliminado) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
