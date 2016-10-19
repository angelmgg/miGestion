package com.angelsoft.gestion.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SuperDescriptorDetalle implements Serializable, Cloneable {

	private Campo campo = null;
	private String nombreCampo = "";
	private Integer posDesde = null;
	private Integer posHasta = null;

	public Campo getCampo() {
		return campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

	public String getNombreCampo() {
		if (this.getCampo() != null){
			this.setNombreCampo(this.getCampo().getNombre());
		}
		return nombreCampo;
	}
	public String getNombreCortoCampo() {
		String cadena = "";
		if (this.getCampo() != null){
			cadena = this.getCampo().getNombreCorto();
		}
		return cadena;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public Integer getPosDesde() {
		return posDesde;
	}

	public void setPosDesde(Integer posDesde) {
		this.posDesde = posDesde;
	}

	public Integer getPosHasta() {
		return posHasta;
	}

	public void setPosHasta(Integer posHasta) {
		this.posHasta = posHasta;
	}

	@Override
	public String toString() {
		return toStringEditado();
	}
	
	public String toStringEditado() {
		return "   --> " + ((campo == null)?nombreCampo : campo.getNombreCorto() + " "+ campo.getNombre() + " " + campo.getTipo() + ((campo.getLongitud() != null)?" " + campo.getLongitud().toString():"")) + ((posDesde != null)?" " + posDesde.toString():"")+ ((posHasta!= null)?" " + posHasta.toString():"");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((nombreCampo == null) ? 0 : nombreCampo.hashCode());
		result = prime * result + ((posDesde == null) ? 0 : posDesde.hashCode());
		result = prime * result + ((posHasta == null) ? 0 : posHasta.hashCode());
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
		SuperDescriptorDetalle other = (SuperDescriptorDetalle) obj;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (nombreCampo == null) {
			if (other.nombreCampo != null)
				return false;
		} else if (!nombreCampo.equals(other.nombreCampo))
			return false;
		if (posDesde == null) {
			if (other.posDesde != null)
				return false;
		} else if (!posDesde.equals(other.posDesde))
			return false;
		if (posHasta == null) {
			if (other.posHasta != null)
				return false;
		} else if (!posHasta.equals(other.posHasta))
			return false;
		return true;
	}

}
