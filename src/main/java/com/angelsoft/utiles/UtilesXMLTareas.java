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

import com.angelsoft.gestion.bean.auxiliares.Tarea;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.gestion.ctes.CtesXML;

public class UtilesXMLTareas {
	
	public static List<Tarea> leeXMLTareas(String path) throws JDOMException, IOException, ParseException{

		List<Tarea> listaTareas = new ArrayList<Tarea>();

	    SAXBuilder builder = new SAXBuilder();
	    File xmlFile = new File(path);

        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build( xmlFile );

        //Se obtiene la raiz 'tables'
        Element rootNode = document.getRootElement();

        //Se obtiene la lista de hijos de la raiz
        List<Element> listaElementosNota = rootNode.getChildren(CtesXML.TAREA_ID);

        if (listaElementosNota != null){
	        for(Iterator<Element> iterador0 = (Iterator<Element>) listaElementosNota.iterator(); iterador0.hasNext(); ){
	            Element elemento = (Element) iterador0.next();
	            listaTareas.add(elementToTarea(elemento));
	        }
        }

	    return listaTareas;
	}
	
	public static Tarea elementToTarea(Element elementoTarea) throws JDOMException, IOException, ParseException{
		Tarea tarea = new Tarea();
		
		List<Element> listaDetalle = elementoTarea.getChildren();
		for(Element elementoAux: listaDetalle){
    		if (CtesXML.TAREA_ID_FECHA.equalsIgnoreCase(elementoAux.getName())){
    			tarea.setFecha(Constantes.formatoYYYYMMDD.parse(elementoAux.getValue()));
    		} else if (CtesXML.TAREA_ID_DESCRIPCION.equalsIgnoreCase(elementoAux.getName())){
    			tarea.setDescripcion(elementoAux.getValue());
    		} else if (CtesXML.TAREA_ID_OBSERVACION.equalsIgnoreCase(elementoAux.getName())){
    			tarea.setObservacion(elementoAux.getValue());    			
    		} else if (CtesXML.TAREA_ID_ESTADO.equalsIgnoreCase(elementoAux.getName())){
    			tarea.setEstado(elementoAux.getValue());
    		}
    	}
    	
    	return tarea;
	}
	
}
