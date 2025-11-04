package com.arqui_web.tp_integrador3.dto;

public record EstudianteCarreraDTO(
		Long estudianteId, 
		Long carreraId, 
		String fecha_ingreso,
		String fecha_egreso)
{

	public Long estudianteId() {
		return estudianteId;
	}

	public Long carreraId() {
		return carreraId;
	}

	public String fecha_ingreso() {
		return fecha_ingreso;
	}

	public String fecha_egreso() {
		return fecha_egreso;
	}

}
