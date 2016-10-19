package com.angelsoft.analizador.ctes;

import java.io.Serializable;

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

@SuppressWarnings("serial")
public class ConstantesNatural implements Serializable{

//	public static String DIR_CODIGO_FUENTE  = "Y:\\natural_unix\\codigo_fuente\\DES\\";
	public static String DIR_CODIGO_FUENTE  = "Z:\\codigo_fuente\\DES\\";
	public static String DIR_COMPILADOS     = "GP";
	public static String DIR_FUENTES        = "SRC";
	
	public static int SIZE_CONT_LINEA = 4;
	public static String [] ELEMENTOS_APLICACION = {ParametrosDdm.NOMBRE,ParametrosGlobal.NOMBRE,ParametrosHelprutina.NOMBRE,ParametrosLocal.NOMBRE,ParametrosMapa.NOMBRE,ParametrosParameter.NOMBRE,ParametrosPrograma.NOMBRE,ParametrosSubprograma.NOMBRE,ParametrosSubrutina.NOMBRE,ParametrosCopycode.NOMBRE, ParametrosTexto.NOMBRE};
	
	public static String [] CASOS_COMENTARIOS_INICIO_LINEA = {"*"};
	public static String [] CASOS_MARK = {" MARK* ", " MARK * "};
	public static String [] CASOS_EM = {" EM =", " EM= ", " EM = ", "(EM =", "(EM= ", "(EM = "};
	public static String [] CASOS_CMSWRKFN  = {" CMSWRKFN "};

	public static String [] LLAMADAS_A_PROGRAMAS = {"FETCH RETURN " , "FETCH "};
	public static String [] LLAMADAS_A_SUBPROGRAMAS = {"CALLNAT " , "CALL "};
	public static String [] USO_INCLUDES = {"INCLUDE "};
	public static String [] USO_VIEWS = {"VIEW OF "};
	public static String [] DEFINICION_SUBRUTINAS = {"DEFINE SUBROUTINE "};
	public static String [] USO_LOCAL = {"LOCAL USING "};
	public static String [] USO_PARAMETER = {"PARAMETER USING "};
	public static String [] USO_CONSULTAS = {"CODIGO-CONSULTA"};
	public static String [] USO_MAPAS = {"USING MAP "};
	public static String [] USO_HELPROUTINES ={"HE="}; 
}
