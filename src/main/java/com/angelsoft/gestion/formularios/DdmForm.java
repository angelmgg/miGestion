package com.angelsoft.gestion.formularios;

import com.angelsoft.gestion.bean.Campo;
import com.angelsoft.gestion.bean.entidades.Ddm;
import com.angelsoft.gestion.ctes.Cadena;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DdmForm extends MisForm {

	private TextField nombre;
	private TextField descripcion;
	private TextField db;
	private TextField fnr;
	private Button botonSalvar;
	private Button botonBorrar;

	private Ddm ddm;

	public DdmForm(Object padre) {
		super(padre);

		preparaForm();

		botonSalvar.setClickShortcut(KeyCode.ENTER);
		botonSalvar.addClickListener(e -> this.save());
		botonBorrar.addClickListener(e -> this.delete());
	}

	public void setDdm(Ddm ddm) {
		this.ddm = ddm;
		BeanFieldGroup.bindFieldsUnbuffered(ddm, this);

		gridDatos.setContainerDataSource(new BeanItemContainer<>(Campo.class, ddm.getListaCampos()));

		// Show delete button for only customers already in the database
		botonBorrar.setVisible(ddm.isPersisted());
		setVisible(true);

	}

	private void preparaForm() {
		nombre = new TextField(Cadena.NOMBRE);
		nombre.setInputPrompt(Cadena.NOMBRE);
		nombre.setWidth(300, Unit.PIXELS);

		db = new TextField(Cadena.DB);
		db.setInputPrompt(Cadena.DB);
		db.setWidth(70, Unit.PIXELS);

		fnr = new TextField(Cadena.FNR);
		fnr.setInputPrompt(Cadena.FNR);
		fnr.setWidth(70, Unit.PIXELS);

		descripcion = new TextField(Cadena.DESCRIPCION);
		descripcion.setInputPrompt(Cadena.DESCRIPCION);
		descripcion.setSizeFull();

		botonSalvar = new Button(Cadena.BT_SALVAR);
		botonBorrar = new Button(Cadena.BT_BORRAR);

		final HorizontalLayout hLayout1 = new HorizontalLayout(nombre, db, fnr);
		hLayout1.setSpacing(true);

		botonera.addComponent(botonSalvar);
		botonera.addComponent(botonBorrar);

		final VerticalLayout vLayout1 = new VerticalLayout(hLayout1, descripcion, gridDatos, botonera);
		vLayout1.setSpacing(true);

		addComponents(vLayout1);

		configuraTablaDatos();
	}

	private void configuraTablaDatos() {
		gridDatos.setCaption(Cadena.CAMPOS);
		gridDatos.setSelectionMode(SelectionMode.SINGLE);

		gridDatos.addColumn("nivel", Integer.class);
		gridDatos.addColumn("nombreCorto", String.class);
		gridDatos.addColumn("nombre", String.class);
		gridDatos.addColumn("tipo", String.class);
		gridDatos.addColumn("longitud", Long.class);
		gridDatos.addColumn("descripcion", String.class);

		gridDatos.getColumn("nivel").setMaximumWidth(55);
		gridDatos.getColumn("nombreCorto").setMaximumWidth(60);
		gridDatos.getColumn("nombre").setMaximumWidth(300);
		gridDatos.getColumn("tipo").setMaximumWidth(60);
		gridDatos.getColumn("longitud").setMaximumWidth(70);
		gridDatos.getColumn("descripcion").setMaximumWidth(500);

		gridDatos.setColumnOrder("nivel", "nombreCorto");
		gridDatos.setColumnReorderingAllowed(true);

		gridDatos.setSizeFull();

	}

	private void delete() {
		// personaService.delete(ddm);
		// uiAux.updateListPersonas();
		((MisForm)padre).cierraVentana();
	}

	private void save() {
		// personaService.save(ddm);
		// uiAux.updateListPersonas();
		((MisForm)padre).cierraVentana();
	}
}
