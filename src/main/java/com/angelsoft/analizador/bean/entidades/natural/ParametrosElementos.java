package com.angelsoft.analizador.bean.entidades.natural;

import com.angelsoft.analizador.ctes.parametros.ParametrosCopycode;
import com.angelsoft.analizador.ctes.parametros.ParametrosDdm;
import com.angelsoft.analizador.ctes.parametros.ParametrosGlobal;
import com.angelsoft.analizador.ctes.parametros.ParametrosHelprutina;
import com.angelsoft.analizador.ctes.parametros.ParametrosLocal;
import com.angelsoft.analizador.ctes.parametros.ParametrosMapa;
import com.angelsoft.analizador.ctes.parametros.ParametrosParameter;
import com.angelsoft.analizador.ctes.parametros.ParametrosPrograma;
import com.angelsoft.analizador.ctes.parametros.ParametrosSubprograma;
import com.angelsoft.analizador.ctes.parametros.ParametrosSubrutina;
import com.angelsoft.analizador.ctes.parametros.ParametrosTexto;

public class ParametrosElementos implements Comparable<ParametrosElementos>{

	private String nombre;
	private String extGP;
	private String extSRC;
	private Boolean leeSoloPrimeraLinea;
	private Boolean quitanLineasAsteriscadas;
	
	public ParametrosElementos(String tipo) {
		super();
		if (tipo.equalsIgnoreCase(ParametrosCopycode.NOMBRE)){
			this.nombre = ParametrosCopycode.NOMBRE;
			this.extGP = ParametrosCopycode.EXT_GP;
			this.extSRC = ParametrosCopycode.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosCopycode.LEER_SOLO_PRIMERALINEA;
			this.quitanLineasAsteriscadas = ParametrosCopycode.QUITAR_ASTERISCADAS;
		} else if (tipo.equalsIgnoreCase(ParametrosDdm.NOMBRE)){
			this.nombre = ParametrosDdm.NOMBRE;
			this.extGP = ParametrosDdm.EXT_GP;
			this.extSRC = ParametrosDdm.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosDdm.LEER_SOLO_PRIMERALINEA;
			this.quitanLineasAsteriscadas = ParametrosDdm.QUITAR_ASTERISCADAS;
		} else if (tipo.equalsIgnoreCase(ParametrosGlobal.NOMBRE)){
			this.nombre = ParametrosGlobal.NOMBRE;
			this.extGP = ParametrosGlobal.EXT_GP;
			this.extSRC = ParametrosGlobal.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosGlobal.LEER_SOLO_PRIMERALINEA;
			this.quitanLineasAsteriscadas = ParametrosGlobal.QUITAR_ASTERISCADAS;
		} else if (tipo.equalsIgnoreCase(ParametrosHelprutina.NOMBRE)){
			this.nombre = ParametrosHelprutina.NOMBRE;
			this.extGP = ParametrosHelprutina.EXT_GP;
			this.extSRC = ParametrosHelprutina.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosHelprutina.LEER_SOLO_PRIMERALINEA;
			this.quitanLineasAsteriscadas = ParametrosHelprutina.QUITAR_ASTERISCADAS;
		} else if (tipo.equalsIgnoreCase(ParametrosLocal.NOMBRE)){
			this.nombre = ParametrosLocal.NOMBRE;
			this.extGP = ParametrosLocal.EXT_GP;
			this.extSRC = ParametrosLocal.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosLocal.LEER_SOLO_PRIMERALINEA;
			this.quitanLineasAsteriscadas = ParametrosLocal.QUITAR_ASTERISCADAS;
		} else if (tipo.equalsIgnoreCase(ParametrosMapa.NOMBRE)){
			this.nombre = ParametrosMapa.NOMBRE;
			this.extGP = ParametrosMapa.EXT_GP;
			this.extSRC = ParametrosMapa.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosMapa.LEER_SOLO_PRIMERALINEA;
			this.quitanLineasAsteriscadas = ParametrosMapa.QUITAR_ASTERISCADAS;
		} else if (tipo.equalsIgnoreCase(ParametrosParameter.NOMBRE)){
			this.nombre = ParametrosParameter.NOMBRE;
			this.extGP = ParametrosParameter.EXT_GP;
			this.extSRC = ParametrosParameter.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosParameter.LEER_SOLO_PRIMERALINEA;
			this.quitanLineasAsteriscadas = ParametrosParameter.QUITAR_ASTERISCADAS;
		} else if (tipo.equalsIgnoreCase(ParametrosPrograma.NOMBRE)){
			this.nombre = ParametrosPrograma.NOMBRE;
			this.extGP = ParametrosPrograma.EXT_GP;
			this.extSRC = ParametrosPrograma.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosPrograma.LEER_SOLO_PRIMERALINEA;
			this.quitanLineasAsteriscadas = ParametrosPrograma.QUITAR_ASTERISCADAS;
		} else if (tipo.equalsIgnoreCase(ParametrosSubprograma.NOMBRE)){
			this.nombre = ParametrosSubprograma.NOMBRE;
			this.extGP = ParametrosSubprograma.EXT_GP;
			this.extSRC = ParametrosSubprograma.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosSubprograma.QUITAR_ASTERISCADAS;
			this.quitanLineasAsteriscadas = ParametrosSubprograma.LEER_SOLO_PRIMERALINEA;
		} else if (tipo.equalsIgnoreCase(ParametrosSubrutina.NOMBRE)){
			this.nombre = ParametrosSubrutina.NOMBRE;
			this.extGP = ParametrosSubrutina.EXT_GP;
			this.extSRC = ParametrosSubrutina.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosSubrutina.LEER_SOLO_PRIMERALINEA;
			this.quitanLineasAsteriscadas = ParametrosSubrutina.QUITAR_ASTERISCADAS;
		}	else if (tipo.equalsIgnoreCase(ParametrosTexto.NOMBRE)){
			this.nombre = ParametrosTexto.NOMBRE;
			this.extGP = ParametrosTexto.EXT_GP;
			this.extSRC = ParametrosTexto.EXT_SRC;
			this.leeSoloPrimeraLinea = ParametrosTexto.LEER_SOLO_PRIMERALINEA;
			this.quitanLineasAsteriscadas = ParametrosTexto.QUITAR_ASTERISCADAS;
		}
	
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the extGP
	 */
	public String getExtGP() {
		return extGP;
	}

	/**
	 * @return the extSRC
	 */
	public String getExtSRC() {
		return extSRC;
	}

	/**
	 * @return the leeSoloPrimeraLinea
	 */
	public Boolean seLeeSoloPrimeraLinea() {
		return leeSoloPrimeraLinea;
	}

	/**
	 * @return the quitarlineasAsteriscadas
	 */
	public Boolean seQuitanLineasAsteriscadas() {
		return quitanLineasAsteriscadas;
	}
	
   /**
   *
   */
   public boolean equals( Object a ) {
     if ( this == a ) return true;
     if ( !(a instanceof ParametrosElementos) ) return false;

     ParametrosElementos parametroAux = (ParametrosElementos)a;
     return
       ( this.nombre == parametroAux.nombre ) &&
       ( this.extGP == parametroAux.extGP ) &&
       ( this.extSRC == parametroAux.extSRC );
   }
   
   public int compareTo( ParametrosElementos parametroAux){
		
		int aux = 0;
		
		aux = this.getNombre().compareTo(parametroAux.getNombre());
		
		if (aux == 0) {
			aux = (-1) * this.getExtSRC().compareTo(parametroAux.getExtSRC());
		}
		if (aux == 0) {
			aux = (-1) * this.getExtGP().compareTo(parametroAux.getExtGP());
		}
		
		return aux;
   }
}
