/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosLocal;

/**
 * @author a.garcia
 *
 */
public class Local extends ElementoNatural implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Local(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosLocal.NOMBRE);
	}

	public Local() {
		super();
		this.parametros = new ParametrosElementos(ParametrosLocal.NOMBRE);
	}
}
