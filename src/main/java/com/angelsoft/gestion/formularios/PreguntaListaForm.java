package com.angelsoft.gestion.formularios;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.Pregunta;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.gestion.manager.PreguntaManager;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.TextRenderer;
import com.vaadin.ui.themes.ValoTheme;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings("serial")
public class PreguntaListaForm extends MisForm {

	{
		logger = Logger.getLogger(this.getClass().getName());
		nombreExport = "Preguntas";
		plantillaJasper = "Pregunta";
		subWindow = new Window("Detalle Pregunta");
	}

	private final PreguntaManager preguntaManager = PreguntaManager.getInstance();
	private final TextField textoFiltro = new TextField();

	public PreguntaListaForm(Object padre) {
		super(padre);
		inicializarElementos();
	}

	public void inicializarElementos() {
		textoFiltro.setInputPrompt("Filtrar por ...:");
		textoFiltro.addTextChangeListener(e -> {
			gridDatos.setContainerDataSource(
					new BeanItemContainer<>(Pregunta.class, preguntaManager.findAll(e.getText())));
		});

		final Button botonLimpiar = new Button(FontAwesome.TIMES);
		botonLimpiar.setDescription("Limpiar filtro");
		botonLimpiar.addClickListener(e -> {
			textoFiltro.clear();
			refrescaListaDatos();
		});

		final Button botonInformeDet = new Button("PDF Preguntas");
		botonInformeDet.setIcon(FontAwesome.FILE_PDF_O);
		botonInformeDet.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		botonInformeDet.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				listaDatosToPDF();
			}
		});

		final CssLayout filtros = new CssLayout();
		filtros.addComponents(textoFiltro, botonLimpiar, botonInformeDet);
		filtros.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		configuraTablaDatos();

		final VerticalLayout main = new VerticalLayout(filtros, gridDatos);
		main.setSizeFull();

		refrescaListaDatos();

		vlPrincipal.addComponents(main);

		setSpacing(true);
		addComponent(vlPrincipal);
	}

	private void configuraTablaDatos() {
		gridDatos.setCaption("Preguntas");
		gridDatos.setSizeFull();
		gridDatos.setSelectionMode(SelectionMode.SINGLE);

		gridDatos.addColumn("fecha", Date.class);
		gridDatos.addColumn("tipo", String.class);
		gridDatos.addColumn("texto", String.class);

		gridDatos.getColumn("fecha").setRenderer(new DateRenderer(Constantes.formatoDDMMYYYY));
		gridDatos.getColumn("fecha").setWidth(120);
		gridDatos.getColumn("tipo").setWidth(120);
		gridDatos.getColumn("texto").setWidthUndefined();
		gridDatos.getColumn("texto").setRenderer(new TextRenderer());

		gridDatos.setColumnOrder("fecha", "tipo", "texto");
		gridDatos.setColumnReorderingAllowed(true); // Permite mover las
													// columnas
		gridDatos.setSizeFull();

	}

	private void refrescaListaDatos() {
		final List<Pregunta> listaPreguntas = preguntaManager.findAll(textoFiltro.getValue());
		gridDatos.setContainerDataSource(new BeanItemContainer<>(Pregunta.class, listaPreguntas));
		gridDatos.setSizeFull();
	}

	private void listaDatosToPDF() {
		generadorInformes.creaPDF(plantillaJasper, "Lista Preguntas", parametrosPlantilla, new JRBeanCollectionDataSource((List<Pregunta>) gridDatos.getContainerDataSource().getItemIds()), nombreExport + Constantes.formatoFechayyyyMMddHmmssSSS.format(new Date()));
	}

}
