package com.angelsoft.analizador.bean.entidades.natural;

import java.io.Serializable;

public class Uso implements Serializable, Cloneable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ide;
	private String aplicacionLlamador;
	private String tipoLlamador;
	private String nombreLlamador;
	private String comoLoLlama;
	private String nombreLlamado;
	private Integer linea;
	
	public Long getIde() {
		return ide;
	}
	public void setIde(Long ide) {
		this.ide = ide;
	}
	public String getAplicacionLlamador() {
		return aplicacionLlamador;
	}
	public void setAplicacionLlamador(String aplicacionLlamador) {
		this.aplicacionLlamador = aplicacionLlamador;
	}
	public String getTipoLlamador() {
		return tipoLlamador;
	}
	public void setTipoLlamador(String tipoLlamador) {
		this.tipoLlamador = tipoLlamador;
	}
	public String getNombreLlamador() {
		return nombreLlamador;
	}
	public void setNombreLlamador(String nombreLlamador) {
		this.nombreLlamador = nombreLlamador;
	}
	public String getComoLoLlama() {
		return comoLoLlama;
	}
	public void setComoLoLlama(String comoLoLlama) {
		this.comoLoLlama = comoLoLlama;
	}
	public String getNombreLlamado() {
		return nombreLlamado;
	}
	public void setNombreLlamado(String nombreLlamado) {
		this.nombreLlamado = nombreLlamado;
	}
	public Integer getLinea() {
		return linea;
	}
	public void setLinea(Integer linea) {
		this.linea = linea;
	}

}
