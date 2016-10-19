package com.angelsoft.utiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.ctes.Constantes;

public class Utiles {

	public static Random random = new Random();
	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}

	public static void preparaDirectorio(String pathDirectorio){
		final String[] cadenas = pathDirectorio.split("\\\\");
		if (cadenas.length > 1){
			String pathAux = cadenas[0];
			for(int j = 1 ; j < cadenas.length ; j++){
				pathAux += "\\" + cadenas[j];

				final File folderProyecto = new File(pathAux);
				if (!folderProyecto.exists()) {
					folderProyecto.mkdir();
				}
			}
		}
	}

	public static List<String> copiaFicherosATemp(String dirTrabajo, String directorioActual, String urlProperties){
		final Properties rutas = new Properties();

		final List<String> pathScripts = new LinkedList<String>();

		try {

			rutas.load(new FileInputStream(directorioActual + urlProperties));

			for (final Enumeration<Object> e = rutas.keys(); e.hasMoreElements() ; ) {
				final Object obj = e.nextElement();
				final String pathDirectorio = directorioActual + rutas.getProperty(obj.toString());
				final File dir = new File(pathDirectorio);
				final String[] ficheros = dir.list();

				for (int x=0; x<ficheros.length; x++){
					if (!ficheros[x].substring(0, 2).equalsIgnoreCase("~$")){
						final int punto = ficheros[x].indexOf(".") + 1;
						final String extensionAux = ficheros[x].substring(punto);

						if (Constantes.LISTA_EXTENSIONES_FIC_COPIAR.contains(extensionAux)){
							Utiles.fileCopy(pathDirectorio + "\\" + ficheros[x], dirTrabajo + "\\" + ficheros[x]);
							pathScripts.add(dirTrabajo + "\\" + ficheros[x]);
						}
					}
				}

			}

		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "copiaFicherosATemp " + ex.toString());
		}

		return pathScripts;
	}

	public static void fileCopy(String sourceFile, String destinationFile) {
		try {
			final File inFile = new File(sourceFile);
			final File outFile = new File(destinationFile);

			final FileInputStream in = new FileInputStream(inFile);
			final FileOutputStream out = new FileOutputStream(outFile);

			int c;
			while( (c = in.read() ) != -1) {
				out.write(c);
			}

			in.close();
			out.close();

		} catch(final IOException ex) {
			logger.log(Level.SEVERE, "fileCopy " + ex.toString());
		}
	}

	public static String elementoRandom(List<String> listaCandidatos){
		Collections.shuffle(listaCandidatos);
		final int index = random.nextInt(listaCandidatos.size());
		return listaCandidatos.get(index);
	}

	public static Integer elementoRandomInteger(List<Integer> listaCandidatos){
		Collections.shuffle(listaCandidatos);
		final int index = random.nextInt(listaCandidatos.size());
		return listaCandidatos.get(index);
	}

	public static Calendar fechaRandom(){
		final Calendar fecha = Calendar.getInstance();

		final int diasRes = random.nextInt(200);
		fecha.roll(Calendar.DATE, diasRes);

		final int diasSum = random.nextInt(200);
		fecha.add(Calendar.DATE, diasSum);

		return fecha;
	}

	/**
	 *
	 * @param lista
	 * @return
	 */
	public static String listaStringAStringConComas(final List<String> lista) {
		final StringBuilder cadenaSB = new StringBuilder();

		for (final String cadena : lista) {
			cadenaSB.append((!("").equalsIgnoreCase(cadenaSB.toString()) ? ", " : "") + cadena);
		}

		return cadenaSB.toString();
	}

	/**
	 *
	 */
	public static void visualizaListaString(final List<String> lista) {
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println(" CONTENIDO --> " + (!lista.isEmpty()? lista.size() + " lineas": "vacio"));
		for (final String cadena : lista) {
			System.out.println(cadena);
		}
		System.out.println("--------------------------------------------------------------------------------------------");
	}
}
