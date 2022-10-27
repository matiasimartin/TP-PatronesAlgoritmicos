package pablosz.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;
import pablosz.app.Application;
import pablosz.app.Domicilio;
import pablosz.app.PersistentObjects;
import pablosz.app.Persona;

@SpringBootTest(classes=Application.class)
public class PersistentObjectTest
{
	@Autowired
	private PersistentObjects po;

	private static long key1 = 1;
	private static int intKey1 = 1;
	private static String stringKey1 = "PABLO";

	private static long key2 = 2;
	private static int intKey2 = 2;
	private static String stringKey2 = "PEDRO";

	Persona persona;
	Persona persona2;
	Domicilio domicilio;
	
	@BeforeEach
	public void crearObjectos() {
		persona = new Persona("Matias", 215487);
		persona2 = new Persona("Juan", 325614);
		domicilio = new Domicilio("CalleFalsa 123", "Calle1 y Calle 2", "555-222",false, null, null);
	}

	@BeforeEach
	@Transactional
	public void before() throws Exception
	{
		// persisto sobre la sesion 1
		po.createSession(key1,100000);
		po.store(key1,"PABLO");
		po.store(key1,Integer.valueOf(1));

		// persisto sobre la sesion 2
		po.createSession(key2,200000);
		po.store(key2,"PEDRO");
		po.store(key2,Integer.valueOf(2));
	}
	
	@AfterEach
	@Transactional
	public void after()
	{
		// destruyo
		po.destroySession(key1);
		po.destroySession(key2);
	}
		
	@Test
	@Transactional
	public void testErrores() throws Exception
	{
		// load de un objeto que no fue persistido o fue borrado => null
		assertNull(po.load(key1,Double.class));

		// store sobre una sesión que no fue creada => Exception
		asegurarTiraExcepcion(()->po.store(999,"ERROR"));

		// load sobre una sesión que no fue creada => Exception
		asegurarTiraExcepcion(()->po.load(999,String.class));

		// creo una sesión que ya fue creada
		asegurarTiraExcepcion(()->po.createSession(key1,100000));
	}
	
	@Test
	@Transactional
	public void testFuncionalidad() throws Exception
	{
		assertEquals(po.load(key1,String.class),stringKey1);
		//assertEquals(po.load(key1,Integer.class),intKey1);
		assertEquals(po.load(key2,String.class),stringKey2);
		//assertEquals(po.load(key2,Integer.class),intKey2);
	}
	
	@Test
	@Transactional
	public void testPersistableNotPersistable() throws Exception
	{
		// store
		MiClase1 mc1 = new MiClase1(1,2,3);
		mc1.setAttPersistable("x");
		mc1.setAttNoPersistable("y");
		po.store(key2,mc1);
		
		// load y comparo
		MiClase1 q = (MiClase1)po.load(key2,MiClase1.class);
		assertEquals(q.getAtt1(),1);
		assertEquals(q.getAtt2(),2);
		assertEquals(q.getAtt3(),3);
		assertEquals(q.getAttPersistable(),"x");
		assertNull(q.getAttNoPersistable());

		// store
		MiClase1Base mcb1 = new MiClase1Base();
		mcb1.setAttPersistable("x");
		mcb1.setAttNoPersistable("y");
		po.store(key2,mcb1);
		
		// load y comparo
		MiClase1Base p = (MiClase1Base)po.load(key2,MiClase1Base.class);
		assertEquals(p.getAttPersistable(),"x");
		assertNull(p.getAttNoPersistable());
	}
	
	@Test
	@Transactional
	public void testRemove() throws Exception
	{
		// verifico que existe el objeto
		String s = (String)po.load(key1,String.class);
		assertEquals(s,stringKey1);
		
		// lo remuevo
		po.remove(key1,String.class);
		s = (String)po.load(key1,String.class);
		assertNull(s);
	}	
	
	@Test
	@Transactional
	public void testDestroy() throws Exception
	{
		// la sesion existe
		String s = (String)po.load(key1,String.class);
		assertNotNull(s);
		
		// la destruyo
		po.destroySession(key1);
		asegurarTiraExcepcion(()->po.load(key1,String.class));
	}
	
	
	private void asegurarTiraExcepcion(Executable e)
	{
		try
		{
			e.execute();
			throw new RuntimeException("Debería tirar una excepción");
		}
		catch(Throwable t)
		{
		}
	}	
}
