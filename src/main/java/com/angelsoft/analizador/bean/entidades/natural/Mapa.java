/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosMapa;

/**
 * @author a.garcia
 *
 */
public class Mapa extends ElementoNatural implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Mapa(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosMapa.NOMBRE);
	}

	public Mapa() {
		super();
		this.parametros = new ParametrosElementos(ParametrosMapa.NOMBRE);
	}
}
