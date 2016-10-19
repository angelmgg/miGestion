package gestion.main;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.angelsoft.gestion.bean.Campo;
import com.angelsoft.gestion.bean.Estructura;
import com.angelsoft.gestion.bean.SuperDescriptor;
import com.angelsoft.gestion.bean.auxiliares.InicializacionCodigo;
import com.angelsoft.gestion.bean.auxiliares.Nota;
import com.angelsoft.gestion.bean.auxiliares.Pregunta;
import com.angelsoft.gestion.bean.auxiliares.Respuesta;
import com.angelsoft.gestion.bean.auxiliares.Tarea;
import com.angelsoft.gestion.bean.entidades.Ddm;
import com.angelsoft.gestion.ctes.Constantes;
import com.angelsoft.utiles.GeneraXMLDefiniciones;
import com.angelsoft.utiles.UtilesXMLDefiniciones;
import com.angelsoft.utiles.UtilesXMLInicializacion;
import com.angelsoft.utiles.UtilesXMLNotas;
import com.angelsoft.utiles.UtilesXMLPreguntas;
import com.angelsoft.utiles.UtilesXMLTareas;

public class Pruebas {

	public static String rutaRecursos = System.getProperty("user.dir") + "\\src\\main\\java\\com\\angelsoft\\recursos\\";
	public static GeneraXMLDefiniciones generaXML  = new GeneraXMLDefiniciones();
	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public static void main(String[] args) {

//		leeXMLDefinicion();
//		leeXMLInicialicacion();
//		leeXMLTareas();
//		leeXMLNotas();
//		leeXMLPreguntas();clon
	}

	public static void leeXMLDefinicion() {
		List<Ddm> lista = null;

		try {
			String ruta = rutaRecursos + Constantes.XML_DEFINICIONES;
			lista = UtilesXMLDefiniciones.leeXMLDdms(ruta);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "leeXMLDefinicion " + e.toString());
		}
		
		sacaDdms(lista);
//		listaDdmToXML(lista);
		
	}

	
	public static void sacaDetalleDefinicion(List<Object> listaObjeto){
		for(Object objetoAux: listaObjeto){
			System.out.println(objetoAux);
			
			if (objetoAux instanceof Estructura){
				sacaDetalleDefinicion(((Estructura) objetoAux).getListaDetalle());
			} else if (objetoAux instanceof SuperDescriptor){
				sacaDetalleDefinicion(((SuperDescriptor) objetoAux).getListaDetalle());
			} else if (objetoAux instanceof Campo){
				sacaDetalleDefinicion(((Campo) objetoAux).getRedefine());
			}
		}
	}
	
	public static void leeXMLInicialicacion() {
		List<InicializacionCodigo> lista = null;

		try {
			String ruta = rutaRecursos + Constantes.XML_INICIALICACIONES;
			lista = UtilesXMLInicializacion.leeXMLInicializaciones(ruta);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "leeXMLInicialicacion " + e.toString());
		}
		
		for(InicializacionCodigo codigoAux: lista){
			System.out.println(codigoAux);
		}
	}
	
	public static void leeXMLTareas() {
		List<Tarea> lista = null;

		try {
			String ruta = rutaRecursos + Constantes.XML_TAREAS;
			lista = UtilesXMLTareas.leeXMLTareas(ruta);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "leeXMLTareas " + e.toString());
		}
		
		for(Tarea tareaAux: lista){
			System.out.println(tareaAux);
		}
	}
	
	public static void leeXMLNotas() {
		List<Nota> lista = null;

		try {
			String ruta = rutaRecursos + Constantes.XML_NOTAS;
			lista = UtilesXMLNotas.leeXMLNotas(ruta);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "leeXMLNotas " + e.toString());
		}
		
		for(Nota notaAux: lista){
			System.out.println(notaAux);
		}
	}
	
	public static void leeXMLPreguntas() {
		List<Pregunta> lista = null;

		try {
			String ruta = rutaRecursos + Constantes.XML_PREGUNTAS;
			lista = UtilesXMLPreguntas.leeXMLPreguntas(ruta);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "leeXMLPreguntas " + e.toString());
		}
		
		sacaPreguntas(lista, " ");
		
	}
	
	private static void sacaDdms(List<Ddm> lista) {
		for(Ddm ddmAux: lista){
			System.out.println(ddmAux);
			sacaDetalleDefinicion(ddmAux.getListaDetalle());
			System.out.println("-------------------------------------------");
			System.out.println();
		}
	}
	
	public static void sacaPreguntas(List<Pregunta> listaPreguntas, String prefijo){
		for(Pregunta preguntaAux: listaPreguntas){
			System.out.println(prefijo + "P: " + preguntaAux);
			sacaRespuestas(preguntaAux.getListaRespuestas(), prefijo + " ");
		}
	}

	public static void sacaRespuestas(List<Respuesta> listaRespuestas, String prefijo){
		for(Respuesta respuestaAux: listaRespuestas){
			System.out.println(prefijo + ">" + respuestaAux);
			sacaPreguntas(respuestaAux.getListaPreguntas(), prefijo + "  ");
			sacaNotas(respuestaAux.getListaNotas(), prefijo + " ");
		}
	}
	
	public static void sacaNotas(List<Nota> listaNotas, String prefijo){
		for(Nota notaAux: listaNotas){
			System.out.println(prefijo + " NOTA: " + notaAux);
		}
	}
	
	public static void listaDdmToXML(List<Ddm> lista){
		//Generate XML
        Source source = new DOMSource(generaXML.listaDdmToXML(lista, "ddm"));
        //Indicamos donde lo queremos almacenar
        Result result = new StreamResult(new java.io.File(Constantes.PATH_TEMP + "ddm.xml")); //nombre del archivo
        Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "listaDdmToXML " + e.toString());
		}
	}
		
}
