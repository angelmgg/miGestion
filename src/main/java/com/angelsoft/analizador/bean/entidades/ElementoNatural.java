package com.angelsoft.analizador.bean.entidades;

import java.io.File;
import java.util.List;

import com.angelsoft.analizador.bean.entidades.natural.ParametrosElementos;
import com.angelsoft.analizador.bean.entidades.natural.Uso;

public abstract class ElementoNatural implements Comparable<ElementoNatural>{

	private NombreArchivo nombre;
	private String aplicacion;
	private String pathFuente;
	private String pathCompilado;

	protected ParametrosElementos parametros;

	protected List<Uso> listaUsos;
	protected List<Anotacion> listaAnotaciones;

	public ElementoNatural (){
	}

	public ElementoNatural (File elemento){
		int posPunto = elemento.getName().lastIndexOf(".");
		this.nombre = new NombreArchivo(elemento.getName().substring(0, posPunto) ,elemento.getName().substring(posPunto));
	}

	/**
	 * @return the nombre
	 */
	public NombreArchivo getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(NombreArchivo nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the aplicacion
	 */
	public String getAplicacion() {
		return aplicacion;
	}


	/**
	 * @param aplicacion the aplicacion to set
	 */
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}


	/**
	 * @return the pathFuente
	 */
	public String getPathFuente() {
		return pathFuente;
	}


	/**
	 * @param pathFuente the pathFuente to set
	 */
	public void setPathFuente(String pathFuente) {
		this.pathFuente = pathFuente;
	}


	/**
	 * @return the pathCompilado
	 */
	public String getPathCompilado() {
		return pathCompilado;
	}


	/**
	 * @param pathCompilado the pathCompilado to set
	 */
	public void setPathCompilado(String pathCompilado) {
		this.pathCompilado = pathCompilado;
	}


	/**
	 * @return the parametros
	 */
	public ParametrosElementos getParametros() {
		return parametros;
	}


	/**
	 * @return the listaAnotaciones
	 */
	public List<Anotacion> getListaAnotaciones() {
		return listaAnotaciones;
	}


	/**
	 * @param listaAnotaciones the listaAnotaciones to set
	 */
	public void setListaAnotaciones(List<Anotacion> listaAnotaciones) {
		this.listaAnotaciones = listaAnotaciones;
	}

   /**
   *
   */
   public boolean equals( Object a ) {
     if ( this == a ) return true;
     if ( !(a instanceof ElementoNatural) ) return false;

     ElementoNatural elementoAux = (ElementoNatural)a;
     return
       ( this.getNombre() == elementoAux.getNombre() ) &&
       ( this.getAplicacion() == elementoAux.getAplicacion() ) &&
       ( this.getParametros() == elementoAux.getParametros() ) &&
       ( this.getPathCompilado() == elementoAux.getPathCompilado() ) &&
       ( this.getPathFuente().equals(elementoAux.getPathFuente()) );
   }

   public int compareTo( ElementoNatural elementoAux){

		int aux = 0;

		aux = this.getParametros().getNombre().compareTo(elementoAux.getParametros().getNombre());

		if (aux == 0) {
			aux = (-1) * this.getAplicacion().compareTo(elementoAux.getAplicacion());
		}

		if (aux == 0) {
			aux = (-1) * this.getParametros().compareTo(elementoAux.getParametros());
		}

		if (aux == 0) {
			aux = (-1) * this.getPathCompilado().compareTo(elementoAux.getPathCompilado());
		}

		if (aux == 0) {
			aux = (-1) * this.getPathFuente().compareTo(elementoAux.getPathFuente());
		}

		return aux;
   }

	/**
	 * @return the listaUsos
	 */
	public List<Uso> getListaUsos() {
		return listaUsos;
	}
	
	/**
	 * @param listaUsos the listaUsos to set
	 */
	public void setListaUsos(List<Uso> listaUsos) {
		this.listaUsos = listaUsos;
	}
	
	public String getFuenteCompilado(){
		String resultado = "";
	    if (this.pathFuente != null && !((String) this.pathFuente).equalsIgnoreCase("")) {
	    	if (this.pathCompilado != null && !((String) this.pathCompilado).equalsIgnoreCase("")) {
	    		resultado = "A";
	    	} else {
	    		resultado = "F";
	    	}
	    } else {	
	    	if (this.pathCompilado != null && !((String) this.pathCompilado).equalsIgnoreCase("")) {
	    		resultado = "C";
	    	}
	    }	
	    return resultado;
	}
	
	public String getTipo(){ 
		return (this.getParametros()!= null && !this.getParametros().getNombre().equalsIgnoreCase(""))?this.getParametros().getNombre():"";
	}
}
