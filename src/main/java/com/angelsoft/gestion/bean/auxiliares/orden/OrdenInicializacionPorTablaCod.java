package com.angelsoft.gestion.bean.auxiliares.orden;

import java.util.Comparator;

import com.angelsoft.gestion.bean.auxiliares.InicializacionCodigo;

public class OrdenInicializacionPorTablaCod implements Comparator<Object>{
	
	/**
	 * 
	 */
	public int compare(Object o1, Object o2){
		InicializacionCodigo a1 = (InicializacionCodigo) o1;
		InicializacionCodigo a2 = (InicializacionCodigo) o2;
		
		int aux = 0;
		
		aux = (-1) * a1.getTabla().compareTo(a2.getTabla());

		if (aux == 0) {
			aux = (-1) * a1.getCodigo().compareTo(a2.getCodigo());
		}

		return aux;
	}
	
	/**
	 * 
	 */
	public boolean equals(Object o){
		return this == o;
	}
}
