package com.web.basura;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.web.basura.entity.ArrayName;
import com.web.basura.service.EmailService;

@SpringBootTest
class BasuraApplicationTests {
	@Autowired
	private EmailService email;
	
	@Autowired
	private ArrayName arra;

	@Test
	void contextLoads() {
	}
	@Test
	void sendEmail() {
		email.enviarCorreo("horariobasura@gmail.com",false,true);
	}
	
	@Test
	void array() {
		String[] arr = arra.getArrayName();
		String arraResult = "";
		for(String arra : arr) {
			arraResult += arra;
		}
		assertTrue(arraResult.contains("C"));
	}
	
	@Test
	void array1() {
		String[] arr = arra.getArrayName();
		String arraResult = "";
		for(String arra : arr) {
			arraResult += arra;
		}
		assertFalse(arraResult.contains("c"));
	}
	@Test
	void array2() {
		String[] arr = arra.getArrayName();
		String arraResult = "";
		for(String arra : arr) {
			arraResult += arra;
		}
		assertFalse(arraResult.contains("SE"));
	}
	@Test
	void array3() {
		String[] arr = arra.getArrayName();
		assertTrue(arr.length == 4);
	}
}
