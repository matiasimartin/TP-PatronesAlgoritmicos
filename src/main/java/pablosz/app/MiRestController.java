package pablosz.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiRestController
{
	@RequestMapping("/hola")
	public String test()
	{
		return "Hola Mundo!";
	}
}
