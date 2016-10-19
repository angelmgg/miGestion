package com.angelsoft.gestion.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.Tarea;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.utiles.UtilesXMLTareas;

public class TareaDao {

	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public List<Tarea> traeTareas() {
		List<Tarea> lista = null;
		
		try {
			// PENDIENTE
			String ruta = getClass().getResource(Constantes.PATH_RECURSOS + Constantes.XML_TAREAS).getPath();
			lista = UtilesXMLTareas.leeXMLTareas(ruta);
			
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error leyendo el fichero. " + ex.toString());
			throw new RuntimeException(ex);
		}
		
		return lista;
	}
}
