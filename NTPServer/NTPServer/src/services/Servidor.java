package services;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("Servidor")//ruta a la clase
public class Servidor {
	Calendar calendario = Calendar.getInstance();
	static long t1,t2;
	@GET //tipo de petición HTTP
	@Produces(MediaType.TEXT_PLAIN) //tipo de texto devuelto
	@Path("pedirTiempo") //ruta al método
	public String pedirTiempo() throws InterruptedException //el método debe retornar String
	{
		t1=tiempo();
		TimeUnit.SECONDS.sleep( (long) (1 + Math.random()*3));
		t2=tiempo();
		return (t1+":"+t2);
	}

	
	
	
	public long tiempo() //el método debe retornar String
	{
		return System.currentTimeMillis();
	}
}
