package com.angelsoft.gestion.ctes;

public class CtesXML {

	public static String ROOT_DEFINICIONES = "TABLAS";
	public static String TABLA_ID_DDM = "TABLA";
	public static String TABLA_ID_CAMPO = "CAMPO";
	public static String TABLA_ID_GRUPO = "GRUPO-CAMPOS";
	public static String TABLA_ID_SUPER = "SUPER";
	
	public static String CAMPO_ELEMENTO_REDEFINE = "REDEFINE";
	public static String CAMPO_ELEMENTO_FK = "FK";
	
	public static String TABLA_ATTR_DDM = "ddm";
	public static String TABLA_ATTR_DB = "db";
	public static String TABLA_ATTR_FNR = "fnr";
	
	public static String ELEMENTO_DESCRIPCION = "DESCRIPCION";
	public static String SUPER_ELEMENTO_DET = "SUPER-DET";

	public static String CAMPO_ATTR_NIVEL = "nivel";
	public static String CAMPO_ATTR_NOMBRE_CORTO = "nombreCorto";
	public static String CAMPO_ATTR_NOMBRE = "nombre";
	public static String CAMPO_ATTR_TIPO = "tipo";
	public static String CAMPO_ATTR_LONGITUD = "longitud";
	public static String CAMPO_ATTR_DESCRIPTOR = "descriptor";
	public static String CAMPO_ATTR_UNICO = "unico";
	public static String CAMPO_ATTR_OBLIGATORIO = "obligatorio";
	
	public static String GRUPO_ATTR_NIVEL = "nivel";
	public static String GRUPO_ATTR_NOMBRE = "nombre";
	
	public static String SUPER_ATTR_NOMBRE = "nombre";
	public static String SUPER_ATTR_NOMBRE_CORTO = "nombreCorto";
	public static String SUPER_DET_ATTR_NOMBRE_CORTO = "nombreCorto";
	public static String SUPER_DET_ATTR_CAMPO = "campo";
	public static String SUPER_DET_ATTR_DESDE = "desde";
	public static String SUPER_DET_ATTR_HASTA = "hasta";
	
	public static String FK_ATTR_TABLA = "tabla";
	public static String FK_ATTR_CAMPO = "campo";
	
	public static String INICIALIZACION_ID = "INICIALIZACION";
	public static String INICIALIZACION_ID_CODIGO = "CODIGO";
	public static String INICIALIZACION_ATTR_TABLA= "tabla";
	public static String CODIGO_ATTR_CODIGO= "codigo";
	public static String CODIGO_ATTR_DESCRIPCION= "descripcion";
	public static String CODIGO_ATTR_OBSERVACION= "observacion";
	
	public static String NOTA_ID = "NOTA";
	public static String NOTA_ATTR_ENTIDAD= "entidad";
	public static String NOTA_ATTR_TEXTO= "texto";
	
	public static String TAREA_ID = "TAREA";
	public static String TAREA_ID_FECHA= "fecha";
	public static String TAREA_ID_DESCRIPCION= "descripcion";
	public static String TAREA_ID_OBSERVACION= "observacion";
	public static String TAREA_ID_ESTADO= "estado";
	
	public static String PREGUNTA_ID = "FAQ";
	public static String PREGUNTA_ATTR_TIPO = "tipo";
	public static String PREGUNTA_ID_FECHA= "FECHA";
	public static String PREGUNTA_ID_TEXTO= "TEXTO";
	public static String PREGUNTA_ID_RESPUESTA= "RESPUESTA";
	public static String RESPUESTA_ID_FECHA= "FECHA";
	public static String RESPUESTA_ID_TEXTO= "TEXTO";
	public static String RESPUESTA_ID_PREGUNTA= "FAQ";
	public static String RESPUESTA_ID_NOTA= "NOTA";
}
