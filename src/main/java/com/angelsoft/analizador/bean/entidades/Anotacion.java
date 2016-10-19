package com.angelsoft.analizador.bean.entidades;
import java.util.List;

/**
 * 
 */

/**
 * @author a.garcia
 *
 */
public class Anotacion {

	private String tipo;
	private List<Object> listaDetalle;
	
	public Anotacion(String tipo, List<Object> listaDetalle){
		this.tipo = tipo;
		this.listaDetalle = listaDetalle;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @return the listaDetalle
	 */
	public List<Object> getListaDetalle() {
		return listaDetalle;
	}

}
