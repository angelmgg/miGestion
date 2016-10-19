package com.angelsoft.gestion.formularios;

import java.util.List;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.Nota;
import com.angelsoft.gestion.manager.NotaManager;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class NotaListaForm extends MisForm {

	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	private final NotaManager notaManager = NotaManager.getInstance();
	
	private final TextField textoFiltro = new TextField();
	
	public NotaListaForm(Object padre) {
		super(padre);
		inicializarElementos();
	}

	public void inicializarElementos() {
		textoFiltro.setInputPrompt("Filtrar por ...:");
		textoFiltro.addTextChangeListener(e->{
			gridDatos.setContainerDataSource(new BeanItemContainer<>(Nota.class, notaManager.findAll(e.getText())));
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
		gridDatos.setCaption("Notas");
		gridDatos.setSizeFull();
		gridDatos.setSelectionMode(SelectionMode.SINGLE);

		gridDatos.addColumn("entidad", String.class);
		gridDatos.addColumn("texto", String.class);
				
		gridDatos.getColumn("entidad").setWidth(150);
		gridDatos.getColumn("texto");
		
		gridDatos.setColumnOrder("entidad","texto");
		gridDatos.setColumnReorderingAllowed(true); // Permite mover las columnas
		gridDatos.setSizeFull();
	}

	private void refrescaListaDatos() {
		final List<Nota> listaNotas = notaManager.findAll(textoFiltro.getValue());
		gridDatos.setContainerDataSource(new BeanItemContainer<>(Nota.class, listaNotas));
		gridDatos.setSizeFull();
	}

}
