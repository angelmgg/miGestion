package com.angelsoft.gestion.bean.entidades.orden;

import java.util.Comparator;

import com.angelsoft.gestion.bean.entidades.Ddm;

public class OrdenDdmPorNombreAsc implements Comparator<Object>{
	
	/**
	 * 
	 */
	public int compare(Object o1, Object o2){
		Ddm a1 = (Ddm) o1;
		Ddm a2 = (Ddm) o2;
		
		int aux = 0;
		
		if (aux == 0) {
			aux = (-1) * a1.getNombre().compareTo(a2.getNombre());
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
