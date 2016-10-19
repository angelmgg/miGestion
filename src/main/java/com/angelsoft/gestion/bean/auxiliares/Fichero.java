package com.angelsoft.gestion.bean.auxiliares;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Fichero {

	private String path;
	private File file;
	private FileWriter fileWriter;
	private BufferedWriter bufferdWriter;
	private PrintWriter printerWriter;
	
	public void close() throws IOException{
		this.getPrinterWriter().append("\n");
		this.getPrinterWriter().close();
		this.getBufferdWriter().close();
		this.getFileWriter().close();
	}
	
	public void escribe(String cadena){
		this.getPrinterWriter().println(cadena);
	}
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * @return the fileWriter
	 */
	public FileWriter getFileWriter() {
		return fileWriter;
	}
	/**
	 * @param fileWriter the fileWriter to set
	 */
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	/**
	 * @return the bufferdWriter
	 */
	public BufferedWriter getBufferdWriter() {
		return bufferdWriter;
	}
	/**
	 * @param bufferdWriter the bufferdWriter to set
	 */
	public void setBufferdWriter(BufferedWriter bufferdWriter) {
		this.bufferdWriter = bufferdWriter;
	}
	/**
	 * @return the printerWriter
	 */
	public PrintWriter getPrinterWriter() {
		return printerWriter;
	}
	/**
	 * @param printerWriter the printerWriter to set
	 */
	public void setPrinterWriter(PrintWriter printerWriter) {
		this.printerWriter = printerWriter;
	}

}
