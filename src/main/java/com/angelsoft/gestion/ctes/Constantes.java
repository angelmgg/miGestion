package com.angelsoft.gestion.ctes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class Constantes implements Serializable{

	private static ResourceBundle parametros = ResourceBundle.getBundle("parametros");

	public static String DIR_RESULTADOS     = parametros.getString("DIR_TEMP");
	public static String XML_DEFINICIONES   = parametros.getString("XML_DEFINICIONES");
	public static String XML_INICIALICACIONES   = parametros.getString("XML_INICIALICACIONES");
	public static String XML_NOTAS   = parametros.getString("XML_NOTAS");
	public static String XML_TAREAS   = parametros.getString("XML_TAREAS");
	public static String XML_PREGUNTAS   = parametros.getString("XML_PREGUNTAS");
	
	public static DateFormat formatoDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
	public static DateFormat formatoYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	
	public static final SimpleDateFormat formatoFechayyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat formatoFechayyyyMMddHmmssSSS = new SimpleDateFormat("yyyyMMdd HmmssSSS");

	public static String PATH_RECURSOS = "/com/angelsoft/recursos/";
	public static String PATH_REPORTS = "/com/angelsoft/recursos/jasper/";
	public static String PATH_LOGOS = "/com/angelsoft/recursos/logos/";
	
	public static String PATH_TEMP ="c:\\TEMP\\";
	
	public static String LOGO_GOB = "logoIzq.png";
	public static String LOGO_APP = "logoDer.png";
	
	public static String EXTENSION_TXT = ".txt";
	public static String EXTENSION_PDF = ".pdf";
	public static String EXTENSION_EXCEL = ".xls";
	public static String EXTENSION_PLANTILLAS_EXCEL = "ExcelReport.jasper";
	public static String EXTENSION_PLANTILLAS_PDF = "Report.jasper";
	
	public static String[] LETRAS= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	private static final String[] EXT_FIC_COPIAR = { "txt" };
	public static final List<String> LISTA_EXTENSIONES_FIC_COPIAR = Arrays.asList(Constantes.EXT_FIC_COPIAR);
}
