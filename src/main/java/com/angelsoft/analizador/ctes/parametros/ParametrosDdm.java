package com.angelsoft.analizador.ctes.parametros;

import java.util.ResourceBundle;




public class ParametrosDdm {
	
	private static ResourceBundle parametros = ResourceBundle.getBundle("parametrosNatural");
	
	public static String NOMBRE = parametros.getString("DDM_NOMBRE");
	public static String EXT_GP = parametros.getString(NOMBRE + "_GP_EXT");
	public static String EXT_SRC = parametros.getString(NOMBRE + "_SRC_EXT");
	public static boolean LEER_SOLO_PRIMERALINEA = Boolean.TRUE;
	public static boolean QUITAR_ASTERISCADAS = Boolean.FALSE;
	
	public static String CADENA_DB = "DB:";
	public static String CADENA_FNR = "FILE:";
	public static String SEPARACION_NOMBRE = "-";
	public static int COL_HASTA_NOMBRE = 54;
	
}
