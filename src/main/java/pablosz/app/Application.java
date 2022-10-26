package pablosz.app;

import javax.persistence.EntityManager;
import pablosz.app.PersistentObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



// +---------------------------------------------------------+
// | NOTA: Si queremos organizar los componentes y entidades | 
// | dentro de diferentes paquetes tendremos que especificar |
// | explicitamente cada uno de estos descomenando las       |
// | siguientes lineas: @ComponentScan y @EntityScan         |
// +---------------------------------------------------------+
// @ComponentScan(basePackages={"pablosz.pkgcomponentes1,pablosz.pkgcomponentes2,etc..."})
// @EntityScan(basePackages={"pablosz.pkgdomain1,pkgpablosz.domain2,etc"})

@SpringBootApplication
public class Application implements CommandLineRunner
{
    private static Logger LOG = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class,args);
	}
	

	@Autowired
	private PersistentObjects po;
	
	@Override
	public void run(String... args) throws Exception
	{

		LOG.info("Todo funciona correctamente? "+(po!=null));
	}
}
