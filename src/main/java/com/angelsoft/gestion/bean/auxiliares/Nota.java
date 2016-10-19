package com.angelsoft.gestion.bean.auxiliares;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Nota implements Serializable, Cloneable {
	
	private Long ide;
	private String entidad = "";
	private String texto = "";
	
	public Long getIde() {
		return ide;
	}

	public void setIde(Long ide) {
		this.ide = ide;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entidad == null) ? 0 : entidad.hashCode());
		result = prime * result + ((ide == null) ? 0 : ide.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
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
		Nota other = (Nota) obj;
		if (entidad == null) {
			if (other.entidad != null)
				return false;
		} else if (!entidad.equals(other.entidad))
			return false;
		if (ide == null) {
			if (other.ide != null)
				return false;
		} else if (!ide.equals(other.ide))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

	@Override
	public Nota clone() throws CloneNotSupportedException {
		return (Nota) super.clone();
	}

	@Override
	public String toString() {
		return toStringEditado();
	}
	
	public String toStringEditado() {
		return ((!"".equalsIgnoreCase(entidad)?entidad + " - ":"")) + texto;
	}	
}
