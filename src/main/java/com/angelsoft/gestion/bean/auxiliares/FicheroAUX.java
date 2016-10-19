package com.angelsoft.gestion.bean.auxiliares;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FicheroAUX extends Fichero {

	/**
	 * @param path
	 * @throws IOException 
	 */
	public FicheroAUX(String path, String extension) throws IOException {
		super();
		this.setPath(path);
		this.setFile(new File(this.getPath() + extension));
		this.setFileWriter(new FileWriter(this.getFile()));
		this.setBufferdWriter(new BufferedWriter(this.getFileWriter()));
		this.setPrinterWriter(new PrintWriter(this.getBufferdWriter()));
		
	}

	public void close() throws IOException{
		super.close();
	}
	
}
