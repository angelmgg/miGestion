package com.angelsoft.utiles;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.angelsoft.gestion.bean.Campo;
import com.angelsoft.gestion.bean.Estructura;
import com.angelsoft.gestion.bean.ForeingKey;
import com.angelsoft.gestion.bean.SuperDescriptor;
import com.angelsoft.gestion.bean.SuperDescriptorDetalle;
import com.angelsoft.gestion.bean.entidades.Ddm;
import com.angelsoft.gestion.ctes.CtesXML;

public class GeneraXMLDefiniciones {

	private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private Document doc = null;
	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
    
	public Document listaDdmToXML(List<Ddm> lista, String nombreXML){
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			doc = implementation.createDocument(null, CtesXML.ROOT_DEFINICIONES, null);
			doc.setXmlVersion("1.0");
			
            Element raiz = doc.getDocumentElement();
            
			for(Ddm ddmAux: lista){
				
				Map<String, String> atributos = new LinkedHashMap<String, String>();
				
				atributos.put(CtesXML.TABLA_ATTR_DDM, ddmAux.getNombre());
				if (ddmAux.getDb() != null){
					atributos.put(CtesXML.TABLA_ATTR_DB, ddmAux.getDb().toString());
				}
				if (ddmAux.getFnr() != null){
					atributos.put(CtesXML.TABLA_ATTR_FNR, ddmAux.getFnr().toString());
				}
				
				Node elementoDdm = dameElemento(CtesXML.TABLA_ID_DDM, "", atributos);
				
				if (ddmAux.getDescripcion() != null && !"".equalsIgnoreCase(ddmAux.getDescripcion())){
					elementoDdm.appendChild(dameElemento(CtesXML.ELEMENTO_DESCRIPCION, ddmAux.getDescripcion(), new LinkedHashMap<String, String>()));
				}
				
				for (Object objetoAux: ddmAux.getListaDetalle()){
					if (objetoAux instanceof Campo){
						elementoDdm.appendChild(dameElemento((Campo) objetoAux));
						
					} else if (objetoAux instanceof Estructura){
						elementoDdm.appendChild(dameElemento((Estructura) objetoAux));
					} else if (objetoAux instanceof ForeingKey){
						elementoDdm.appendChild(dameElemento((ForeingKey) objetoAux));
					} else if (objetoAux instanceof SuperDescriptor){
						elementoDdm.appendChild(dameElemento((SuperDescriptor) objetoAux));
					}
				} 
				
				raiz.appendChild(elementoDdm);
				
			}
						
		} catch (Exception e) {
			logger.log(Level.SEVERE, "GeneraXML.listaDdmToXML " + e.toString());
		}
        
        
        return doc;
	}
	
	private Node dameElemento(SuperDescriptor superAux) {
		   
		Map<String, String> atributos = new LinkedHashMap<String, String>();
		
		if (superAux.getNombreCorto() != null && !"".equalsIgnoreCase(superAux.getNombreCorto())){
			atributos.put(CtesXML.SUPER_ATTR_NOMBRE_CORTO, superAux.getNombreCorto());
		}
		if (superAux.getNombre() != null && !"".equalsIgnoreCase(superAux.getNombre())){
			atributos.put(CtesXML.SUPER_ATTR_NOMBRE, superAux.getNombre());
		}
        
		Node elementoSuper = dameElemento(CtesXML.TABLA_ID_GRUPO, "", atributos);
		
		if (superAux.getDescripcion() != null && !"".equalsIgnoreCase(superAux.getDescripcion())){
			elementoSuper.appendChild(dameElemento(CtesXML.ELEMENTO_DESCRIPCION, superAux.getDescripcion(), new LinkedHashMap<String, String>()));
		}
		
		for (Object objetoAux: superAux.getListaDetalle()){
			if (objetoAux instanceof SuperDescriptorDetalle){
				elementoSuper.appendChild(dameElemento((SuperDescriptorDetalle) objetoAux));
			}
		} 
		return elementoSuper;
    }
	
	private Node dameElemento(SuperDescriptorDetalle superDetAux) {
		
		Map<String, String> atributos = new LinkedHashMap<String, String>();
		
		if (superDetAux.getCampo() != null){
			if (superDetAux.getCampo().getNombreCorto() != null && !"".equalsIgnoreCase(superDetAux.getCampo().getNombreCorto())){
				atributos.put(CtesXML.SUPER_DET_ATTR_NOMBRE_CORTO, superDetAux.getCampo().getNombreCorto());
			}
			if (superDetAux.getCampo().getNombre() != null && !"".equalsIgnoreCase(superDetAux.getCampo().getNombre())){
				atributos.put(CtesXML.SUPER_DET_ATTR_CAMPO, superDetAux.getCampo().getNombre());
			}
		}
		if (superDetAux.getPosDesde() != null){
			atributos.put(CtesXML.SUPER_DET_ATTR_DESDE, superDetAux.getPosDesde().toString());
		}
		if (superDetAux.getPosHasta() != null){
			atributos.put(CtesXML.SUPER_DET_ATTR_HASTA, superDetAux.getPosHasta().toString());
		}
		
		Node elementoDetalle = dameElemento(CtesXML.SUPER_ELEMENTO_DET, "", atributos);
		
		return elementoDetalle;
	}
	
	private Node dameElemento(Estructura estructuraAux) {
		   
		Map<String, String> atributos = new LinkedHashMap<String, String>();
		
		if (estructuraAux.getNivel() != null){
			atributos.put(CtesXML.GRUPO_ATTR_NIVEL, estructuraAux.getNivel().toString());
		}
		if (estructuraAux.getNombre() != null){
			atributos.put(CtesXML.GRUPO_ATTR_NOMBRE, estructuraAux.getNombre());
		}
        
		Node elementoEstructura = dameElemento(CtesXML.TABLA_ID_GRUPO, "", atributos);
		
		for (Object objetoAux: estructuraAux.getListaDetalle()){
			if (objetoAux instanceof Campo){
				elementoEstructura.appendChild(dameElemento((Campo) objetoAux));
				
			} else if (objetoAux instanceof Estructura){
				elementoEstructura.appendChild(dameElemento((Estructura) objetoAux));
			}
		} 
		return elementoEstructura;
    }
	
	private Node dameElemento(Campo campoAux) {
   
		Map<String, String> atributos = new LinkedHashMap<String, String>();
		
		if (campoAux.getNivel() != null){
			atributos.put(CtesXML.CAMPO_ATTR_NIVEL, campoAux.getNivel().toString());
		}
		if (campoAux.getNombreCorto() != null){
			atributos.put(CtesXML.CAMPO_ATTR_NOMBRE_CORTO, campoAux.getNombreCorto());
		}
		if (campoAux.getNombre() != null){
			atributos.put(CtesXML.CAMPO_ATTR_NOMBRE, campoAux.getNombre());
		}
		if (campoAux.getTipo() != null){
			atributos.put(CtesXML.CAMPO_ATTR_TIPO, campoAux.getTipo());
		}
		if (campoAux.getLongitud() != null){
			atributos.put(CtesXML.CAMPO_ATTR_LONGITUD, campoAux.getLongitud().toString());
		}
		if (campoAux.getEsDescriptor()){
			atributos.put(CtesXML.CAMPO_ATTR_DESCRIPTOR, "S");
		}
		if (campoAux.getEsObligatorio()){
			atributos.put(CtesXML.CAMPO_ATTR_OBLIGATORIO, "S");
		}
		if (campoAux.getEsUnico()){
			atributos.put(CtesXML.CAMPO_ATTR_UNICO, "S");
		}
        
		Node elementoCampo = dameElemento(CtesXML.TABLA_ID_CAMPO, "", atributos);
		
		if (campoAux.getDescripcion() != null && !"".equalsIgnoreCase(campoAux.getDescripcion())){
			elementoCampo.appendChild(dameElemento(CtesXML.ELEMENTO_DESCRIPCION, campoAux.getDescripcion(), new LinkedHashMap<String, String>()));
		}
		
		if (campoAux.getFk() != null){
			Map<String, String> atributosFk = new LinkedHashMap<String, String>();
			atributosFk.put(CtesXML.FK_ATTR_TABLA, campoAux.getFk().getTabla());
			atributosFk.put(CtesXML.FK_ATTR_CAMPO, campoAux.getFk().getCampo());
			
			elementoCampo.appendChild(dameElemento(CtesXML.CAMPO_ELEMENTO_FK, "", atributosFk));
		}
		
		if (!campoAux.getRedefine().isEmpty()){
			Node elementoRedefine = dameElemento(CtesXML.CAMPO_ELEMENTO_REDEFINE, "", new LinkedHashMap<String, String>());
			
			for (Object objetoAux: campoAux.getRedefine()){
				if (objetoAux instanceof Campo){
					elementoRedefine.appendChild(dameElemento((Campo) objetoAux));
				}
			}
			elementoCampo.appendChild(elementoRedefine);
		}
		
		return elementoCampo;
    }
	
	private Node dameElemento(ForeingKey foreingKeyAux) {
		
		Map<String, String> atributos = new LinkedHashMap<String, String>();
		
		if (foreingKeyAux.getTabla() != null && !"".equalsIgnoreCase(foreingKeyAux.getTabla())){
			atributos.put(CtesXML.FK_ATTR_TABLA, foreingKeyAux.getTabla());
		}
		if (foreingKeyAux.getCampo() != null && !"".equalsIgnoreCase(foreingKeyAux.getCampo())){
			atributos.put(CtesXML.FK_ATTR_CAMPO, foreingKeyAux.getCampo());
		}
		
		Node elementoEstructura = dameElemento(CtesXML.CAMPO_ELEMENTO_FK, "", atributos);
		
		return elementoEstructura;
	}

	private Node dameElemento(String name, String value, Map<String, String> atributos) {
        Element elemento = doc.createElement(name);
        for (Map.Entry entry : atributos.entrySet()) {
            elemento.setAttribute((String) entry.getKey(), (String) entry.getValue());
        }
        
        elemento.appendChild(doc.createTextNode(value));
        return elemento;
    }
}
