package com.angelsoft.analizador.manager;

import java.util.List;
import java.util.SortedMap;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.bean.entidades.NombreArchivo;
import com.angelsoft.analizador.bean.entidades.natural.Aplicacion;

public interface ElementoNaturalManager {

	public SortedMap<NombreArchivo, ElementoNatural> sacaElementosAplicaciones(List<Aplicacion> listaAplicaciones);
	public SortedMap<NombreArchivo, ElementoNatural> sacaElementosAplicacion(Aplicacion aplicacionAux);
	public void analizaTexto(ElementoNatural elementoAux);
	public void trataLineaElemento(ElementoNatural elementoAux, int numLinea, String linea);
	public boolean lineaComentadaOVacia(String linea);
	
//	public CopycodeBD transformaAVersionBD(Copycode elemento);
//	public DdmBD transformaAVersionBD(Ddm elemento);
//	public GlobalBD transformaAVersionBD(Global elemento);
//	public HelprutinaBD transformaAVersionBD(Helprutina elemento);
//	public LocalBD transformaAVersionBD(Local elemento);
//	public MapaBD transformaAVersionBD(Mapa elemento);
//	public ParameterBD transformaAVersionBD(Parameter elemento);
//	public ProgramaBD transformaAVersionBD(Programa elemento);
//	public SubprogramaBD transformaAVersionBD(Subprograma elemento);
//	public SubrutinaBD transformaAVersionBD(Subrutina elemento);
//	public TextoBD transformaAVersionBD(Texto elemento);
//	public ElementoNatural transformaBDAVersionElementoNatural(Object elementoBD);

}


