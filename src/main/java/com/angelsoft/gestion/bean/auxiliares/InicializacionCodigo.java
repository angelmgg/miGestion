package com.angelsoft.gestion.bean.auxiliares;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InicializacionCodigo implements Serializable, Cloneable {

	private Long ide;
	private String tabla = "";
	private String codigo = "";
	private String valor = "";
	private String observacion = "";
	
	public Long getIde() {
		return ide;
	}

	public void setIde(Long ide) {
		this.ide = ide;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((ide == null) ? 0 : ide.hashCode());
		result = prime * result + ((tabla == null) ? 0 : tabla.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		InicializacionCodigo other = (InicializacionCodigo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (ide == null) {
			if (other.ide != null)
				return false;
		} else if (!ide.equals(other.ide))
			return false;
		if (tabla == null) {
			if (other.tabla != null)
				return false;
		} else if (!tabla.equals(other.tabla))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public InicializacionCodigo clone() throws CloneNotSupportedException {
		return (InicializacionCodigo) super.clone();
	}

	@Override
	public String toString() {
		return tabla + " " + codigo + " "+ valor;
	}
}
