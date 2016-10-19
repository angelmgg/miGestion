package com.angelsoft.gestion.manager;

import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.Campo;
import com.angelsoft.gestion.bean.SuperDescriptor;
import com.angelsoft.gestion.bean.auxiliares.Fichero;
import com.angelsoft.gestion.bean.auxiliares.FicheroAUX;
import com.angelsoft.gestion.bean.entidades.Ddm;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.utiles.UtilesObjetos;

public class FicheroManager {
	private static FicheroManager instance;
	private static Logger logger; 
	
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	private FicheroManager() {
	}
	
	/**
	 * @return 
	 */
	public static FicheroManager getInstance() {
		if (instance == null) {
			instance = new FicheroManager();
		}
		return instance;
	}
	
	public boolean generaFicheroDdm(String nombreFichero, List<Ddm> listaDdms){
		boolean resultado = true;
		
		SortedMap<String, Fichero> mapaFicheros = new TreeMap<String, Fichero>();
		
		try{
			for (Ddm ddmAux: listaDdms){
				FicheroAUX ficheroAux = new FicheroAUX(nombreFichero+ ddmAux.getNombre(), Constantes.EXTENSION_TXT);
				
				pasaAFichero(ficheroAux, ddmAux);
				
				mapaFicheros.put(ddmAux.getNombre(), ficheroAux);
			}
			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
        
		} finally {
        	
           try {
   				Iterator<String> iteradorMapa = mapaFicheros.keySet().iterator();
   				while(iteradorMapa.hasNext()){
   					String nombreFicheroAux = iteradorMapa.next();
   					Fichero ficheroAux = (Fichero) mapaFicheros.get(nombreFicheroAux);
   					ficheroAux.close();

   					logger.log(Level.INFO, "Se ha creado -->" + ficheroAux.getPath());
   				}
           } catch (Exception ex) {
        	   logger.log(Level.SEVERE, null, ex);
           }
        }
		
		return resultado;
	}

	public void pasaAFichero(Fichero fichero, Ddm ddmAux){
		List<Campo> camposDdm = ddmAux.getListaCampos();
		
		for (Campo campoAux: camposDdm){
			String cadena = UtilesObjetos.formatoCreacion(campoAux);
			if (!"".equalsIgnoreCase(cadena)){
				fichero.escribe(cadena);
			}
		}
		
		List<SuperDescriptor> superDdm = ddmAux.getListaSuperDescriptores();
		for (SuperDescriptor superAux: superDdm){
			String cadena = UtilesObjetos.formatoCreacion(superAux);
			if (!"".equalsIgnoreCase(cadena)){
				fichero.escribe(cadena);
			}
		}
	}

}
