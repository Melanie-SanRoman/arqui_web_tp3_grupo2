package com.arqui_web.tp_integrador3;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arqui_web.tp_integrador3.repository.EstudianteCarreraRepository;

@SpringBootTest
class TpIntegrador3ApplicationTests {

	@Autowired
	private EstudianteCarreraRepository repo;

	@Test
	void TEST_TiposDeQuery() {
		List<Object[]> resultados = repo.testQuery();
		for (Object[] fila : resultados) {
			System.out.println(Arrays.toString(fila));
			System.out.println("Tipos: " + fila[0].getClass() + ", " + fila[1].getClass() + ", " + fila[2].getClass()
					+ ", " + fila[3].getClass());
		}
	}

}
