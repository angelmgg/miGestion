/**
 *
 */
package com.angelsoft.analizador.bean.entidades.natural;

import java.io.File;
import java.io.Serializable;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosDdm;

/**
 * @author a.garcia
 *
 */
public class Ddm extends ElementoNatural implements Serializable{

	private int fnr;
	private int db;
	private String nombreDdm;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Ddm(){
		super();
		this.parametros = new ParametrosElementos(ParametrosDdm.NOMBRE);
	}

	public Ddm(File elemento){
		super(elemento);
		this.parametros = new ParametrosElementos(ParametrosDdm.NOMBRE);
	}

	/**
	 * @return the fnr
	 */
	public int getFnr() {
		return fnr;
	}

	/**
	 * @param fnr the fnr to set
	 */
	public void setFnr(int fnr) {
		this.fnr = fnr;
	}

	/**
	 * @return the db
	 */
	public int getDb() {
		return db;
	}

	/**
	 * @param db the db to set
	 */
	public void setDb(int db) {
		this.db = db;
	}

	/**
	 * @return the nombreDdm
	 */
	public String getNombreDdm() {
		return nombreDdm;
	}

	/**
	 * @param nombreDdm the nombreDdm to set
	 */
	public void setNombreDdm(String nombreDdm) {
		this.nombreDdm = nombreDdm;
	}
}