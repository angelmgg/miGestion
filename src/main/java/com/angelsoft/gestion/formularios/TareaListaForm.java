package com.angelsoft.gestion.formularios;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.Tarea;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.gestion.manager.TareaManager;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class TareaListaForm extends MisForm {

	{
		logger = Logger.getLogger(this.getClass().getName());
	}

	private final TareaManager tareaManager = TareaManager.getInstance();

	private final TextField textoFiltro = new TextField();

	public TareaListaForm(Object padre) {
		super(padre);
		inicializarElementos();
	}

	public void inicializarElementos() {
		textoFiltro.setInputPrompt("Filtrar por ...:");
		textoFiltro.addTextChangeListener(e -> {
			gridDatos.setContainerDataSource(new BeanItemContainer<>(Tarea.class, tareaManager.findAll(e.getText())));
		});

		final Button botonLimpiar = new Button(FontAwesome.TIMES);
		botonLimpiar.setDescription("Limpiar filtro");
		botonLimpiar.addClickListener(e -> {
			textoFiltro.clear();
			refrescaListaDatos();
		});

		final CssLayout filtros = new CssLayout();
		filtros.addComponents(textoFiltro, botonLimpiar);
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
		gridDatos.setCaption("Tareas");
		gridDatos.setSizeFull();
		gridDatos.setSelectionMode(SelectionMode.SINGLE);

		gridDatos.addColumn("fecha", Date.class);
		gridDatos.addColumn("estado", String.class);
		gridDatos.addColumn("descripcion", String.class);

		gridDatos.getColumn("fecha").setRenderer(new DateRenderer(Constantes.formatoDDMMYYYY));
		gridDatos.getColumn("fecha").setWidth(120);
		gridDatos.getColumn("estado").setWidth(150);
		gridDatos.getColumn("descripcion");

		gridDatos.setColumnOrder("fecha", "estado", "descripcion");
		gridDatos.setColumnReorderingAllowed(true); // Permite mover las
													// columnas
		gridDatos.setSizeFull();

	}

	private void refrescaListaDatos() {
		final List<Tarea> listaTareas = tareaManager.findAll(textoFiltro.getValue());
		gridDatos.setContainerDataSource(new BeanItemContainer<>(Tarea.class, listaTareas));
		gridDatos.setSizeFull();
	}

}
