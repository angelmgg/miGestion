package com.angelsoft.analizador.bean.entidades;

public class NombreArchivo implements Comparable<NombreArchivo> {

	private String nombre;
	private String extension;
	
	
	public NombreArchivo(String nombre, String extension) {
		super();
		this.nombre = nombre;
		this.extension = extension;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}
	
	public String toString(){
		return this.nombre + this.extension;
	}
   /**
   *
   */
   public boolean equals( Object a ) {
     if ( this == a ) return true;
     if ( !(a instanceof NombreArchivo) ) return false;

     NombreArchivo aux = (NombreArchivo)a;
     return
       ( this.getNombre() == aux.getNombre() ) &&
       ( this.getExtension() == aux.getExtension());
   }
   
   public int compareTo( NombreArchivo nombreAux){
		
		int aux = 0;
		
		aux = this.getNombre().compareTo(nombreAux.getNombre());
		
		if (aux == 0) {
			aux = (-1) * this.getExtension().compareTo(nombreAux.getExtension());
		}
		
		return aux;
   }
}
