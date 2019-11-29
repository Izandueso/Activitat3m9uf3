import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eX {
	
	@SuppressWarnings("rawtypes")
	public static void main (String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		try {
			
			//Demanem la URL
			System.out.println("Introdueix una URL: exemple -> http://insbaixcamp.org/index.php/2013-10-29-12-24-12/informacio-general-4 <-");
			String urlinsti = teclado.nextLine();
			
			//Demanem els camps de cap�alera que es volen
			System.out.println("Introdueix els camps de cap�alera que vols: exemple -> 1 , 2 , 3 , 4 <-");
			int campCap�alera = teclado.nextInt();
			
			//Demanem el text per mostrar arguments
			System.out.println("Introdueix un text per mostrar arguments, exemple: ->  #java cerca  http://insbaixcamp.org/  5  script <-");
			String textarguments = teclado.nextLine();
			
			String cadena;
			URL url = new URL(urlinsti);
			URLConnection connexio = url.openConnection();
			
			//Mostrem la data i contingut
			System.out.println("===============================================================");
			System.out.println("ADRE�A, DARA I CONTINGUT");
			System.out.println("Adre�a [getURL]: " + connexio.getURL());
			
			//Mostrem la ultima data de modificacio
			Date data = new Date(connexio.getLastModified());
			System.out.println("Data �ltima modificaci� [getLastModified()]: " + data);
			System.out.println("Tipus de Contingut [getContentType()]" + connexio.getContentType());
			
			System.out.println("===============================================================");
			System.out.println("TOTS ELS CAMPS DE CAP�ALERA AMB getHeaderFields(): ");
			
			//Fem servir una estructura Map per a recuperar cap�aleres
			Map campsCap�alera = connexio.getHeaderFields();
			Iterator it = campsCap�alera.entrySet().iterator();
			
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				System.out.println(map.getKey() + ":" + map.getValue());
			}
			
			//Mostrem la quantitat de camps de cap�elera que ens ha demanat el usuari
			System.out.println("===============================================================");
			System.out.println("Camps de Cap�alera");
			for (int i = 0; i < campCap�alera; i++) {
				System.out.println("getHeaderField(" + i + ")=>"  + connexio.getHeaderField(i));
			}
			System.out.println("===============================================================");
			
			System.out.println("Contingut de [url.getFile()]: " + url.getFile());
			BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));
			
			while ((cadena = pagina.readLine()) != null) {
				
				System.out.println(cadena);
			}
			
		}
		catch (MalformedURLException e) { e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		
		
	}
	boolean isGifFormat(URL url){
		 boolean ret = false;
		 try {
		 URLConnection con = url.openConnection();
		 String headerType = con.getContentType();
		 String guessType = con.guessContentTypeFromName(url.
		getFile());
		 ret = headerType.endsWith("script") || guessType.endsWith("script");
		 } catch (IOException ex) {
		 Logger.getLogger(eX.class.getName()).log(Level.
		SEVERE, null, ex);
		 }
		 return ret;
		 }

	
	

}
