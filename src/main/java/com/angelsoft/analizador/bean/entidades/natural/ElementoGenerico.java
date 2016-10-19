package com.angelsoft.analizador.bean.entidades.natural;

public class ElementoGenerico {

	private String libreria;
	private String nombre;
	
	public ElementoGenerico(String libreria, String nombre) {
		super();
		this.libreria = libreria;
		this.nombre = nombre;
	}
	
	public String getLibreria() {
		return libreria;
	}
	public void setLibreria(String libreria) {
		this.libreria = libreria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((libreria == null) ? 0 : libreria.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		ElementoGenerico other = (ElementoGenerico) obj;
		if (libreria == null) {
			if (other.libreria != null)
				return false;
		} else if (!libreria.equals(other.libreria))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
