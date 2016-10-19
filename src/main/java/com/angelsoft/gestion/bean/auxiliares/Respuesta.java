package com.angelsoft.gestion.bean.auxiliares;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Respuesta implements Serializable, Cloneable {
	
	private Long ide;
	private Date fecha = null;
	private String texto = "";
	private List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
	private List<Nota> listaNotas = new ArrayList<Nota>();

	@Override
	public Respuesta clone() throws CloneNotSupportedException {
		return (Respuesta) super.clone();
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<Pregunta> getListaPreguntas() {
		return listaPreguntas;
	}

	public void setListaPreguntas(List<Pregunta> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}

	public List<Nota> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(List<Nota> listaNotas) {
		this.listaNotas = listaNotas;
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
		result = prime * result + ((listaNotas == null) ? 0 : listaNotas.hashCode());
		result = prime * result + ((listaPreguntas == null) ? 0 : listaPreguntas.hashCode());
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
		Respuesta other = (Respuesta) obj;
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
		if (listaNotas == null) {
			if (other.listaNotas != null)
				return false;
		} else if (!listaNotas.equals(other.listaNotas))
			return false;
		if (listaPreguntas == null) {
			if (other.listaPreguntas != null)
				return false;
		} else if (!listaPreguntas.equals(other.listaPreguntas))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

	public String toStringEditado() {
		return ((fecha != null)?"" + fecha:"") + " " + texto;
	}
}
