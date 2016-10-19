package com.angelsoft.gestion.formularios;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.angelsoft.utiles.GeneraXMLDefiniciones;
import com.angelsoft.utiles.GeneradorInformes;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public abstract class MisForm extends FormLayout {
	
	protected VerticalLayout vlPrincipal = new VerticalLayout();
	protected HorizontalLayout botonera = new HorizontalLayout();

	protected Grid gridDatos = new Grid();
	protected Object padre;
	protected Window subWindow;
	
	protected static Logger logger;
	
	protected String plantillaJasper = "";
	protected String nombreExport = "";
	protected Map<String, Object> parametrosPlantilla = new HashMap<String, Object>();
	
	protected final GeneradorInformes generadorInformes = new GeneradorInformes();
	protected final GeneraXMLDefiniciones generadorXML  = new GeneraXMLDefiniciones();
	
	public MisForm() {
		super();
		
		vlPrincipal.setSizeFull();
		vlPrincipal.setMargin(true);
		vlPrincipal.setSpacing(true);
		
		setResponsive(true);
		
		botonera.setSpacing(true);
	}
	
	public MisForm(Object padre) {
		super();
		this.padre = padre;
	}
	
	public void cierraVentana(){
		subWindow.close();
	}
}
