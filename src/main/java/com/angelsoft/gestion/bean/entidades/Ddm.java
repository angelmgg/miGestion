package com.angelsoft.gestion.bean.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.angelsoft.gestion.bean.Campo;
import com.angelsoft.gestion.bean.Estructura;
import com.angelsoft.gestion.bean.SuperDescriptor;

@SuppressWarnings("serial")
public class Ddm implements Serializable, Cloneable {

	private Long ide;
	private String nombre = "";
	private String descripcion = "";
	private Long db;
	private Long fnr;
	private List<Object> listaDetalle= new ArrayList<Object>();

	public Long getIde() {
		return ide;
	}

	public void setIde(Long ide) {
		this.ide = ide;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getDb() {
		return db;
	}

	public void setDb(Long db) {
		this.db = db;
	}

	public Long getFnr() {
		return fnr;
	}

	public void setFnr(Long fnr) {
		this.fnr = fnr;
	}

	public boolean isPersisted() {
		return ide != null;
	}
	
	public List<Object> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<Object> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	public List<Campo> getListaCampos() {
		List<Campo> listaAux = new ArrayList<Campo>();
		for (Object objetoAux: listaDetalle){
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
	
	public Boolean getTieneFks() {
		Boolean swTiene = false;
		
		for (Campo campoAux: getListaCampos()){
			if (campoAux.getEsFk()) { 
				swTiene = true;
			}
		}
		
		return swTiene;
	}
	
	public List<SuperDescriptor> getListaSuperDescriptores() {
		List<SuperDescriptor> listaAux = new ArrayList<SuperDescriptor>();
		for (Object objetoAux: listaDetalle){
			if (objetoAux instanceof SuperDescriptor){
				listaAux.add((SuperDescriptor) objetoAux);
			}
		}
		return listaAux;
	}
	
	@Override
	public Ddm clone() throws CloneNotSupportedException {
		return (Ddm) super.clone();
	}

	@Override
	public String toString() {
		return nombre + " " + db + " " + fnr + " " + descripcion;
	}
	
	public String toStringEditado() {
		return nombre +" (DB:" + db+ ", FNR:" + fnr + ")"+ " - " + descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((db == null) ? 0 : db.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((fnr == null) ? 0 : fnr.hashCode());
		result = prime * result + ((ide == null) ? 0 : ide.hashCode());
		result = prime * result + ((listaDetalle == null) ? 0 : listaDetalle.hashCode());
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
		Ddm other = (Ddm) obj;
		if (db == null) {
			if (other.db != null)
				return false;
		} else if (!db.equals(other.db))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fnr == null) {
			if (other.fnr != null)
				return false;
		} else if (!fnr.equals(other.fnr))
			return false;
		if (ide == null) {
			if (other.ide != null)
				return false;
		} else if (!ide.equals(other.ide))
			return false;
		if (listaDetalle == null) {
			if (other.listaDetalle != null)
				return false;
		} else if (!listaDetalle.equals(other.listaDetalle))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}	

}
