package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Cliente {
	
	public static void main(String[] args) throws IOException {

		String dir[] = {"192.168.0.69:8080","192.168.0.8:8080"};

		for (String ip : dir) {	
			double mejorPar[]= {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};//el 1 es offset y el 0 delay
	
			for(int i=0; i<8; i++) {
				
				double t0 = System.currentTimeMillis();
				double t1 = 0, t2 = 0;		
				URL urlListo = null;
				StringBuilder dorsal = new StringBuilder();
				
				urlListo = new URL("http://"+ip+"/NTPServer/Servidor/pedirTiempo");
				
				HttpURLConnection connListo = null;
				connListo = (HttpURLConnection) urlListo.openConnection();
				connListo.setRequestMethod("GET");
				if(connListo.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "+ connListo.getResponseCode());
				}else {
				
					BufferedReader rd = new BufferedReader(new InputStreamReader(connListo.getInputStream()));
					String linea;
					// Mientras el BufferedReader se pueda leer, agregar contenido a dorsal
					while ((linea = rd.readLine()) != null) {
						String[] tiempitos = linea.split(":");
						t1=Double.parseDouble(tiempitos[0]);
						t2=Double.parseDouble(tiempitos[1]);
					}
					double t3 = System.currentTimeMillis();
					// Cerrar el BufferedReader
					rd.close();
					
					double offset = (t1-t0+t2-t3)/2;
					double delay = t1-t0+t3-t2;
					double thispar[] = {delay, offset};
					
					if( delay < mejorPar[0]) mejorPar=thispar;
				 
				}
			}
			
			System.out.println("Para la máquina con ip ("+ip+"):\n\n\tEl offset es --> "+mejorPar[1]+"\n\n\tEl delay es -->"+mejorPar[0]+"\n\n");
		}
		System.out.println("Para esta máquina:\n\n\tEl offset es --> 0\n\n\tEl delay es -->0\n\n");
	}
}
