package com.arqui_web.tp_integrador3.dto;

public record EstudianteCarreraDTO(
		Long estudianteId, 
		Long carreraId, 
		String fecha_ingreso, 
		String fecha_egreso) {
}
