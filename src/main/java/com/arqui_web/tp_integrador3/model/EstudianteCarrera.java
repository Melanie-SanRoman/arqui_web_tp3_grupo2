package com.arqui_web.tp_integrador3.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante_carrera")
public class EstudianteCarrera {
	@EmbeddedId
	private EstudianteCarreraId id;

	@ManyToOne
	@MapsId("estudianteId")
	@JoinColumn(name = "estudiante_id")
	private Estudiante estudiante;

	@ManyToOne
	@MapsId("carreraId")
	@JoinColumn(name = "carrera_id")
	private Carrera carrera;

	@Column(nullable = false)
	private LocalDate fecha_ingreso;
	@Column(nullable = true)
	private LocalDate fecha_egreso;

	public EstudianteCarrera() {
		super();
	}

	public EstudianteCarrera(LocalDate fecha_ingreso, LocalDate fecha_egreso) {
		super();
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_egreso = fecha_egreso;
	}

	public EstudianteCarrera(Estudiante estudiante, Carrera carrera, LocalDate fecha_ingreso) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_egreso = null;
		this.id = new EstudianteCarreraId(estudiante.getId(), carrera.getId());
	}

	public void setId(EstudianteCarreraId id) {
		this.id = id;
	}

	public LocalDate getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(LocalDate fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public LocalDate getFecha_egreso() {
		return fecha_egreso;
	}

	public void setFecha_egreso(LocalDate fecha_egreso) {
		this.fecha_egreso = fecha_egreso;
	}

	public EstudianteCarreraId getId() {
		return id;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
}
