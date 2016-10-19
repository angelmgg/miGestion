package com.angelsoft.gestion.ctes;

import java.util.Arrays;
import java.util.List;

public class CtesNatural {

	public static final String TIPO_ALFANUMERICO = "A";
	public static final String TIPO_NUMERICO = "N";
	public static final String TIPO_DATE = "D";
	public static final String TIPO_TIME = "T";
	public static final String TIPO_BOOLEAN = "B";
	
	private static final String[] TIPOS_CAMPO = { CtesNatural.TIPO_ALFANUMERICO, CtesNatural.TIPO_NUMERICO, CtesNatural.TIPO_DATE, CtesNatural.TIPO_TIME ,CtesNatural.TIPO_BOOLEAN};
	public static final List<String> LISTA_TIPOS_CAMPO = Arrays.asList(CtesNatural.TIPOS_CAMPO);

}
