/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosSubprograma;

/**
 * @author a.garcia
 *
 */
public class Subprograma extends ElementoNatural implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Subprograma(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosSubprograma.NOMBRE);
	}

	public Subprograma() {
		super();
		this.parametros = new ParametrosElementos(ParametrosSubprograma.NOMBRE);
	}
}
