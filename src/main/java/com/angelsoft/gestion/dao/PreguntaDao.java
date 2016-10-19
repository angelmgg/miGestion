package com.angelsoft.gestion.dao;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.Pregunta;
import com.angelsoft.gestion.bean.auxiliares.orden.OrdenPreguntaPorFechaAsc;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.utiles.UtilesXMLPreguntas;

public class PreguntaDao {

	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public List<Pregunta> traePreguntas() {
		List<Pregunta> lista = null;
		
		try {
			// PENDIENTE
			String ruta = getClass().getResource(Constantes.PATH_RECURSOS + Constantes.XML_PREGUNTAS).getPath();
			lista = UtilesXMLPreguntas.leeXMLPreguntas(ruta);
			Collections.sort(lista, new OrdenPreguntaPorFechaAsc());
			
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error leyendo el fichero. " + ex.toString());
			throw new RuntimeException(ex);
		}
		
		return lista;
	}
}
