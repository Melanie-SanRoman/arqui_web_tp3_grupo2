
package com.arqui_web.tp_integrador3.dto;

public class ReporteCarrerasDTO {
	private String nombreCarrera;
	private Long inscriptos;
	private Long egresados;
	private Integer year;
	
	public ReporteCarrerasDTO() {
		super();
	}

	public ReporteCarrerasDTO(String nombreCarrera, Long inscriptos, Long egresados, Integer year) {
		super();
		this.nombreCarrera = nombreCarrera;
		this.inscriptos = inscriptos;
		this.egresados = egresados;
		this.year = year;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public Long getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(Long inscriptos) {
		this.inscriptos = inscriptos;
	}

	public Long getEgresados() {
		return egresados;
	}

	public void setEgresados(Long egresados) {
		this.egresados = egresados;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "ReporteCarrerasDTO [nombreCarrera=" + nombreCarrera + ", inscriptos=" + inscriptos + ", egresados="
				+ egresados + ", año=" + year + "]";
	}
}
