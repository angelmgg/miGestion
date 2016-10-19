package com.angelsoft.gestion.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ForeingKey implements Serializable, Cloneable{

	private String tabla = "";
	private String campo = "";

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	@Override
	public String toString() {
		return toStringEditado();
	}
	
	public String toStringEditado() {
		return "FK[" + campo + " de " + tabla + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((tabla == null) ? 0 : tabla.hashCode());
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
		ForeingKey other = (ForeingKey) obj;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (tabla == null) {
			if (other.tabla != null)
				return false;
		} else if (!tabla.equals(other.tabla))
			return false;
		return true;
	}

}
