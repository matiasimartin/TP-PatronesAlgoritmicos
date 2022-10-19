package pablosz.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MiRestController
{
	@Autowired
	private PersistentObjects po;
	@RequestMapping("/hola")
	public String test()
	{
		return "Hola Mundo!";
	}

	@RequestMapping("/crearSesion/{key}/{timeout}")
	public void crearSesion(@PathVariable long key, @PathVariable long timeout){
		po.createSession(key, timeout);
	}
}
