/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosSubrutina;

/**
 * @author a.garcia
 *
 */
public class Subrutina extends ElementoNatural implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Subrutina(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosSubrutina.NOMBRE);
	}

	public Subrutina() {
		super();
		this.parametros = new ParametrosElementos(ParametrosSubrutina.NOMBRE);
	}
}
