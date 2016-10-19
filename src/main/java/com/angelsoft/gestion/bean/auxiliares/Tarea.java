package com.angelsoft.gestion.bean.auxiliares;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Tarea implements Serializable, Cloneable {
	private Long ide;
	private Date fecha = null;
	private String descripcion = "";
	private String observacion = "";
	private String estado = "";
			
	@Override
	public Tarea clone() throws CloneNotSupportedException {
		return (Tarea) super.clone();
	}

	public Long getIde() {
		return ide;
	}

	public void setIde(Long ide) {
		this.ide = ide;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return toStringEditado();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((observacion == null) ? 0 : observacion.hashCode());		
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((ide == null) ? 0 : ide.hashCode());
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
		Tarea other = (Tarea) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (ide == null) {
			if (other.ide != null)
				return false;
		} else if (!ide.equals(other.ide))
			return false;
		return true;
	}

	public String toStringEditado() {
		return ((fecha != null)?fecha + " - ":"") + descripcion + " " + estado;
	}	
}
