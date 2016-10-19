package com.angelsoft.gestion.formularios;

import java.util.List;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.InicializacionCodigo;
import com.angelsoft.gestion.manager.InicializacionManager;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class InicializacionListaForm extends MisForm {

	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	private final InicializacionManager inicializacionManager = InicializacionManager.getInstance();
	
	private final TextField textoFiltro = new TextField();
	
	public InicializacionListaForm(UI uiAux) {
		super(uiAux);
		inicializarElementos();
	}

	public void inicializarElementos() {
		textoFiltro.setInputPrompt("Filtrar por ...:");
		textoFiltro.addTextChangeListener(e->{
			gridDatos.setContainerDataSource(new BeanItemContainer<>(InicializacionCodigo.class, inicializacionManager.findAll(e.getText())));
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
		gridDatos.setCaption("InicializacionCodigos");
		gridDatos.setSizeFull();
		gridDatos.setSelectionMode(SelectionMode.SINGLE);

		gridDatos.addColumn("tabla", String.class);
		gridDatos.addColumn("codigo", String.class);
		gridDatos.addColumn("valor", String.class);
				
		gridDatos.getColumn("tabla").setWidth(300);
		gridDatos.getColumn("codigo").setWidth(70);
		gridDatos.getColumn("valor");
		
		gridDatos.setColumnOrder("tabla","codigo", "valor");
		gridDatos.setColumnReorderingAllowed(true); // Permite mover las columnas
		gridDatos.setSizeFull();
	}

	private void refrescaListaDatos() {
		final List<InicializacionCodigo> listaInicializacionCodigos = inicializacionManager.findAll(textoFiltro.getValue());
		gridDatos.setContainerDataSource(new BeanItemContainer<>(InicializacionCodigo.class, listaInicializacionCodigos));
		gridDatos.setSizeFull();
	}

}
