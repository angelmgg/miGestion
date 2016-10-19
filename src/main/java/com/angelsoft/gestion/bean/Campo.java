package com.angelsoft.gestion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.angelsoft.gestion.ctes.CtesNatural;

@SuppressWarnings("serial")
public class Campo implements Serializable, Cloneable{ 

	private Integer nivel = 0;
	private String nombreCorto = "";
	private String nombre = "";
	private String tipo = "";
	private Integer longitud = null;
	private Boolean esDescriptor = Boolean.FALSE;
	private Boolean esSuperDescriptor = Boolean.FALSE;
	private Boolean esUnico = Boolean.FALSE;
	private Boolean esObligatorio = Boolean.FALSE;
	private String descripcion = "";
	private ForeingKey fk = null;
	private Boolean esRedefinicion = Boolean.FALSE;
	private List<Object> redefine = new ArrayList<Object>();
	
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getLongitud() {
		return longitud;
	}
	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}
	public Boolean getEsDescriptor() {
		return esDescriptor;
	}
	public void setEsDescriptor(Boolean esDescriptor) {
		this.esDescriptor = esDescriptor;
	}
	public Boolean getEsUnico() {
		return esUnico;
	}
	public void setEsUnico(Boolean esUnico) {
		this.esUnico = esUnico;
	}
	public Boolean getEsObligatorio() {
		return esObligatorio;
	}
	public void setEsObligatorio(Boolean esObligatorio) {
		this.esObligatorio = esObligatorio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getEsSuperDescriptor() {
		return esSuperDescriptor;
	}
	public void setEsSuperDescriptor(Boolean esSuperDescriptor) {
		this.esSuperDescriptor = esSuperDescriptor;
	}
	public ForeingKey getFk() {
		return fk;
	}
	public void setFk(ForeingKey fk) {
		this.fk = fk;
	}
	public Boolean getEsRedefinicion() {
		return esRedefinicion;
	}
	public void setEsRedefinicion(Boolean esRedefinicion) {
		this.esRedefinicion = esRedefinicion;
	}
	public List<Object> getRedefine() {
		return redefine;
	}
	public void setRedefine(List<Object> redefine) {
		this.redefine = redefine;
	}

	public Boolean getEsFk() {
		return (this.fk != null);
	}
	public String getFkTabla() {
		return (getEsFk())? this.fk.getTabla() :"";
	}
	public String getFkCampo() {
		return (getEsFk())? this.fk.getCampo() :"";
	}
	
	public List<Campo> getListaCampos() {
		List<Campo> listaAux = new ArrayList<Campo>();
		for (Object objetoAux: redefine){
			if (objetoAux instanceof Campo){
				listaAux.add((Campo) objetoAux);
				if (!((Campo) objetoAux).getRedefine().isEmpty()){
					listaAux.addAll(((Campo) objetoAux).getListaCampos());
				}
			} else if (objetoAux instanceof Estructura){
				listaAux.addAll(((Estructura) objetoAux).getListaCampos());
			}
		}
		return listaAux;
	}
	
	@Override
	public Campo clone() throws CloneNotSupportedException {
		return (Campo) super.clone();
	}

	@Override
	public String toString() {
		return toStringEditado();
	}
	
	public String toStringEditado() {
		return ((nivel != null)?" " + nivel.toString():"") + " " + ((esRedefinicion)?"--":(!"".equalsIgnoreCase(nombreCorto)?nombreCorto:"")) + " " + nombre + " " + tipo + ((longitud != null)?" " + longitud.toString():"") + ((esDescriptor)?" D" :"") + ((esSuperDescriptor)?" SD" :"") + ((esUnico)?" U" :"")+ ((esObligatorio)?" O" :"") + ((fk!= null)?" " +fk:"");
	}
	
	public String getLongitudDB(){
		String cadena = "";
		if (CtesNatural.TIPO_DATE.equalsIgnoreCase(this.getTipo())){
			cadena = "4";
		} else if(CtesNatural.TIPO_TIME.equalsIgnoreCase(this.getTipo())){
			cadena = "7";
		} else {
			cadena = getLongitud().toString();
		}
		
		return cadena;
	}
	
	public String getTipoDB(){
		String cadena = "";
		if (CtesNatural.TIPO_DATE.equalsIgnoreCase(this.getTipo())){
			cadena = "P";
		} else if (CtesNatural.TIPO_TIME.equalsIgnoreCase(this.getTipo())){
			cadena = "P";
		} else if (CtesNatural.TIPO_NUMERICO.equalsIgnoreCase(this.getTipo())){
			cadena = "U";
		} else {
			cadena = getTipo();
		}
		
		return cadena;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((esDescriptor == null) ? 0 : esDescriptor.hashCode());
		result = prime * result + ((esObligatorio == null) ? 0 : esObligatorio.hashCode());
		result = prime * result + ((esRedefinicion == null) ? 0 : esRedefinicion.hashCode());
		result = prime * result + ((esSuperDescriptor == null) ? 0 : esSuperDescriptor.hashCode());
		result = prime * result + ((esUnico == null) ? 0 : esUnico.hashCode());
		result = prime * result + ((fk == null) ? 0 : fk.hashCode());
		result = prime * result + ((longitud == null) ? 0 : longitud.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombreCorto == null) ? 0 : nombreCorto.hashCode());
		result = prime * result + ((redefine == null) ? 0 : redefine.hashCode());
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
		Campo other = (Campo) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (esDescriptor == null) {
			if (other.esDescriptor != null)
				return false;
		} else if (!esDescriptor.equals(other.esDescriptor))
			return false;
		if (esObligatorio == null) {
			if (other.esObligatorio != null)
				return false;
		} else if (!esObligatorio.equals(other.esObligatorio))
			return false;
		if (esRedefinicion == null) {
			if (other.esRedefinicion != null)
				return false;
		} else if (!esRedefinicion.equals(other.esRedefinicion))
			return false;
		if (esSuperDescriptor == null) {
			if (other.esSuperDescriptor != null)
				return false;
		} else if (!esSuperDescriptor.equals(other.esSuperDescriptor))
			return false;
		if (esUnico == null) {
			if (other.esUnico != null)
				return false;
		} else if (!esUnico.equals(other.esUnico))
			return false;
		if (fk == null) {
			if (other.fk != null)
				return false;
		} else if (!fk.equals(other.fk))
			return false;
		if (longitud == null) {
			if (other.longitud != null)
				return false;
		} else if (!longitud.equals(other.longitud))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nombreCorto == null) {
			if (other.nombreCorto != null)
				return false;
		} else if (!nombreCorto.equals(other.nombreCorto))
			return false;
		if (redefine == null) {
			if (other.redefine != null)
				return false;
		} else if (!redefine.equals(other.redefine))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
}
