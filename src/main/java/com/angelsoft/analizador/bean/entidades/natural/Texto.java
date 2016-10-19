/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosTexto;

/**
 * @author a.garcia
 *
 */
public class Texto extends ElementoNatural implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Texto(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosTexto.NOMBRE);

	}

	public Texto() {
		super();
		this.parametros = new ParametrosElementos(ParametrosTexto.NOMBRE);
	}


}
