package com.angelsoft.gestion.dao;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.entidades.Ddm;
import com.angelsoft.gestion.bean.entidades.orden.OrdenDdmPorDbFnrAsc;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.utiles.UtilesXMLDefiniciones;

public class DdmDao {

	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public List<Ddm> traeDdms() {
		List<Ddm> lista = null;
		
		try {
			String ruta = getClass().getResource(Constantes.PATH_RECURSOS + Constantes.XML_DEFINICIONES).getPath();
			lista = UtilesXMLDefiniciones.leeXMLDdms(ruta);
			Collections.sort(lista, new OrdenDdmPorDbFnrAsc());
			
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error leyendo el fichero. " + ex.toString());
			throw new RuntimeException(ex);
		}
		
		return lista;
	}
}
