package com.arqui_web.tp_integrador3.model.exceptions;

public class RecursoNoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecursoNoEncontradoException() {
		super("No se ha encontrado el estudiante.");
	}
}
