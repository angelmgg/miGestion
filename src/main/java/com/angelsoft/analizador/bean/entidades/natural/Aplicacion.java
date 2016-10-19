package com.angelsoft.analizador.bean.entidades.natural;

import java.io.Serializable;
import java.util.Calendar;

public class Aplicacion implements Serializable, Cloneable, Comparable<Aplicacion>  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ide;
	private String nombre;
	private String descripcion;
	private String path;
	private String programaArranque;
	private Calendar fcArranque;
	private Boolean elementosCargados;
	private Boolean procesosCargados;
	public Long getIde() {
		return ide;
	}
	public void setIde(Long ide) {
		this.ide = ide;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getProgramaArranque() {
		return programaArranque;
	}
	public void setProgramaArranque(String programaArranque) {
		this.programaArranque = programaArranque;
	}
	public Calendar getFcArranque() {
		return fcArranque;
	}
	public void setFcArranque(Calendar fcArranque) {
		this.fcArranque = fcArranque;
	}
	public Boolean getElementosCargados() {
		return elementosCargados;
	}
	public void setElementosCargados(Boolean elementosCargados) {
		this.elementosCargados = elementosCargados;
	}
	public Boolean getProcesosCargados() {
		return procesosCargados;
	}
	public void setProcesosCargados(Boolean procesosCargados) {
		this.procesosCargados = procesosCargados;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((elementosCargados == null) ? 0 : elementosCargados.hashCode());
		result = prime * result + ((fcArranque == null) ? 0 : fcArranque.hashCode());
		result = prime * result + ((ide == null) ? 0 : ide.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((procesosCargados == null) ? 0 : procesosCargados.hashCode());
		result = prime * result + ((programaArranque == null) ? 0 : programaArranque.hashCode());
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
		Aplicacion other = (Aplicacion) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (elementosCargados == null) {
			if (other.elementosCargados != null)
				return false;
		} else if (!elementosCargados.equals(other.elementosCargados))
			return false;
		if (fcArranque == null) {
			if (other.fcArranque != null)
				return false;
		} else if (!fcArranque.equals(other.fcArranque))
			return false;
		if (ide == null) {
			if (other.ide != null)
				return false;
		} else if (!ide.equals(other.ide))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (procesosCargados == null) {
			if (other.procesosCargados != null)
				return false;
		} else if (!procesosCargados.equals(other.procesosCargados))
			return false;
		if (programaArranque == null) {
			if (other.programaArranque != null)
				return false;
		} else if (!programaArranque.equals(other.programaArranque))
			return false;
		return true;
	}
	
	public int compareTo(Aplicacion o) {
		return this.nombre.compareTo(o.nombre);
	}
}
