/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosCopycode;

/**
 * @author a.garcia
 *
 */
public class Copycode extends ElementoNatural implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Copycode(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosCopycode.NOMBRE);

	}

	public Copycode() {
		super();
		this.parametros = new ParametrosElementos(ParametrosCopycode.NOMBRE);
	}
}
