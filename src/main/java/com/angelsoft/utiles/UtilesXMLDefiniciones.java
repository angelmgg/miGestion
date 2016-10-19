package com.angelsoft.utiles;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.angelsoft.gestion.bean.Campo;
import com.angelsoft.gestion.bean.Estructura;
import com.angelsoft.gestion.bean.ForeingKey;
import com.angelsoft.gestion.bean.SuperDescriptor;
import com.angelsoft.gestion.bean.SuperDescriptorDetalle;
import com.angelsoft.gestion.bean.entidades.Ddm;
import com.angelsoft.gestion.ctes.CtesXML;

public class UtilesXMLDefiniciones {
	private static int contCampos = 0;
	private static int contSD = 0;
	
	public static List<Ddm> leeXMLDdms(String path) throws JDOMException, IOException, ParseException, CloneNotSupportedException{

		List<Ddm> listaDdms = new ArrayList<Ddm>();

	    SAXBuilder builder = new SAXBuilder();
	    File xmlFile = new File(path);

        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build( xmlFile );

        //Se obtiene la raiz 'tables'
        Element rootNode = document.getRootElement();

        //Se obtiene la lista de hijos de la raiz
        List<Element> listaTablas = rootNode.getChildren(CtesXML.TABLA_ID_DDM);

        if (listaTablas != null){

	        for(Iterator<Element> iterador0 = (Iterator<Element>) listaTablas.iterator(); iterador0.hasNext(); ){
	            Element elemento = (Element) iterador0.next();
            	Ddm ddmAux = elementToDdm(elemento);
            	listaDdms.add(ddmAux);
	        }
        }

	    return listaDdms;
	}
	
	public static Ddm elementToDdm(Element elementoDdm) throws JDOMException, IOException, ParseException, CloneNotSupportedException{
    	Ddm ddmAux = new Ddm();
    	contCampos = 0;
    	contSD = 0;
    	ddmAux.setNombre(elementoDdm.getAttributeValue(CtesXML.TABLA_ATTR_DDM));
    	ddmAux.setDb(Long.valueOf(elementoDdm.getAttributeValue(CtesXML.TABLA_ATTR_DB)));
    	ddmAux.setFnr(Long.valueOf(elementoDdm.getAttributeValue(CtesXML.TABLA_ATTR_FNR)));
    	
    	List<Element> listaDetalleTabla = elementoDdm.getChildren();
    	for(Element elementoAux: listaDetalleTabla){
    		if (CtesXML.ELEMENTO_DESCRIPCION.equalsIgnoreCase(elementoAux.getName())){
    			ddmAux.setDescripcion(elementoAux.getValue());
    		} else if (CtesXML.TABLA_ID_CAMPO.equalsIgnoreCase(elementoAux.getName())){
    			ddmAux.getListaDetalle().add(elementToCampo(elementoAux, 0, Boolean.FALSE));
    		} else if (CtesXML.TABLA_ID_GRUPO.equalsIgnoreCase(elementoAux.getName())){
    			ddmAux.getListaDetalle().add(elementToEstructura(elementoAux, 0));
    		} else if (CtesXML.TABLA_ID_SUPER.equalsIgnoreCase(elementoAux.getName())){
    			ddmAux.getListaDetalle().add(elementToSuperDescriptor(elementoAux));    			
    		}
    	}
    	
    	for(SuperDescriptor superDes: ddmAux.getListaSuperDescriptores()){
    		for(Object objetoAux: superDes.getListaDetalle()){
    			if (objetoAux instanceof SuperDescriptorDetalle){
    				String cadenaBuscar = ((SuperDescriptorDetalle)objetoAux).getNombreCampo();
    				for(Campo campoAux: ddmAux.getListaCampos()){
    					if (campoAux.getNombre().equalsIgnoreCase(cadenaBuscar)){
    						((SuperDescriptorDetalle)objetoAux).setCampo(campoAux.clone());
    					}
    				}
    			}
    		}
    	}
    	
    	return ddmAux;
	}
	
	public static Campo elementToCampo(Element elementoCampo, int nivelPadre, Boolean deRedefine) throws JDOMException, IOException, ParseException{
		Campo campoAux = new Campo();
		
		if (elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_NIVEL) != null){
			campoAux.setNivel(Integer.valueOf(elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_NIVEL)));
		} else {
			campoAux.setNivel(nivelPadre + 1);
		}
		
		if (!deRedefine){
			if (elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_NOMBRE_CORTO) != null){
				campoAux.setNombreCorto(elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_NOMBRE_CORTO));
			} else {
				campoAux.setNombreCorto(UtilesObjetos.dameNombreCorto(contCampos++));
			}
		}
		
		campoAux.setNombre(elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_NOMBRE));
		campoAux.setTipo(elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_TIPO));
		
		if (elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_LONGITUD) != null){
			campoAux.setLongitud(Integer.valueOf(elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_LONGITUD)));
		}
		
		if ("S".equalsIgnoreCase(elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_DESCRIPTOR))){
			campoAux.setEsDescriptor(Boolean.TRUE);
		};
		if ("S".equalsIgnoreCase(elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_UNICO))){
			campoAux.setEsUnico(Boolean.TRUE);
		};
		if ("S".equalsIgnoreCase(elementoCampo.getAttributeValue(CtesXML.CAMPO_ATTR_OBLIGATORIO))){
			campoAux.setEsObligatorio(Boolean.TRUE);
		};
		    	
    	List<Element> listaDetalleCampo = elementoCampo.getChildren();
    	for(Element elementoAux: listaDetalleCampo){
    		if (CtesXML.ELEMENTO_DESCRIPCION.equalsIgnoreCase(elementoAux.getName())){
    			campoAux.setDescripcion(elementoAux.getValue());
    			
    		} else if (CtesXML.CAMPO_ELEMENTO_FK.equalsIgnoreCase(elementoAux.getName())){
    			ForeingKey fkAux = new ForeingKey();
    			fkAux.setTabla(elementoAux.getAttributeValue(CtesXML.FK_ATTR_TABLA));
    			fkAux.setCampo(elementoAux.getAttributeValue(CtesXML.FK_ATTR_CAMPO));
    			campoAux.setFk(fkAux);
    			
    		} else if (CtesXML.CAMPO_ELEMENTO_REDEFINE.equalsIgnoreCase(elementoAux.getName())){
    			List<Element> listaDetalleRedefine = elementoAux.getChildren();
    	    	for(Element elementoRedefineAux: listaDetalleRedefine){
    	    		Campo redefinicion = elementToCampo(elementoRedefineAux, campoAux.getNivel(), Boolean.TRUE);
    	    		redefinicion.setEsRedefinicion(Boolean.TRUE);
    	    		campoAux.getRedefine().add(redefinicion);
    	    	}
    		}
    	}
    	
    	return campoAux;
	}
	
	public static Estructura elementToEstructura(Element elementoEstructura, int nivelPadre) throws JDOMException, IOException, ParseException{
		Estructura estructuraAux = new Estructura();
		
		if (elementoEstructura.getAttributeValue(CtesXML.GRUPO_ATTR_NIVEL) != null){
			estructuraAux.setNivel(Integer.valueOf(elementoEstructura.getAttributeValue(CtesXML.GRUPO_ATTR_NIVEL)));
		} else {
			estructuraAux.setNivel(nivelPadre);
		}
		
		estructuraAux.setNombre(elementoEstructura.getAttributeValue(CtesXML.GRUPO_ATTR_NOMBRE));
		    	
    	List<Element> listaDetalleCampo = elementoEstructura.getChildren();
    	for(Element elementoAux: listaDetalleCampo){
    		if (CtesXML.ELEMENTO_DESCRIPCION.equalsIgnoreCase(elementoAux.getName())){
    			estructuraAux.setDescripcion(elementoAux.getValue());
    		} else if (CtesXML.TABLA_ID_CAMPO.equalsIgnoreCase(elementoAux.getName())){
    			estructuraAux.getListaDetalle().add(elementToCampo(elementoAux, estructuraAux.getNivel(), Boolean.FALSE));
    		} else if (CtesXML.TABLA_ID_GRUPO.equalsIgnoreCase(elementoAux.getName())){
    			estructuraAux.getListaDetalle().add(elementToEstructura(elementoAux, estructuraAux.getNivel()));
    		}
    	}
    	
    	return estructuraAux;
	}
	
	public static SuperDescriptor elementToSuperDescriptor(Element elementoSuper) throws JDOMException, IOException, ParseException{
		SuperDescriptor superAux = new SuperDescriptor();
		
		if (elementoSuper.getAttributeValue(CtesXML.SUPER_ATTR_NOMBRE_CORTO) != null){
			superAux.setNombreCorto(elementoSuper.getAttributeValue(CtesXML.SUPER_ATTR_NOMBRE_CORTO));
		} else {
			superAux.setNombreCorto("S" + ++contSD);
		}
		
		superAux.setNombre(elementoSuper.getAttributeValue(CtesXML.SUPER_ATTR_NOMBRE));
		
		List<Element> listaDetalleSuper = elementoSuper.getChildren();
    	for(Element elementoAux: listaDetalleSuper){
    		if (CtesXML.ELEMENTO_DESCRIPCION.equalsIgnoreCase(elementoAux.getName())){
    			superAux.setDescripcion(elementoAux.getValue());
    		} else if (CtesXML.SUPER_ELEMENTO_DET.equalsIgnoreCase(elementoAux.getName())){
    			SuperDescriptorDetalle superDescriptorDetalle = new SuperDescriptorDetalle(); 	

    			superDescriptorDetalle.setNombreCampo(elementoAux.getAttributeValue(CtesXML.SUPER_DET_ATTR_CAMPO));
    			
    			if (elementoAux.getAttributeValue(CtesXML.SUPER_DET_ATTR_DESDE) != null){
    				superDescriptorDetalle.setPosDesde(Integer.valueOf(elementoAux.getAttributeValue(CtesXML.SUPER_DET_ATTR_DESDE)));
    			}
    			if (elementoAux.getAttributeValue(CtesXML.SUPER_DET_ATTR_HASTA) != null){
    				superDescriptorDetalle.setPosHasta(Integer.valueOf(elementoAux.getAttributeValue(CtesXML.SUPER_DET_ATTR_HASTA)));
    			}
    			
    			superAux.getListaDetalle().add(superDescriptorDetalle);
    		}
    	}
		return superAux;
	}
	
}
