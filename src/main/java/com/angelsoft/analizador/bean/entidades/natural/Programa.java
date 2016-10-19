/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosPrograma;

/**
 * @author a.garcia
 *
 */
public class Programa extends ElementoNatural implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Programa(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosPrograma.NOMBRE);
	}

	public Programa() {
		super();
		this.parametros = new ParametrosElementos(ParametrosPrograma.NOMBRE);
	}
}
