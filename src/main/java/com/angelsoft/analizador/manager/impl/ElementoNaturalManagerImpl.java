package com.angelsoft.analizador.manager.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.bean.entidades.NombreArchivo;
import com.angelsoft.analizador.bean.entidades.natural.Aplicacion;
import com.angelsoft.analizador.bean.entidades.natural.Copycode;
import com.angelsoft.analizador.bean.entidades.natural.Ddm;
import com.angelsoft.analizador.bean.entidades.natural.Global;
import com.angelsoft.analizador.bean.entidades.natural.Helprutina;
import com.angelsoft.analizador.bean.entidades.natural.Local;
import com.angelsoft.analizador.bean.entidades.natural.Mapa;
import com.angelsoft.analizador.bean.entidades.natural.Parameter;
import com.angelsoft.analizador.bean.entidades.natural.ParametrosElementos;
import com.angelsoft.analizador.bean.entidades.natural.Programa;
import com.angelsoft.analizador.bean.entidades.natural.Subprograma;
import com.angelsoft.analizador.bean.entidades.natural.Subrutina;
import com.angelsoft.analizador.bean.entidades.natural.Texto;
import com.angelsoft.analizador.bean.entidades.natural.Uso;
import com.angelsoft.analizador.ctes.ConstantesNatural;
import com.angelsoft.analizador.ctes.parametros.ParametrosCopycode;
import com.angelsoft.analizador.ctes.parametros.ParametrosDdm;
import com.angelsoft.analizador.ctes.parametros.ParametrosGlobal;
import com.angelsoft.analizador.ctes.parametros.ParametrosHelprutina;
import com.angelsoft.analizador.ctes.parametros.ParametrosLocal;
import com.angelsoft.analizador.ctes.parametros.ParametrosMapa;
import com.angelsoft.analizador.ctes.parametros.ParametrosParameter;
import com.angelsoft.analizador.ctes.parametros.ParametrosPrograma;
import com.angelsoft.analizador.ctes.parametros.ParametrosSubprograma;
import com.angelsoft.analizador.ctes.parametros.ParametrosSubrutina;
import com.angelsoft.analizador.ctes.parametros.ParametrosTexto;
import com.angelsoft.analizador.filtro.FiltroExtensionFile;
import com.angelsoft.analizador.manager.ElementoNaturalManager;

public class ElementoNaturalManagerImpl implements ElementoNaturalManager{
	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}

	public SortedMap<NombreArchivo, ElementoNatural> sacaElementosAplicaciones(List<Aplicacion> listaAplicaciones){
//		logger.info("--> sacaElementosAplicaciones");
		SortedMap<NombreArchivo, ElementoNatural> mapaElementos = new TreeMap<NombreArchivo, ElementoNatural>();

		if (listaAplicaciones != null){
			for(Iterator<Aplicacion> iteradorList = (Iterator<Aplicacion>) listaAplicaciones.iterator(); iteradorList.hasNext(); ){
				Aplicacion aplicacionAux = (Aplicacion) iteradorList.next();
				logger.info("--> sacaElementosAplicaciones " + " for " + aplicacionAux.getNombre());
				mapaElementos.putAll(sacaElementosAplicacion(aplicacionAux));
			}
		}
//		logger.info("--> sacaElementosAplicaciones " + "END");
		return mapaElementos;
	}

	public SortedMap<NombreArchivo, ElementoNatural> sacaElementosAplicacion(Aplicacion aplicacionAux){
//		logger.info("--> sacaElementosAplicacion");
		SortedMap<NombreArchivo, ElementoNatural> mapaElementos = new TreeMap<NombreArchivo, ElementoNatural>();

		for (int i = 0; i < ConstantesNatural.ELEMENTOS_APLICACION.length;i++){
			ParametrosElementos parametrosAux = new ParametrosElementos(ConstantesNatural.ELEMENTOS_APLICACION[i]);
			mapaElementos.putAll(extraeElementosPorExtension(aplicacionAux, mapaElementos, ConstantesNatural.DIR_FUENTES, parametrosAux.getExtSRC()));
			mapaElementos.putAll(extraeElementosPorExtension(aplicacionAux, mapaElementos, ConstantesNatural.DIR_COMPILADOS, parametrosAux.getExtGP()));
		}

		return mapaElementos;
	}

	private SortedMap<NombreArchivo, ElementoNatural> extraeElementosPorExtension(Aplicacion aplicacionAux, SortedMap<NombreArchivo, ElementoNatural> mapaElementos, String nombreSubdirectorio, String extension){
//		logger.info("--> extraeElementosPorExtension");
		File directorio = new File(aplicacionAux.getPath()+ "\\"+ nombreSubdirectorio);
		File[] listaArchivos = directorio.listFiles(new FiltroExtensionFile(extension));

		if(listaArchivos!=null && listaArchivos.length>0){

			for(File archivo:listaArchivos){

				ElementoNatural elementoAux = trataElementoNatural(archivo, extension);
				if (elementoAux != null){
					elementoAux.setAplicacion(aplicacionAux.getNombre());

					if (mapaElementos == null){ mapaElementos = new TreeMap<NombreArchivo, ElementoNatural>();}

					NombreArchivo nombreAux = new NombreArchivo(elementoAux.getNombre().getNombre(), elementoAux.getParametros().getExtGP());
					NombreArchivo nombreAux2 = new NombreArchivo(elementoAux.getNombre().getNombre(), elementoAux.getParametros().getExtSRC());

					ElementoNatural eleAux = null;
					if (mapaElementos != null){
						if (mapaElementos.containsKey(nombreAux)){
							eleAux = mapaElementos.get(nombreAux);
						}else if (mapaElementos.containsKey(nombreAux2)){
							eleAux = mapaElementos.get(nombreAux2);
						}
					}
					if (eleAux != null){
						if (elementoAux.getPathCompilado()!= null) eleAux.setPathCompilado(elementoAux.getPathCompilado());
						if (elementoAux.getPathFuente()!= null) eleAux.setPathFuente(elementoAux.getPathFuente());
						mapaElementos.put(eleAux.getNombre(), eleAux);
					}else{
						mapaElementos.put(elementoAux.getNombre(), elementoAux);
					}
				}
			}
		}

		return mapaElementos;

	}

	private ElementoNatural trataElementoNatural(File fichero, String extension){
//		logger.info("--> trataElementoNatural");
		ElementoNatural elementoAux = null;

		if (extension.equalsIgnoreCase(ParametrosDdm.EXT_GP) || extension.equalsIgnoreCase(ParametrosDdm.EXT_SRC)){
		   	elementoAux = new Ddm(fichero);
		} else if (extension.equalsIgnoreCase(ParametrosGlobal.EXT_GP) || extension.equalsIgnoreCase(ParametrosGlobal.EXT_SRC)){
		   	elementoAux = new Global(fichero);
		} else if (extension.equalsIgnoreCase(ParametrosHelprutina.EXT_GP) || extension.equalsIgnoreCase(ParametrosHelprutina.EXT_SRC)){
		   	elementoAux = new Helprutina(fichero);
		} else if (extension.equalsIgnoreCase(ParametrosLocal.EXT_GP) || extension.equalsIgnoreCase(ParametrosLocal.EXT_SRC)){
		   	elementoAux = new Local(fichero);
		} else if (extension.equalsIgnoreCase(ParametrosMapa.EXT_GP) || extension.equalsIgnoreCase(ParametrosMapa.EXT_SRC)){
		   	elementoAux = new Mapa(fichero);
		} else if (extension.equalsIgnoreCase(ParametrosParameter.EXT_GP) || extension.equalsIgnoreCase(ParametrosParameter.EXT_SRC)){
		   	elementoAux = new Parameter(fichero);
		} else if (extension.equalsIgnoreCase(ParametrosPrograma.EXT_GP) || extension.equalsIgnoreCase(ParametrosPrograma.EXT_SRC)){
		   	elementoAux = new Programa(fichero);
		} else if (extension.equalsIgnoreCase(ParametrosSubprograma.EXT_GP) || extension.equalsIgnoreCase(ParametrosSubprograma.EXT_SRC)){
		   	elementoAux = new Subprograma(fichero);
		} else if (extension.equalsIgnoreCase(ParametrosSubrutina.EXT_GP) || extension.equalsIgnoreCase(ParametrosSubrutina.EXT_SRC)){
		   	elementoAux = new Subrutina(fichero);
		} else if (extension.equalsIgnoreCase(ParametrosCopycode.EXT_GP) || extension.equalsIgnoreCase(ParametrosCopycode.EXT_SRC)){
		   	elementoAux = new Copycode(fichero);
		} else if (extension.equalsIgnoreCase(ParametrosTexto.EXT_GP) || extension.equalsIgnoreCase(ParametrosTexto.EXT_SRC)){
		   	elementoAux = new Texto(fichero);
		}

		if (elementoAux!= null){
			String cadena = fichero.getName().replaceAll(elementoAux.getNombre().getNombre(), "");
			if (cadena.equalsIgnoreCase(elementoAux.getParametros().getExtGP())){
				elementoAux.setPathCompilado(fichero.getAbsolutePath());
			}if (cadena.equalsIgnoreCase(elementoAux.getParametros().getExtSRC())){
				elementoAux.setPathFuente(fichero.getAbsolutePath());
				if (elementoAux.getParametros().getNombre().equalsIgnoreCase(ParametrosDdm.NOMBRE) ||
					elementoAux.getParametros().getNombre().equalsIgnoreCase(ParametrosLocal.NOMBRE)||
					elementoAux.getParametros().getNombre().equalsIgnoreCase(ParametrosCopycode.NOMBRE) ||
					elementoAux.getParametros().getNombre().equalsIgnoreCase(ParametrosPrograma.NOMBRE)||
					elementoAux.getParametros().getNombre().equalsIgnoreCase(ParametrosSubprograma.NOMBRE)||
					elementoAux.getParametros().getNombre().equalsIgnoreCase(ParametrosSubrutina.NOMBRE)||
					elementoAux.getParametros().getNombre().equalsIgnoreCase(ParametrosMapa.NOMBRE)||
					elementoAux.getParametros().getNombre().equalsIgnoreCase(ParametrosHelprutina.NOMBRE)){

					analizaTexto(elementoAux);
				}
			}
		}

		return elementoAux;
	}

	public void analizaTexto(ElementoNatural elementoAux){
//		logger.info("--> analizaTexto");
		if (elementoAux.getPathFuente() != null){
			if (elementoAux.getListaUsos() == null){
				elementoAux.setListaUsos(new LinkedList<Uso>());
			}

			try {
				FileReader fr;
				fr = new FileReader(elementoAux.getPathFuente());
				BufferedReader entrada = new BufferedReader(fr);
				String lineaAux;
				int numLinea = 0;
				while((lineaAux = entrada.readLine()) != null){
					trataLineaElemento(elementoAux, numLinea++ ,lineaAux);
				}
				entrada.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void trataLineaElemento(ElementoNatural elementoAux, int numLinea, String linea){
//		logger.info("--> trataLineaElemento");

		linea = linea.substring(ConstantesNatural.SIZE_CONT_LINEA);

		if (!elementoAux.getParametros().seQuitanLineasAsteriscadas() || !lineaComentadaOVacia(linea)){
			if (elementoAux instanceof Ddm){
				if (numLinea == 0 ){
					((Ddm) elementoAux).setDb(Integer.parseInt(linea.substring(linea.indexOf(ParametrosDdm.CADENA_DB)+ParametrosDdm.CADENA_DB.length(), linea.indexOf(ParametrosDdm.CADENA_FNR)).replaceAll(" ","")));
					((Ddm) elementoAux).setFnr(Integer.parseInt(linea.substring(linea.indexOf(ParametrosDdm.CADENA_FNR) + ParametrosDdm.CADENA_FNR.length(), linea.indexOf(ParametrosDdm.SEPARACION_NOMBRE)).replaceAll(" ","")));
					((Ddm) elementoAux).setNombreDdm(linea.substring(linea.indexOf(ParametrosDdm.SEPARACION_NOMBRE) + ParametrosDdm.SEPARACION_NOMBRE.length(), ParametrosDdm.COL_HASTA_NOMBRE).replaceAll(" ","").trim());
				}
			} else if (elementoAux instanceof Local){
				buscaUsoDdmEnLocal(elementoAux, numLinea, linea);
				
			} else if (elementoAux instanceof Mapa){				
				buscaCadenas(elementoAux, numLinea, linea, ConstantesNatural.USO_HELPROUTINES, ",");
				
			} else if (elementoAux instanceof Programa ||
					   elementoAux instanceof Subprograma ||
					   elementoAux instanceof Subrutina ||
					   elementoAux instanceof Helprutina){

				buscaCadenas(elementoAux, numLinea, linea, ConstantesNatural.USO_VIEWS, null);
				buscaCadenas(elementoAux, numLinea, linea, ConstantesNatural.USO_LOCAL, null);
				buscaCadenas(elementoAux, numLinea, linea, ConstantesNatural.USO_PARAMETER, null);
				buscaCadenas(elementoAux, numLinea, linea, ConstantesNatural.LLAMADAS_A_SUBPROGRAMAS, null);
				buscaCadenas(elementoAux, numLinea, linea, ConstantesNatural.LLAMADAS_A_PROGRAMAS, null);
				buscaCadenas(elementoAux, numLinea, linea, ConstantesNatural.USO_INCLUDES, null);
				buscaCadenas(elementoAux, numLinea, linea, ConstantesNatural.USO_MAPAS, null);
				
				buscaUsoConsulta(elementoAux, numLinea, linea, ConstantesNatural.USO_CONSULTAS);
			}
		}
	}

	public void buscaUsoDdmEnLocal(ElementoNatural elementoAux, int numLinea, String linea){
//		logger.info("--> buscaUsoDdmEnLocal");
		if (linea.length() > ParametrosLocal.POS_NOMBRE_DDM && linea.toUpperCase().substring(ParametrosLocal.POS_IDENTIFICADOR_VISTA_DESDE, ParametrosLocal.POS_IDENTIFICADOR_VISTA_HASTA).equalsIgnoreCase("V")){
			Uso uso = new Uso();
			uso.setTipoLlamador(elementoAux.getParametros().getNombre());
			uso.setNombreLlamador(elementoAux.getNombre().getNombre());
			uso.setComoLoLlama(ConstantesNatural.USO_VIEWS[0]);
			uso.setNombreLlamado(linea.toUpperCase().substring(ParametrosLocal.POS_NOMBRE_DDM));
			uso.setLinea(numLinea + 1);

			elementoAux.getListaUsos().add(uso);
		}
	}
	
	public void buscaUsoConsulta(ElementoNatural elementoAux, int numLinea, String linea, String [] cadenasABuscar){
//		logger.info("--> buscaUsoConsulta");
		boolean encontrado = Boolean.FALSE;

		if (!linea.trim().equalsIgnoreCase("")){
			for (int j = 0; j < cadenasABuscar.length && !encontrado; j++){
				 String cadenaBuscada = cadenasABuscar[j];
				 int pos = linea.toUpperCase().indexOf(cadenaBuscada);
				 if (pos >= 0){
					 if (linea.toUpperCase().indexOf("MOVE") >= 0){
						 String [ ] palabras = linea.split(" ");
		
						 for (int i = 0; i < palabras.length && !encontrado; i++){
							 String palabraAux = palabras[i];
							 if (palabraAux.length() > 1 && palabraAux.substring(0, 1).equalsIgnoreCase("'") && palabraAux.substring(palabraAux.length()-1, palabraAux.length()).equalsIgnoreCase("'")){
								Uso uso = new Uso();
								uso.setTipoLlamador(elementoAux.getParametros().getNombre());
							 	uso.setNombreLlamador(elementoAux.getNombre().getNombre());
							 	uso.setComoLoLlama(cadenaBuscada);
							 	uso.setNombreLlamado(palabraAux.replaceAll("'", ""));
							 	uso.setLinea(numLinea + 1);
							 	encontrado = Boolean.TRUE;
							 
							 	elementoAux.getListaUsos().add(uso);
							 }
						 }
					 }
				 }
			}
		}
	}

	public boolean buscaCadenas(ElementoNatural elementoAux, int numLinea, String linea, String [] cadenasABuscar, String caracterDelimitador){
//		logger.info("--> buscaCadenas");
		boolean encontrado = Boolean.FALSE;

		if (!linea.trim().equalsIgnoreCase("")){
			for (int i = 0; i < cadenasABuscar.length && !encontrado; i++){
				 String cadenaBuscada = cadenasABuscar[i];
				 int pos = linea.toUpperCase().indexOf(cadenaBuscada);
				 if (pos >= 0){
					 String [ ] palabras = linea.substring(pos + cadenaBuscada.length()).split("\\ ");

					 Uso uso = new Uso();
					 uso.setTipoLlamador(elementoAux.getParametros().getNombre());
					 uso.setNombreLlamador(elementoAux.getNombre().getNombre());
					 uso.setComoLoLlama(cadenaBuscada);
					 
					 if (caracterDelimitador == null){
					   uso.setNombreLlamado(palabras[0].replaceAll("'", ""));
					 } else {
					   uso.setNombreLlamado(palabras[0].split(caracterDelimitador)[0].replaceAll("'", ""));	 
					 }
					 uso.setLinea(numLinea + 1);

					 encontrado = Boolean.TRUE;

					 elementoAux.getListaUsos().add(uso);
				 }
			}
		}
		return encontrado;
	}

	public boolean lineaComentadaOVacia(String linea){
//		logger.info("--> lineaComentadaOVacia");

		boolean resultado = Boolean.FALSE;

		if (linea.trim().equalsIgnoreCase("")){
			resultado = Boolean.TRUE;
		} else {
			int tamTotal = linea.toUpperCase().length();

			for (int i = 0; i < ConstantesNatural.CASOS_COMENTARIOS_INICIO_LINEA.length && !resultado; i++){
				int tamAux = ConstantesNatural.CASOS_COMENTARIOS_INICIO_LINEA[i].length();
				 if (tamTotal >= tamAux && linea.substring(0,tamAux).equalsIgnoreCase(ConstantesNatural.CASOS_COMENTARIOS_INICIO_LINEA[i])){
					 resultado = Boolean.TRUE;
				 }
			}
		}
		return resultado;
	}

//	public CopycodeBD transformaAVersionBD(Copycode elemento){
//		CopycodeBD elementoAux = new CopycodeBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		return elementoAux;
//	}

//	public DdmBD transformaAVersionBD(Ddm elemento){
//		DdmBD elementoAux = new DdmBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		elementoAux.setFnr(elemento.getFnr());
//		elementoAux.setDb(elemento.getDb());
//		elementoAux.setNombreDdm(elemento.getNombreDdm());
//		return elementoAux;
//	}

//	public GlobalBD transformaAVersionBD(Global elemento){
//		GlobalBD elementoAux = new GlobalBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		return elementoAux;
//	}

//	public HelprutinaBD transformaAVersionBD(Helprutina elemento){
//		HelprutinaBD elementoAux = new HelprutinaBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		return elementoAux;
//	}

//	public LocalBD transformaAVersionBD(Local elemento){
//		LocalBD elementoAux = new LocalBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		return elementoAux;
//	}

//	public MapaBD transformaAVersionBD(Mapa elemento){
//		MapaBD elementoAux = new MapaBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		return elementoAux;
//	}

//	public ParameterBD transformaAVersionBD(Parameter elemento){
//		ParameterBD elementoAux = new ParameterBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		return elementoAux;
//	}

//	public ProgramaBD transformaAVersionBD(Programa elemento){
//		ProgramaBD elementoAux = new ProgramaBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		return elementoAux;
//	}

//	public SubprogramaBD transformaAVersionBD(Subprograma elemento){
//		SubprogramaBD elementoAux = new SubprogramaBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		return elementoAux;
//	}

//	public SubrutinaBD transformaAVersionBD(Subrutina elemento){
//		SubrutinaBD elementoAux = new SubrutinaBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		return elementoAux;
//	}

//	public TextoBD transformaAVersionBD(Texto elemento){
//		TextoBD elementoAux = new TextoBD();
//		elementoAux.setAplicacion(elemento.getAplicacion());
//		elementoAux.setNombre(elemento.getNombre().getNombre());
//		elementoAux.setPathFuente(elemento.getPathFuente());
//		elementoAux.setPathCompilado(elemento.getPathCompilado());
//		return elementoAux;
//	}

//	public ElementoNatural transformaBDAVersionElementoNatural(Object elementoBD){
//		ElementoNatural elementoAux = null;
//
//		if (elementoBD instanceof CopycodeBD){
//			elementoAux = new Copycode();
//			elementoAux.setAplicacion(((CopycodeBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((CopycodeBD) elementoBD).getNombre(), ""));
//			elementoAux.setPathFuente((((CopycodeBD) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((CopycodeBD) elementoBD).getPathCompilado()));
//		} else if (elementoBD instanceof DdmBD){
//			elementoAux = new Ddm();
//			elementoAux.setAplicacion(((DdmBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((DdmBD) elementoBD).getNombreDdm(), ""));
//			elementoAux.setPathFuente((((DdmBD) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((DdmBD) elementoBD).getPathCompilado()));
//		} else if (elementoBD instanceof GlobalBD){
//			elementoAux = new Global();
//			elementoAux.setAplicacion(((GlobalBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((GlobalBD) elementoBD).getNombre(), ""));
//			elementoAux.setPathFuente((((GlobalBD) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((GlobalBD) elementoBD).getPathCompilado()));
//		} else if (elementoBD instanceof HelprutinaBD){
//			elementoAux = new Helprutina();
//			elementoAux.setAplicacion(((HelprutinaBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((HelprutinaBD) elementoBD).getNombre(), ""));
//			elementoAux.setPathFuente((((HelprutinaBD) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((HelprutinaBD) elementoBD).getPathCompilado()));
//		} else if (elementoBD instanceof LocalBD){
//			elementoAux = new Local();
//			elementoAux.setAplicacion(((LocalBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((LocalBD) elementoBD).getNombre(), ""));
//			elementoAux.setPathFuente((((LocalBD) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((LocalBD) elementoBD).getPathCompilado()));
//		} else if (elementoBD instanceof MapaBD){
//			elementoAux = new Mapa();
//			elementoAux.setAplicacion(((MapaBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((MapaBD) elementoBD).getNombre(), ""));
//			elementoAux.setPathFuente((((MapaBD) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((MapaBD) elementoBD).getPathCompilado()));
//		} else if (elementoBD instanceof ParameterBD){
//			elementoAux = new Parameter();
//			elementoAux.setAplicacion(((ParameterBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((ParameterBD) elementoBD).getNombre(), ""));
//			elementoAux.setPathFuente((((ParameterBD) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((ParameterBD) elementoBD).getPathCompilado()));
//		} else if (elementoBD instanceof ProgramaBD){
//			elementoAux = new Programa();
//			elementoAux.setAplicacion(((ProgramaBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((ProgramaBD) elementoBD).getNombre(), ""));
//			elementoAux.setPathFuente((((ProgramaBD) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((ProgramaBD) elementoBD).getPathCompilado()));
//		} else if (elementoBD instanceof Subprograma){
//			elementoAux = new Subprograma();
//			elementoAux.setAplicacion(((SubprogramaBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((SubprogramaBD) elementoBD).getNombre(), ""));
//			elementoAux.setPathFuente((((Subprograma) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((Subprograma) elementoBD).getPathCompilado()));
//		} else if (elementoBD instanceof SubrutinaBD){
//			elementoAux = new Subrutina();
//			elementoAux.setAplicacion(((SubrutinaBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((SubrutinaBD) elementoBD).getNombre(), ""));
//			elementoAux.setPathFuente((((SubrutinaBD) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((SubrutinaBD) elementoBD).getPathCompilado()));
//		} else if (elementoBD instanceof TextoBD){
//			elementoAux = new Texto();
//			elementoAux.setAplicacion(((TextoBD) elementoBD).getAplicacion());
//			elementoAux.setNombre(new NombreArchivo(((TextoBD) elementoBD).getNombre(), ""));
//			elementoAux.setPathFuente((((TextoBD) elementoBD).getPathFuente()));
//			elementoAux.setPathCompilado((((TextoBD) elementoBD).getPathCompilado()));
//		}
//
//		return elementoAux;
//	}

}


