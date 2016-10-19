package com.angelsoft.analizador.utiles;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.angelsoft.analizador.bean.entidades.natural.Aplicacion;
import com.angelsoft.analizador.ctes.ConstantesNatural;

public class UtilesPath {

	public static List<Aplicacion> extraeAplicacionesDirectorio(List<Aplicacion> listaAplicaciones, File path){
		if (listaAplicaciones == null){
			listaAplicaciones = new LinkedList<Aplicacion>();
		}

		if (path.isDirectory() && esDirectorioAplicacion(path)){
			Aplicacion aplicacionAux = new Aplicacion();
			aplicacionAux.setNombre(path.getName());
			aplicacionAux.setPath(path.getAbsolutePath());
			listaAplicaciones.add(aplicacionAux);

		} else if (path.isDirectory()){

			File[] elementosPath = path.listFiles();

			if(elementosPath!=null && elementosPath.length>0){

				for(File elementosHijos:elementosPath){
					if (elementosHijos.isDirectory() && !elementosHijos.getName().equalsIgnoreCase(ConstantesNatural.DIR_COMPILADOS) && !elementosHijos.getName().equalsIgnoreCase(ConstantesNatural.DIR_FUENTES)){
						extraeAplicacionesDirectorio(listaAplicaciones, elementosHijos);
					}
				}
			}
		}
		return listaAplicaciones;
	}

	public static boolean esDirectorioAplicacion(File path){
		boolean resultado = Boolean.FALSE;

		File[] elementosPath = path.listFiles();

		if(elementosPath!=null && elementosPath.length>0){
			boolean tieneDirectorioCompilados = Boolean.FALSE;
			boolean tieneDirectorioFuentes = Boolean.FALSE;
			boolean tieneFiledir = Boolean.FALSE;

			for(File elementoNivel:elementosPath){
				if (elementoNivel.isDirectory()){
					if(elementoNivel.getName().equalsIgnoreCase(ConstantesNatural.DIR_COMPILADOS)){
						tieneDirectorioCompilados = Boolean.TRUE;
					} else if (elementoNivel.getName().equalsIgnoreCase(ConstantesNatural.DIR_FUENTES)){
						tieneDirectorioFuentes = Boolean.TRUE;
					}
				} else {
					if (elementoNivel.getName().equalsIgnoreCase("FILEDIR.SAG")){
						tieneFiledir = Boolean.TRUE;
					}
				}
			}
			resultado = (tieneDirectorioCompilados && tieneDirectorioFuentes && tieneFiledir);
		}
		return resultado;
	}

}
