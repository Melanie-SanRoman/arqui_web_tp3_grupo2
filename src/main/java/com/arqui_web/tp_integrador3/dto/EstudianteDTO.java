package com.arqui_web.tp_integrador3.dto;

import java.time.LocalDate;

import com.arqui_web.tp_integrador3.model.TipoGenero;

public class EstudianteDTO {
	private Long id;
	private String nombre;
	private String apellido;
	private LocalDate fecha_nacimiento;
	private TipoGenero genero;
	private int dni;
	private String ciudad;
	private int num_libreta;

	public EstudianteDTO(Long id, String nombre, String apellido, LocalDate fecha_nacimiento, TipoGenero genero, int dni,
			String ciudad, int num_libreta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
		this.num_libreta = num_libreta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fecha_nacimiento;
	}

	public void setFechaNacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public TipoGenero getGenero() {
		return genero;
	}

	public void setGenero(TipoGenero genero) {
		this.genero = genero;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getNumLibreta() {
		return num_libreta;
	}

	public void setNumLibreta(int num_libreta) {
		this.num_libreta = num_libreta;
	}
}
