package com.angelsoft.gestion.formularios;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.analizador.bean.entidades.ElementoNatural;
import com.angelsoft.analizador.bean.entidades.NombreArchivo;
import com.angelsoft.analizador.bean.entidades.natural.Aplicacion;
import com.angelsoft.analizador.bean.entidades.natural.Copycode;
import com.angelsoft.analizador.bean.entidades.natural.Ddm;
import com.angelsoft.analizador.bean.entidades.natural.ElementoGenerico;
import com.angelsoft.analizador.bean.entidades.natural.Global;
import com.angelsoft.analizador.bean.entidades.natural.Helprutina;
import com.angelsoft.analizador.bean.entidades.natural.Local;
import com.angelsoft.analizador.bean.entidades.natural.Mapa;
import com.angelsoft.analizador.bean.entidades.natural.Parameter;
import com.angelsoft.analizador.bean.entidades.natural.Programa;
import com.angelsoft.analizador.bean.entidades.natural.Subprograma;
import com.angelsoft.analizador.bean.entidades.natural.Subrutina;
import com.angelsoft.analizador.bean.entidades.natural.Uso;
import com.angelsoft.analizador.ctes.parametros.ParametrosCopycode;
import com.angelsoft.analizador.ctes.parametros.ParametrosDdm;
import com.angelsoft.analizador.ctes.parametros.ParametrosGlobal;
import com.angelsoft.analizador.ctes.parametros.ParametrosHelprutina;
import com.angelsoft.analizador.ctes.parametros.ParametrosLocal;
import com.angelsoft.analizador.ctes.parametros.ParametrosMapa;
import com.angelsoft.analizador.ctes.parametros.ParametrosParameter;
import com.angelsoft.analizador.ctes.parametros.ParametrosPrograma;
import com.angelsoft.analizador.ctes.parametros.ParametrosSubprograma;
import com.angelsoft.analizador.ctes.parametros.ParametrosSubrutina;
import com.angelsoft.analizador.manager.ElementoNaturalManager;
import com.angelsoft.analizador.manager.impl.ElementoNaturalManagerImpl;
import com.angelsoft.analizador.utiles.UtilesPath;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class LibreriaListaForm extends MisForm {

	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	private final ElementoNaturalManager elementoNaturalManager = new ElementoNaturalManagerImpl();
	private List<Aplicacion> listaAplicaciones = new LinkedList<Aplicacion>();
	private SortedMap<NombreArchivo, ElementoNatural> mapaElementos = new TreeMap<NombreArchivo, ElementoNatural>();
	
	final List<ElementoGenerico> listaObjetos = new LinkedList<ElementoGenerico>();
	
	private final TextField textUbicacion = new TextField();
	private TreeTable ttable;
	ComboBox comboAplicaciones = new ComboBox();
	
	final Button botonExcel = new Button("Volcar a XLS");

	public LibreriaListaForm(Object padre) {
		super(padre);
		inicializarElementos();
	}

	public void inicializarElementos() {
		textUbicacion.setInputPrompt("Path de las Fuentes:");
		textUbicacion.setWidth("100%");
		
		final Button botonLimpiar = new Button(FontAwesome.TIMES);
		botonLimpiar.setDescription("Limpiar Path Fuentes");
		botonLimpiar.addClickListener(e -> {
			textUbicacion.clear();
			refrescaListaAplicaciones();
		});
		
		final Button botonActualizar = new Button(FontAwesome.REFRESH);
		botonActualizar.setDescription("Actualiza");
		botonLimpiar.setDescription("Buscar las aplicaciones de la nueva ubicación");
		botonActualizar.addClickListener(e -> {
			refrescaListaAplicaciones();
		});
		
		// Create a selection component bound to the container
		final CssLayout filtros1 = new CssLayout();
		filtros1.addComponents(textUbicacion, botonLimpiar, botonActualizar);
		filtros1.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		botonExcel.setIcon(FontAwesome.FILE_EXCEL_O);
		botonExcel.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		botonExcel.setDescription("XLS Ddm´s Detalle");
		botonExcel.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				listaDatosToXLS();
			}
		});
		botonExcel.setVisible(false);
		
		comboAplicaciones.addValueChangeListener(e ->{ 
			refrescaListaDatos();
		});
		comboAplicaciones.setVisible(false);
		
		final CssLayout filtros2 = new CssLayout();
		filtros2.addComponents(comboAplicaciones, botonExcel);
		filtros2.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		
		ttable = new TreeTable("Objetos Libreria");
		ttable.addContainerProperty("Nombre", String.class, null);
		ttable.setColumnWidth("Nombre", 250);
		ttable.addContainerProperty("Tipo", String.class, null);
		ttable.setColumnWidth("Tipo", 150);
		ttable.addContainerProperty("Uso", String.class, null);
		ttable.setColumnWidth("Uso", 175);
		ttable.addContainerProperty("Usado", String.class, null);
		ttable.setColumnWidth("Usado", 200);
		ttable.setVisible(false);
		ttable.setWidth("850");
		
		ttable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
		    @Override
		    public void itemClick(ItemClickEvent itemClickEvent) {
		    	String cadena = (String) itemClickEvent.getItem().getItemProperty("Nombre").getValue();
		    	if (cadena == null){
		    		cadena = (String) itemClickEvent.getItem().getItemProperty("Usado").getValue();
		    	}
		    	addEnListaSeleccionados(cadena);
		    	
		    	
		    	if (ttable.hasChildren(itemClickEvent.getItemId())){
		    		
			    	for (Object hijoAux: ttable.getChildren(itemClickEvent.getItemId())){
			    		cadena = (String) ttable.getItem(hijoAux).getItemProperty("Usado").getValue();
			    		addEnListaSeleccionados(cadena);
			    	}
		    	}
		    }
		});
		
		gridDatos.setCaption("Objetos Seleccionados");
		gridDatos.setSelectionMode(SelectionMode.NONE);

		gridDatos.addColumn("libreria", String.class);
		gridDatos.getColumn("libreria").setWidth(150);
		gridDatos.addColumn("nombre", String.class);
		gridDatos.setColumnOrder("libreria","nombre");
		gridDatos.setColumnReorderingAllowed(false); // Permite mover las columnas
		gridDatos.setWidth("90%");
		gridDatos.setSizeFull();
		gridDatos.setVisible(false);
		
		final HorizontalLayout hLayout = new HorizontalLayout(ttable, gridDatos);
		hLayout.setStyleName(DESIGN_ATTR_PLAIN_TEXT);
		hLayout.setSizeFull();
		
		final VerticalLayout main = new VerticalLayout(filtros1, filtros2, hLayout);
		main.setSizeFull();

		vlPrincipal.addComponents(main);

		setSpacing(true);
		addComponent(vlPrincipal);
	}

	private void refrescaListaAplicaciones() {
		ttable.removeAllItems();
		ttable.refreshRowCache();
		comboAplicaciones.removeAllItems();
		comboAplicaciones.setVisible(false);
		botonExcel.setVisible(false);
		ttable.setVisible(false);
		gridDatos.setVisible(false);
		
		if (!"".equalsIgnoreCase(textUbicacion.getValue())){
			try {
				listaAplicaciones = UtilesPath.extraeAplicacionesDirectorio(new LinkedList<Aplicacion>(), new File(textUbicacion.getValue()));
				Collections.sort(listaAplicaciones);		
				
				if (!listaAplicaciones.isEmpty()){
					for (Aplicacion aplicacionAux: listaAplicaciones){
						comboAplicaciones.addItem(aplicacionAux.getNombre());
					}
					comboAplicaciones.setVisible(true);
				}
			} catch (Exception e) {
				logger.log(Level.SEVERE, "refrescaListaAplicaciones " + e.toString());
			}
		}
		
	}
	
	private void refrescaListaDatos() {
		ttable.removeAllItems();
		ttable.refreshRowCache();
		botonExcel.setVisible(false);
		ttable.setVisible(false);
		
		if (comboAplicaciones.getValue() != null){
			mapaElementos = elementoNaturalManager.sacaElementosAplicacion(listaAplicaciones.get(comboAplicaciones.getTabIndex()));
			ttable.addItem(new Object[]{comboAplicaciones.getValue(), null, null, null}, 0);
			sacaDatosAplicacion();
			ttable.setVisible(true);
			botonExcel.setVisible(true);
		}
			
		// Expand the tree
		for (Object itemId: ttable.getContainerDataSource().getItemIds()) {
		    ttable.setCollapsed(itemId, true);

		    // As we're at it, also disallow children from the current leaves
		    if (! ttable.hasChildren(itemId))
		        ttable.setChildrenAllowed(itemId, false);
		}
	}

	private void sacaDatosAplicacion() {
		int nivel = 1;
		int nivelAux = 0;
		
		if (!mapaElementos.isEmpty()){
			for (Entry<NombreArchivo, ElementoNatural> elementoMapa :mapaElementos.entrySet()){
				NombreArchivo clave = elementoMapa.getKey();
				ElementoNatural elemento = elementoMapa.getValue();
				
				if (elemento instanceof Ddm){
					Ddm ddmAux = (Ddm) elementoMapa.getValue();
					ttable.addItem(new Object[]{ddmAux.getNombreDdm(), ParametrosDdm.NOMBRE, null, null}, nivel);
				} else if (elemento instanceof Parameter){ 
					Parameter parameterAux = (Parameter) elementoMapa.getValue();
					ttable.addItem(new Object[]{parameterAux.getNombre().getNombre(), ParametrosParameter.NOMBRE, null, null}, nivel);
				} else if (elemento instanceof Local){ 
					Local localAux = (Local) elementoMapa.getValue();
					ttable.addItem(new Object[]{localAux.getNombre().getNombre(), ParametrosLocal.NOMBRE, null, null}, nivel);
				} else if (elemento instanceof Programa){ 
					Programa programaAux = (Programa) elementoMapa.getValue();
					ttable.addItem(new Object[]{programaAux.getNombre().getNombre(), ParametrosPrograma.NOMBRE, null, null}, nivel);
				} else if (elemento instanceof Subprograma){ 
					Subprograma subprogramaAux = (Subprograma) elementoMapa.getValue();
					ttable.addItem(new Object[]{subprogramaAux.getNombre().getNombre(), ParametrosSubprograma.NOMBRE, null, null}, nivel);	
				} else if (elemento instanceof Copycode){ 
					Copycode copycodeAux = (Copycode) elementoMapa.getValue();
					ttable.addItem(new Object[]{copycodeAux.getNombre().getNombre(), ParametrosCopycode.NOMBRE, null, null}, nivel);
				} else if (elemento instanceof Mapa){
					Mapa mapaAux = (Mapa) elementoMapa.getValue();
					ttable.addItem(new Object[]{mapaAux.getNombre().getNombre(), ParametrosMapa.NOMBRE, null, null}, nivel);
				} else if (elemento instanceof Subrutina){
					Subrutina subrutinaAux = (Subrutina) elementoMapa.getValue();
					ttable.addItem(new Object[]{subrutinaAux.getNombre().getNombre(), ParametrosSubrutina.NOMBRE, null, null}, nivel);
				} else if (elemento instanceof Helprutina){
					Helprutina helprutinaAux = (Helprutina) elementoMapa.getValue();
					ttable.addItem(new Object[]{helprutinaAux.getNombre().getNombre(), ParametrosHelprutina.NOMBRE, null, null}, nivel);				
				} else if (elemento instanceof Local){ 
					Local localAux = (Local) elementoMapa.getValue();
					ttable.addItem(new Object[]{localAux.getNombre().getNombre(), ParametrosLocal.NOMBRE, null, null}, nivel);
				} else if (elemento instanceof Global){ 
					Global globalAux = (Global) elementoMapa.getValue();
					ttable.addItem(new Object[]{globalAux.getNombre().getNombre(), ParametrosGlobal.NOMBRE, null, null}, nivel);
				} else {
					ttable.addItem(new Object[]{clave.getNombre(), clave.getExtension(), null, null}, nivel);
				}
				nivelAux = añadeUsosElemento(elemento, nivel);
				ttable.setParent(nivel, 0);
				nivel = nivelAux;
			}
		}
	}
	
	private int añadeUsosElemento(ElementoNatural elemento, int nivel){
		int nivelActual = nivel + 1;
		
		if (elemento.getListaUsos() != null){
			for(Uso usoAux: elemento.getListaUsos()){
				ttable.addItem(new Object[]{null, null, usoAux.getComoLoLlama(), usoAux.getNombreLlamado()}, nivelActual);
				ttable.setParent(nivelActual++, nivel);
			}
		}
		return nivelActual;
	}
	
	private void addEnListaSeleccionados(String cadena) {
		listaObjetos.add(new ElementoGenerico(comboAplicaciones.getValue().toString(), cadena));
    	gridDatos.setContainerDataSource(new BeanItemContainer<>(ElementoGenerico.class, listaObjetos));
    	gridDatos.setVisible(true);
	}
	
	private void listaDatosToXLS() {
//		generadorInformes.creaXLS(plantillaJasper, "Dependencias", parametrosPlantilla, new JRBeanCollectionDataSource((List<Ddm>) gridDatos.getContainerDataSource().getItemIds()), nombreExport + Constantes.formatoFechayyyyMMddHmmssSSS.format(new Date()));
	}
}
