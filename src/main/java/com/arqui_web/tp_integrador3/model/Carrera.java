package com.arqui_web.tp_integrador3.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carreraId")
	private Long id;
	@Column(nullable = false)
	private String nombre;

	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EstudianteCarrera> estudiantes = new ArrayList<>();

	public Carrera() {
		super();
	}

	public Carrera(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", nombre=" + nombre + "]";
	}

	public List<EstudianteCarrera> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<EstudianteCarrera> estudiantes) {
		this.estudiantes = estudiantes;
	}
}
