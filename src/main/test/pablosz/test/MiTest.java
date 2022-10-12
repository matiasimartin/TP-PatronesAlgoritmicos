package pablosz.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pablosz.app.Application;

@SpringBootTest(classes=Application.class)
public class MiTest
{
	@Autowired
	private EntityManager em;
	
	@Test
	public void funcionaOk()
	{
		assertNotNull(em);
	}	
}
