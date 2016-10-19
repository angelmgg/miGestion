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

import com.angelsoft.gestion.bean.auxiliares.Nota;
import com.angelsoft.gestion.bean.auxiliares.Pregunta;
import com.angelsoft.gestion.bean.auxiliares.Respuesta;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.gestion.ctes.CtesXML;

public class UtilesXMLPreguntas {
	
	public static List<Pregunta> leeXMLPreguntas(String path) throws JDOMException, IOException, ParseException{

		List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();

	    SAXBuilder builder = new SAXBuilder();
	    File xmlFile = new File(path);

        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build( xmlFile );

        //Se obtiene la raiz 'tables'
        Element rootNode = document.getRootElement();

        //Se obtiene la lista de hijos de la raiz
        List<Element> listaElementosPregunta = rootNode.getChildren(CtesXML.PREGUNTA_ID);

        if (listaElementosPregunta != null){
	        for(Iterator<Element> iterador0 = (Iterator<Element>) listaElementosPregunta.iterator(); iterador0.hasNext(); ){
	            Element elemento = (Element) iterador0.next();
	            listaPreguntas.add(elementToPregunta(elemento));
	        }
        }

	    return listaPreguntas;
	}
	
	public static Pregunta elementToPregunta(Element elementoPregunta) throws JDOMException, IOException, ParseException{
		Pregunta pregunta = new Pregunta();
		
		if (elementoPregunta.getAttributeValue(CtesXML.PREGUNTA_ATTR_TIPO) != null){
			pregunta.setTipo(elementoPregunta.getAttributeValue(CtesXML.PREGUNTA_ATTR_TIPO));
		}
					
    	List<Element> listaDetalle = elementoPregunta.getChildren();
    	for(Element elementoAux: listaDetalle){
    		if (CtesXML.PREGUNTA_ID_FECHA.equalsIgnoreCase(elementoAux.getName())){
    			pregunta.setFecha(Constantes.formatoYYYYMMDD.parse(elementoAux.getValue()));
    		} else if (CtesXML.PREGUNTA_ID_TEXTO.equalsIgnoreCase(elementoAux.getName())){
    			pregunta.setTexto(elementoAux.getText());
    		} else if (CtesXML.PREGUNTA_ID_RESPUESTA.equalsIgnoreCase(elementoAux.getName())){
    			pregunta.getListaRespuestas().add(elementToRespuesta(elementoAux));
    		} else if (CtesXML.PREGUNTA_ID.equalsIgnoreCase(elementoAux.getName())){
    		}
    	}
    	
    	return pregunta;
	}

	public static Respuesta elementToRespuesta(Element elementoRespuesta) throws JDOMException, IOException, ParseException{
		Respuesta respuesta = new Respuesta();
		
    	List<Element> listaDetalle = elementoRespuesta.getChildren();
    	for(Element elementoAux: listaDetalle){
    		if (CtesXML.RESPUESTA_ID_FECHA.equalsIgnoreCase(elementoAux.getName())){
    			respuesta.setFecha(Constantes.formatoYYYYMMDD.parse(elementoAux.getValue()));
    		} else if (CtesXML.RESPUESTA_ID_TEXTO.equalsIgnoreCase(elementoAux.getName())){
    			respuesta.setTexto(elementoAux.getText());
    		} else if (CtesXML.RESPUESTA_ID_PREGUNTA.equalsIgnoreCase(elementoAux.getName())){
    			respuesta.getListaPreguntas().add(elementToPregunta(elementoAux));
    		} else if (CtesXML.RESPUESTA_ID_NOTA.equalsIgnoreCase(elementoAux.getName())){
    			Nota notaAux = new Nota();
    			notaAux.setTexto(elementoAux.getText());
    			respuesta.getListaNotas().add(notaAux);
    		}
    	}
    	
    	return respuesta;
	}

}
