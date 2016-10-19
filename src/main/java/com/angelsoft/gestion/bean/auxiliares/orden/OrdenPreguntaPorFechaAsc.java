package com.angelsoft.gestion.bean.auxiliares.orden;

import java.util.Comparator;

import com.angelsoft.gestion.bean.auxiliares.Pregunta;

public class OrdenPreguntaPorFechaAsc implements Comparator<Object>{
	
	/**
	 * 
	 */
	public int compare(Object o1, Object o2){
		Pregunta a1 = (Pregunta) o1;
		Pregunta a2 = (Pregunta) o2;
		
		int aux = 0;
		
		if (a1.getFecha() != null){
			if (a2.getFecha() != null){
				aux = (-1) * a1.getFecha().compareTo(a2.getFecha());
			} else {
				aux = 1;
			}
		} else {
			if (a2.getFecha() != null){
				aux = -1;
			} else {
				aux = 0;
				
			}
		}

		if (aux == 0) {
			if (a1.getTipo() != null){
				if (a2.getTipo() != null){
					aux = (-1) * a1.getTipo().compareTo(a2.getTipo());
				} else {
					aux = 1;
				}
			} else {
				if (a2.getTipo() != null){
					aux = -1;
				} else {
					aux = 0;
				}
			}
		}
			
		if (aux == 0) {
			aux = (-1) * a1.getTexto().compareTo(a2.getTexto());
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
