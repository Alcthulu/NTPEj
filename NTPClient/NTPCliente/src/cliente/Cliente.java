package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Cliente {
	
	public static void main(String[] args) {
		double mejorPar[]= {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
		for(int i=0; i<8; i++) {
			
			double t0 = System.currentTimeMillis();
			double t1, t2;		
			URL urlListo = null;
			StringBuilder dorsal = new StringBuilder();
			
			urlListo = new URL("http://192.168.0.69:8080/NTPServer/Servidor/pedirTiempo");
			
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
		}
	}
	
}
