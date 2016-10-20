package com.angelsoft.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.angelsoft.gestion.formularios.DdmListaForm;
import com.angelsoft.gestion.formularios.InicializacionListaForm;
import com.angelsoft.gestion.formularios.LibreriaListaForm;
import com.angelsoft.gestion.formularios.NotaListaForm;
import com.angelsoft.gestion.formularios.PreguntaListaForm;
import com.angelsoft.gestion.formularios.TareaListaForm;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mitema")
@Widgetset("com.angelsoft.gestion.GestionWidgetset")
public class PrincipalUI extends UI {

	private List<Component> listaComponentesGestion = new ArrayList<Component>();
	private List<Component> listaComponentesNatural = new ArrayList<Component>();
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PrincipalUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout vLayout = new VerticalLayout();
		
		vLayout.setMargin(true);
		setContent(vLayout);
		
		listaComponentesGestion.add(new DdmListaForm(this));
		listaComponentesGestion.add(new NotaListaForm(this));
		listaComponentesGestion.add(new TareaListaForm(this));
		listaComponentesGestion.add(new InicializacionListaForm(this));
		listaComponentesGestion.add(new PreguntaListaForm(this));
		
		listaComponentesNatural.add(new LibreriaListaForm(this));
		
		final HorizontalLayout hLayout = new HorizontalLayout();
		Button botonImprimir = new Button("Imprime la página visible");
		botonImprimir.setIcon(FontAwesome.PRINT);
		botonImprimir.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		botonImprimir.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				JavaScript.getCurrent().execute("print();");
			}
		});
		
		MenuBar barmenu = new MenuBar();
		hLayout.addComponent(barmenu);
		hLayout.addComponent(botonImprimir);
		
		vLayout.addComponent(hLayout);
		
		MenuBar.Command mycommand = new MenuBar.Command() {
		    public void menuSelected(MenuItem selectedItem) {
		    	activaComponente(selectedItem.getText());
		    }  
		};
		
		MenuItem opciones = barmenu.addItem("De Gestión", null, null);
		for (Component componente: listaComponentesGestion){
			opciones.addItem(componente.getClass().getSimpleName(), null, mycommand);
			vLayout.addComponent(componente);
		}

		MenuItem utilidades = barmenu.addItem("De Natural", null, null);
		for (Component componente: listaComponentesNatural){
			utilidades.addItem(componente.getClass().getSimpleName(), null, mycommand);
			vLayout.addComponent(componente);
		}
		
		activaComponente("");
	}
	
	protected void activaComponente(String nombreComponente){
		for (Component componente: listaComponentesGestion){
			if (nombreComponente.equalsIgnoreCase(componente.getClass().getSimpleName())){
				componente.setVisible(true);
			} else {
				componente.setVisible(false);
			}
		}
		
		for (Component componente: listaComponentesNatural){
			if (nombreComponente.equalsIgnoreCase(componente.getClass().getSimpleName())){
				componente.setVisible(true);
			} else {
				componente.setVisible(false);
			}
		}
	}
}
