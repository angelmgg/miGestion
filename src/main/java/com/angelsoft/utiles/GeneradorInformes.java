package com.angelsoft.utiles;

import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.ctes.Constantes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@SuppressWarnings("serial")
public class GeneradorInformes implements Serializable {
	protected static Logger logger;
	{
		logger = Logger.getLogger(this.getClass().getName());
	}

    public GeneradorInformes() {
    }

    public void creaPDF(String pathPlantilla, String titulo, final Map<String, Object> parametros, final JRBeanCollectionDataSource coleccion, String reportFileName){
    	parametros.put("titulo", titulo);
		parametros.put("logoIzquierda", getClass().getResource(Constantes.PATH_LOGOS + Constantes.LOGO_GOB).getPath());
		parametros.put("logoDerecha", getClass().getResource(Constantes.PATH_LOGOS + Constantes.LOGO_APP).getPath());
		
		try {
			final JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResource(Constantes.PATH_REPORTS + pathPlantilla + Constantes.EXTENSION_PLANTILLAS_PDF).getPath(), parametros, coleccion);
			
			if (jasperPrint != null) {
				final JRPdfExporter exporter = new JRPdfExporter();
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(Constantes.PATH_TEMP + reportFileName + Constantes.EXTENSION_PDF));
				exporter.exportReport();
			}
		} catch (JRException ex) {
         	logger.log(Level.SEVERE, "creaPDF " + ex.toString());
        }
    }
    
    public void creaXLS(String pathPlantilla, String titulo, final Map<String, Object> parametros, final JRBeanCollectionDataSource coleccion, String reportFileName){
    	parametros.put("titulo", titulo);
		parametros.put("logoIzquierda", getClass().getResource(Constantes.PATH_LOGOS + Constantes.LOGO_GOB).getPath());
		parametros.put("logoDerecha", getClass().getResource(Constantes.PATH_LOGOS + Constantes.LOGO_APP).getPath());
		
		try {
			final JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResource(Constantes.PATH_REPORTS + pathPlantilla + Constantes.EXTENSION_PLANTILLAS_EXCEL).getPath(), parametros, coleccion);
			
			if (jasperPrint != null) {
				final JRXlsExporter exporter = new JRXlsExporter();
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(Constantes.PATH_TEMP + reportFileName + Constantes.EXTENSION_EXCEL));

				final SimpleXlsReportConfiguration configuracion = new SimpleXlsReportConfiguration();
				configuracion.setOnePagePerSheet(true);
				configuracion.setRemoveEmptySpaceBetweenRows(true);
				configuracion.setRemoveEmptySpaceBetweenColumns(true);
				configuracion.setDetectCellType(true);
				configuracion.setWhitePageBackground(false);

				exporter.setConfiguration(configuracion);
				exporter.exportReport();
			}
		} catch (JRException ex) {
         	logger.log(Level.SEVERE, "creaPDF " + ex.toString());
        }
    }
       
}
