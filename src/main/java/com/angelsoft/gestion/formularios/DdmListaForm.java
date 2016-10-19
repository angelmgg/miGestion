package com.angelsoft.gestion.formularios;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.angelsoft.gestion.bean.entidades.Ddm;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.gestion.manager.DdmManager;
import com.angelsoft.gestion.manager.FicheroManager;
import com.angelsoft.utiles.Utiles;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings("serial")
public class DdmListaForm extends MisForm {

	{
		logger = Logger.getLogger(this.getClass().getName());
		nombreExport = "DDMs";
		plantillaJasper = "DdmDetalle";
		subWindow = new Window("Detalle DDM");
	}

	private final DdmManager ddmManager = DdmManager.getInstance();
	private final FicheroManager ficheroManager = FicheroManager.getInstance();
	private final TextField textoFiltro = new TextField();
	private final DdmForm formularioDdm = new DdmForm(this);

	public DdmListaForm(Object padre) {
		super(padre);
		inicializarElementos();
	}

	public void inicializarElementos() {
		textoFiltro.setInputPrompt("Filtrar por ...:");
		textoFiltro.addTextChangeListener(e -> {
			gridDatos.setContainerDataSource(new BeanItemContainer<>(Ddm.class, ddmManager.findAll(e.getText())));
		});

		final Button botonLimpiar = new Button(FontAwesome.TIMES);
		botonLimpiar.setDescription("Limpiar filtro");
		botonLimpiar.addClickListener(e -> {
			textoFiltro.clear();
			refrescaListaDatos();
		});

		final Button botonPDF = new Button("PDF Ddm´s Detalle");
		botonPDF.setIcon(FontAwesome.FILE_PDF_O);
		botonPDF.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		botonPDF.setDescription("PDF Ddm´s Detalle");
		botonPDF.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				listaDatosToPDF();
			}
		});
		
		final Button botonExcel = new Button("XLS Ddm´s Detalle");
		botonExcel.setIcon(FontAwesome.FILE_EXCEL_O);
		botonExcel.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		botonExcel.setDescription("XLS Ddm´s Detalle");
		botonExcel.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				listaDatosToXLS();
			}
		});

		final Button botonXML = new Button(FontAwesome.FILE_CODE_O);
		botonXML.setDescription("Ddm´s a XML");
		botonXML.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				listaDatosToXML();
			}
		});
		
		final Button botonTXT = new Button(FontAwesome.FILE_TEXT);
		botonTXT.setDescription("Ddm´s a TXT");
		botonTXT.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				listaDatosToTXT();
			}
		});

		final CssLayout filtros = new CssLayout();
		filtros.addComponents(textoFiltro, botonLimpiar, botonPDF, botonExcel, botonXML, botonTXT);
		filtros.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		configuraTablaDatos();

		final VerticalLayout main = new VerticalLayout(filtros, gridDatos);
		main.setSizeFull();

		refrescaListaDatos();

		vlPrincipal.addComponents(main);

		setSpacing(true);
		addComponent(vlPrincipal);

		subWindow.close();

		formularioDdm.setVisible(false);
		gridDatos.addSelectionListener(event -> {
			if (event.getSelected().isEmpty()) {
				cierraVentana();
			} else {
				final Ddm ddmAux = (Ddm) event.getSelected().iterator().next();
				cierraVentana();
				formularioDdm.setDdm(ddmAux);
				subWindow.setWidth(1000, Unit.PIXELS);
				subWindow.setContent(formularioDdm);
				subWindow.center();
				((UI) this.padre).addWindow(subWindow);
			}
		});

	}

	private void configuraTablaDatos() {
		gridDatos.setCaption("Ddm´s");
		gridDatos.setSelectionMode(SelectionMode.SINGLE);

		gridDatos.addColumn("nombre", String.class);
		gridDatos.addColumn("db", Long.class);
		gridDatos.addColumn("fnr", Long.class);
		gridDatos.addColumn("descripcion", String.class);

		gridDatos.getColumn("nombre").setMaximumWidth(300);
		gridDatos.getColumn("db").setMaximumWidth(60);
		gridDatos.getColumn("fnr").setMaximumWidth(70);
		gridDatos.getColumn("descripcion").setMaximumWidth(500);

		gridDatos.setColumnOrder("nombre", "db", "fnr", "descripcion");
		gridDatos.setColumnReorderingAllowed(true); // Permite mover las
													// columnas
		gridDatos.setSizeFull();

	}

	private void refrescaListaDatos() {
		final List<Ddm> listaDdms = ddmManager.findAll(textoFiltro.getValue());
		gridDatos.setContainerDataSource(new BeanItemContainer<>(Ddm.class, listaDdms));
		subWindow.close();
	}

	private void listaDatosToPDF() {
		generadorInformes.creaPDF(plantillaJasper, "Lista Ddm´s", parametrosPlantilla, new JRBeanCollectionDataSource((List<Ddm>) gridDatos.getContainerDataSource().getItemIds()), nombreExport + Constantes.formatoFechayyyyMMddHmmssSSS.format(new Date()));
	}
	
	private void listaDatosToXLS() {
		generadorInformes.creaXLS(plantillaJasper, "Lista Ddm´s", parametrosPlantilla, new JRBeanCollectionDataSource((List<Ddm>) gridDatos.getContainerDataSource().getItemIds()), nombreExport + Constantes.formatoFechayyyyMMddHmmssSSS.format(new Date()));
	}

	private void listaDatosToXML() {
		Source source = new DOMSource(
				generadorXML.listaDdmToXML((List<Ddm>) gridDatos.getContainerDataSource().getItemIds(), "Ddm"));
		Result result = new StreamResult(new java.io.File(
				Constantes.PATH_TEMP + "Ddm" + Constantes.formatoFechayyyyMMddHmmssSSS.format(new Date()) + ".xml")); // nombre
																														// del
																														// archivo
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "listaDdmToXML " + e.toString());
		}
	}
	
	private void listaDatosToTXT() {
		String pathDirectorio = Constantes.PATH_TEMP + Constantes.formatoFechayyyyMMddHmmssSSS.format(new Date());
		Utiles.preparaDirectorio(pathDirectorio);
		
		ficheroManager.generaFicheroDdm(pathDirectorio + "\\",(List<Ddm>) gridDatos.getContainerDataSource().getItemIds());
	}
}
