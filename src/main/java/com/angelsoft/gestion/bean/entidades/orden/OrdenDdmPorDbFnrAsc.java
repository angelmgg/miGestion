package com.angelsoft.gestion.bean.entidades.orden;

import java.util.Comparator;

import com.angelsoft.gestion.bean.entidades.Ddm;

public class OrdenDdmPorDbFnrAsc implements Comparator<Object>{
	
	/**
	 * 
	 */
	public int compare(Object o1, Object o2){
		Ddm a1 = (Ddm) o1;
		Ddm a2 = (Ddm) o2;
		
		int aux = 0;
		
		if (a1.getDb() != null){
			if (a2.getDb() != null){
				aux = (-1) * a1.getDb().compareTo(a2.getDb());
			} else {
				aux = 1;
			}
		} else {
			if (a2.getDb() != null){
				aux = -1;
			} else {
				aux = 0;
			}
		}
		
		if (aux == 0) {
			aux = (-1) * a1.getFnr().compareTo(a2.getFnr());
		}

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
