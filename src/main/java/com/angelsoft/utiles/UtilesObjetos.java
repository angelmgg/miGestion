package com.angelsoft.utiles;

import java.util.List;

import com.angelsoft.gestion.bean.Campo;
import com.angelsoft.gestion.bean.SuperDescriptor;
import com.angelsoft.gestion.bean.SuperDescriptorDetalle;
import com.angelsoft.gestion.ctes.Constantes;

public class UtilesObjetos {

	public static String dameNombreCorto(int contador){
		int cociente = contador / Constantes.LETRAS.length;
		int resto = contador % Constantes.LETRAS.length;
		
		return (cociente >= Constantes.LETRAS.length)? "??":Constantes.LETRAS[cociente] + Constantes.LETRAS[resto];
	}
	
	public static String formatoCreacion(Campo campoAux){
		String cadena = "";
		
		if (!campoAux.getEsSuperDescriptor()){
			if (!campoAux.getEsRedefinicion()){
				cadena += ((campoAux.getNivel() != null)?campoAux.getNivel().toString():"") + ","; 
				cadena += ((!"".equalsIgnoreCase(campoAux.getNombreCorto())?campoAux.getNombreCorto():"")) + ",";
				cadena += ((campoAux.getLongitudDB() != null)?campoAux.getLongitudDB().toString():"") +",";
				cadena += ((campoAux.getTipoDB() != null)?campoAux.getTipoDB().toString():"");
			}
			
//			if (campoAux.getEsUnico()){
//				cadena += ",U";
//			}
			
			if (campoAux.getEsDescriptor()){
				cadena += ",DE";
			}
		} 
		
		return cadena;
	}
	
	public static String formatoCreacion(SuperDescriptor superAux){
		String cadena = "";
		
		List<Object> superDetalle = superAux.getListaDetalle();
		for (Object objDetalle: superDetalle){
			if (objDetalle instanceof SuperDescriptorDetalle){
				SuperDescriptorDetalle supDetalle = (SuperDescriptorDetalle) objDetalle;
				cadena += (("".equalsIgnoreCase(cadena))?"":",") + supDetalle.getCampo().getNombreCorto();
				
				if (supDetalle.getPosDesde() != null && supDetalle.getPosDesde() != null){
					cadena += "(" + supDetalle.getPosDesde().toString()+ "," + supDetalle.getPosHasta() + ")";
				} else {
					cadena += "(1," + supDetalle.getCampo().getLongitudDB() + ")";
				}
			}
		}
		
		cadena = ((!"".equalsIgnoreCase(superAux.getNombreCorto())?superAux.getNombreCorto():"")) + "=" + cadena;
		
		return cadena;
	}
}
