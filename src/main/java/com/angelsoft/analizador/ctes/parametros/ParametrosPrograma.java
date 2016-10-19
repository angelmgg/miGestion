package com.angelsoft.analizador.ctes.parametros;

import java.util.ResourceBundle;




public class ParametrosPrograma {

	private static ResourceBundle parametros = ResourceBundle.getBundle("parametrosNatural");

	public static String NOMBRE = parametros.getString("PROGRAMA_NOMBRE");
	public static String EXT_GP = parametros.getString(NOMBRE + "_GP_EXT");
	public static String EXT_SRC = parametros.getString(NOMBRE + "_SRC_EXT");
	public static boolean LEER_SOLO_PRIMERALINEA = Boolean.FALSE;
	public static boolean QUITAR_ASTERISCADAS = Boolean.TRUE;

}
