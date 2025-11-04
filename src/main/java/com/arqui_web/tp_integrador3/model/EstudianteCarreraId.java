package com.arqui_web.tp_integrador3.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class EstudianteCarreraId implements Serializable {
	private Long carreraId;
	private Long estudianteId;
	
	public EstudianteCarreraId() {
		super();
	}

	public EstudianteCarreraId(Long carreraId, Long estudianteId) {
		super();
		this.carreraId = carreraId;
		this.estudianteId = estudianteId;
	}

	public Long getEstudianteId() {
		return estudianteId;
	}

	public void setEstudianteId(Long estudianteId) {
		this.estudianteId = estudianteId;
	}

	public Long getCarreraId() {
		return carreraId;
	}

	public void setCarreraId(Long carreraId) {
		this.carreraId = carreraId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof EstudianteCarreraId))
			return false;
		EstudianteCarreraId that = (EstudianteCarreraId) o;
		return Objects.equals(estudianteId, that.estudianteId) && Objects.equals(carreraId, that.carreraId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(estudianteId, carreraId);
	}
}
