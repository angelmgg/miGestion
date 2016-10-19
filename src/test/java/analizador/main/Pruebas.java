package analizador.main;

import java.io.File;
import java.util.List;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.bean.entidades.NombreArchivo;
import com.angelsoft.analizador.bean.entidades.natural.Aplicacion;
import com.angelsoft.analizador.ctes.ConstantesNatural;
import com.angelsoft.analizador.manager.ElementoNaturalManager;
import com.angelsoft.analizador.manager.impl.ElementoNaturalManagerImpl;
import com.angelsoft.analizador.utiles.UtilesPath;

public class Pruebas {

	static ElementoNaturalManager elementoNaturalManager = new ElementoNaturalManagerImpl();
	
	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public static void main(String[] args) {

		leeAplicaciones();
	}
	
	public static void leeAplicaciones() {
		List<Aplicacion> lista = null;
		SortedMap<NombreArchivo, ElementoNatural> mapaElementos = null;

		try {
			lista = UtilesPath.extraeAplicacionesDirectorio(lista, new File(ConstantesNatural.DIR_CODIGO_FUENTE+ "GCTOOLS\\"));
			mapaElementos = elementoNaturalManager.sacaElementosAplicaciones(lista);
			System.out.println(mapaElementos.size());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "leeXMLDefinicion " + e.toString());
		}
		
		System.out.println("APLICACIONES");
		System.out.println("------------");
		for(Aplicacion apli: lista){
			System.out.println(" - " + apli.getNombre());
		}
	}
}
