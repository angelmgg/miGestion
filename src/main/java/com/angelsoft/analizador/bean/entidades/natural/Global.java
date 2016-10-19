/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosGlobal;

/**
 * @author a.garcia
 *
 */
public class Global extends ElementoNatural implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Global(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosGlobal.NOMBRE);
	}

	public Global() {
		super();
		this.parametros = new ParametrosElementos(ParametrosGlobal.NOMBRE);
	}
}
