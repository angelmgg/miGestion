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
import com.angelsoft.gestion.ctes.CtesXML;

public class UtilesXMLNotas {
	
	public static List<Nota> leeXMLNotas(String path) throws JDOMException, IOException, ParseException{

		List<Nota> listaNotas = new ArrayList<Nota>();

	    SAXBuilder builder = new SAXBuilder();
	    File xmlFile = new File(path);

        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build( xmlFile );

        //Se obtiene la raiz 'tables'
        Element rootNode = document.getRootElement();

        //Se obtiene la lista de hijos de la raiz
        List<Element> listaElementosNotas = rootNode.getChildren(CtesXML.NOTA_ID);

        if (listaElementosNotas != null){
	        for(Iterator<Element> iterador0 = (Iterator<Element>) listaElementosNotas.iterator(); iterador0.hasNext(); ){
	            Element elemento = (Element) iterador0.next();
	            listaNotas.add(elementToNota(elemento));
	        }
        }

	    return listaNotas;
	}
	
	public static Nota elementToNota(Element elementoNota) throws JDOMException, IOException, ParseException{
		Nota nota = new Nota();
		
    	nota.setEntidad(elementoNota.getAttributeValue(CtesXML.NOTA_ATTR_ENTIDAD));
    	nota.setTexto(elementoNota.getValue());
    	
    	return nota;
	}
	
}
