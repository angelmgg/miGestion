package com.angelsoft.gestion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Estructura implements Serializable, Cloneable{

	private Integer nivel = 0;
	private String nombre = "";
	private String descripcion = "";
	private List<Object> listaDetalle= new ArrayList<Object>();

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Object> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<Object> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}
	
	public List<Campo> getListaCampos() {
		List<Campo> listaAux = new ArrayList<Campo>();
		for (Object objetoAux: listaDetalle){
			if (objetoAux instanceof Campo){
				listaAux.add((Campo) objetoAux);
				if (!((Campo) objetoAux).getRedefine().isEmpty()){
					listaAux.addAll(((Campo) objetoAux).getListaCampos());
				}
			} else if (objetoAux instanceof Estructura){
				listaAux.addAll(((Estructura) objetoAux).getListaCampos());
			}
		}
		return listaAux;
	}
	
	@Override
	public Estructura clone() throws CloneNotSupportedException {
		return (Estructura) super.clone();
	}

	@Override
	public String toString() {
		return toStringEditado();
	}
	
	public String toStringEditado() {
		return " " + nivel + " " + nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((listaDetalle == null) ? 0 : listaDetalle.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estructura other = (Estructura) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (listaDetalle == null) {
			if (other.listaDetalle != null)
				return false;
		} else if (!listaDetalle.equals(other.listaDetalle))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}
