package com.angelsoft.gestion.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.Nota;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.utiles.UtilesXMLNotas;

public class NotaDao {

	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public List<Nota> traeNotas() {
		List<Nota> lista = null;
		
		try {
			String ruta = getClass().getResource(Constantes.PATH_RECURSOS + Constantes.XML_NOTAS).getPath();
			lista = UtilesXMLNotas.leeXMLNotas(ruta);
			
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error leyendo el fichero. " + ex.toString());
			throw new RuntimeException(ex);
		}
		
		return lista;
	}
}
