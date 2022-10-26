package pablosz.test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;
import pablosz.app.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes=Application.class)
public class MiTest
{
	@Autowired
	private PersistentObjects po;

	@Autowired
	private EntityManager em;
	Persona persona;
	Persona persona2;
	Domicilio domicilio;
	@BeforeEach
	public void crearObjectos() {
		persona = new Persona("Matias", 215487);
		persona2 = new Persona("Juan", 325614);
		domicilio = new Domicilio("CalleFalsa 123", "Calle1 y Calle 2", "555-222",false, null, null);
	}

	@AfterEach
	@Transactional
	public void destruirSesionesYObjetos(){
		Query s = em.createQuery("DELETE FROM Sesion");
		Query o = em.createQuery("DELETE FROM Persistidor");
		s.executeUpdate();
		o.executeUpdate();
	}

	@Test
	@Transactional
	public void crearUnaSesion(){

		po.createSession(1, 100);

		Sesion sesion = (Sesion) em.createQuery("from Sesion where key = 1").getSingleResult();

		assertEquals(1, sesion.getKey());
		assertEquals(100, sesion.getTimeout());

	}
	@Test
	@Transactional
	public void SesionStoreLoadPersona()

	{
		po.createSession(2, 300);

		po.store(2, persona);

		Persona personaRecuperada = po.load(2, persona.getClass());

		assertThat(persona).isEqualToComparingFieldByField(personaRecuperada);


	}

	@Test
	@Transactional
	public void PersonaEnDosSesiones()

	{
		po.createSession(2, 300);
		po.createSession(8, 300);

		po.store(2, persona);
		po.store(8, persona2);

		Persona personaRecuperada = po.load(2, persona.getClass());
		Persona personaRecuperada2 = po.load(8, persona.getClass());

		assertThat(persona).isEqualToComparingFieldByField(personaRecuperada);
		assertThat(persona2).isEqualToComparingFieldByField(personaRecuperada2);


	}

	@Test
	@Transactional
	public void SesionStoreLoadDomicilioNotPersistable()

	{
		po.createSession(3, 200);

		po.store(3, domicilio);

		Domicilio domicilioRecuperado = po.load(3, domicilio.getClass());

		assertEquals(domicilio.getDireccion(), domicilioRecuperado.getDireccion());
		assertEquals(null, domicilioRecuperado.getEntreCalles());
		assertEquals(domicilio.getTelefono(), domicilioRecuperado.getTelefono());
		assertEquals(domicilio.isEdificio(), domicilioRecuperado.isEdificio());
		assertEquals(domicilio.getPiso(), domicilioRecuperado.getPiso());
		assertEquals(domicilio.getDepartamento(), domicilioRecuperado.getDepartamento());

	}

	@Test
	@Transactional
	public void destruirUnaSesion(){

		po.createSession(5, 100);

		Sesion sesion = (Sesion) em.createQuery("from Sesion where key = 5").getSingleResult();

		assertEquals(5, sesion.getKey());
		assertEquals(100, sesion.getTimeout());

		po.destroySession(5);

		List<Sesion> sesiones = (List<Sesion>) em.createQuery("from Sesion where key = 5").getResultList();

		assertEquals(0, sesiones.size());
	}
}
