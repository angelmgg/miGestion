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

import com.angelsoft.gestion.bean.auxiliares.InicializacionCodigo;
import com.angelsoft.gestion.ctes.CtesXML;

public class UtilesXMLInicializacion {
	
	public static List<InicializacionCodigo> leeXMLInicializaciones(String path) throws JDOMException, IOException, ParseException{

		List<InicializacionCodigo> listaInicializacionCodigo = new ArrayList<InicializacionCodigo>();

	    SAXBuilder builder = new SAXBuilder();
	    File xmlFile = new File(path);

        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build( xmlFile );

        //Se obtiene la raiz 'tables'
        Element rootNode = document.getRootElement();

        //Se obtiene la lista de hijos de la raiz
        List<Element> listaTablas = rootNode.getChildren(CtesXML.INICIALIZACION_ID);

        if (listaTablas != null){
	        for(Iterator<Element> iterador0 = (Iterator<Element>) listaTablas.iterator(); iterador0.hasNext(); ){
	            Element elemento = (Element) iterador0.next();
	            listaInicializacionCodigo.addAll(elementToInicializacionCodigo(elemento));
	        }
        }

	    return listaInicializacionCodigo;
	}
	
	public static List<InicializacionCodigo> elementToInicializacionCodigo(Element elementoInicializacion) throws JDOMException, IOException, ParseException{
		List<InicializacionCodigo> listaInicializacionCodigo = new ArrayList<InicializacionCodigo>();
		
    	List<Element> listaDetalle = elementoInicializacion.getChildren();
    	for(Element elementoAux: listaDetalle){
    		if (CtesXML.INICIALIZACION_ID_CODIGO.equalsIgnoreCase(elementoAux.getName())){
    			InicializacionCodigo inicializacionCodigo = new InicializacionCodigo();
    			inicializacionCodigo.setTabla(elementoInicializacion.getAttributeValue(CtesXML.INICIALIZACION_ATTR_TABLA));
    			inicializacionCodigo.setCodigo(elementoAux.getAttributeValue(CtesXML.CODIGO_ATTR_CODIGO));
    			inicializacionCodigo.setValor(elementoAux.getAttributeValue(CtesXML.CODIGO_ATTR_DESCRIPCION));
    			inicializacionCodigo.setObservacion(elementoAux.getAttributeValue(CtesXML.CODIGO_ATTR_OBSERVACION));
    			
    			listaInicializacionCodigo.add(inicializacionCodigo);
    		}
    	}
    	
    	return listaInicializacionCodigo;
	}
	
}
