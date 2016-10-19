package com.angelsoft.gestion.dao;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.InicializacionCodigo;
import com.angelsoft.gestion.bean.auxiliares.orden.OrdenInicializacionPorTablaCod;
import com.angelsoft.gestion.bean.entidades.orden.OrdenDdmPorDbFnrAsc;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.utiles.UtilesXMLInicializacion;

public class InicializacionCodigoDao {

	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public List<InicializacionCodigo> traeInicializacionCodigos() {
		List<InicializacionCodigo> lista = null;
		
		try {
			// PENDIENTE
			String ruta = getClass().getResource(Constantes.PATH_RECURSOS + Constantes.XML_INICIALICACIONES).getPath();
			lista = UtilesXMLInicializacion.leeXMLInicializaciones(ruta);
			Collections.sort(lista, new OrdenInicializacionPorTablaCod());
			
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error leyendo el fichero. " + ex.toString());
			throw new RuntimeException(ex);
		}
		
		return lista;
	}
}
