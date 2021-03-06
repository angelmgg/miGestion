/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosHelprutina;

/**
 * @author a.garcia
 *
 */
public class Helprutina extends ElementoNatural implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Helprutina(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosHelprutina.NOMBRE);
	}

	public Helprutina() {
		super();
		this.parametros = new ParametrosElementos(ParametrosHelprutina.NOMBRE);
	}
}
