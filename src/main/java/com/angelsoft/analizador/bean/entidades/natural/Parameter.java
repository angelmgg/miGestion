/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosParameter;

/**
 * @author a.garcia
 *
 */
public class Parameter extends ElementoNatural implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Parameter(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosParameter.NOMBRE);
	}

	public Parameter() {
		super();
		this.parametros = new ParametrosElementos(ParametrosParameter.NOMBRE);
	}
}
