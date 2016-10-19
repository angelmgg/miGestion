package com.angelsoft.gestion.bean.auxiliares;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Pregunta implements Serializable, Cloneable {

	private Long ide;
	private Date fecha = null;
	private String tipo = "";
	private String texto = "";
	private List<Respuesta> listaRespuestas = new ArrayList<Respuesta>();
	
	@Override
	public Pregunta clone() throws CloneNotSupportedException {
		return (Pregunta) super.clone();
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<Respuesta> getListaRespuestas() {
		return listaRespuestas;
	}

	public void setListaRespuestas(List<Respuesta> listaRespuestas) {
		this.listaRespuestas = listaRespuestas;
	}

	@Override
	public String toString() {
		return toStringEditado();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((ide == null) ? 0 : ide.hashCode());
		result = prime * result + ((listaRespuestas == null) ? 0 : listaRespuestas.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Pregunta other = (Pregunta) obj;
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
		if (listaRespuestas == null) {
			if (other.listaRespuestas != null)
				return false;
		} else if (!listaRespuestas.equals(other.listaRespuestas))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	public String toStringEditado() {
		return ((fecha != null)?"" + fecha:"") + " " + texto;
	}
	
}
