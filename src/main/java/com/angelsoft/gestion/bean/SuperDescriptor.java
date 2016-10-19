package com.angelsoft.gestion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SuperDescriptor implements Serializable, Cloneable{

	private String nombreCorto = "";
	private String nombre = "";
	private String descripcion = "";
	private List<Object> listaDetalle= new ArrayList<Object>();

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
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
	
	@Override
	public String toString() {
		return toStringEditado();
	}
	
	public String toStringEditado() {
		return "   " + nombreCorto + " " + nombre + " "  + ((descripcion != null && !"".equalsIgnoreCase(descripcion))? "( " + descripcion + " )":"");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((listaDetalle == null) ? 0 : listaDetalle.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombreCorto == null) ? 0 : nombreCorto.hashCode());
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
		SuperDescriptor other = (SuperDescriptor) obj;
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
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nombreCorto == null) {
			if (other.nombreCorto != null)
				return false;
		} else if (!nombreCorto.equals(other.nombreCorto))
			return false;
		return true;
	}

}
