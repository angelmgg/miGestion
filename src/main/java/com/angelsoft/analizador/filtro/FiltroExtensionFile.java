package com.angelsoft.analizador.filtro;

import java.io.File;
import java.io.FilenameFilter;

public class FiltroExtensionFile implements FilenameFilter{
    String extension;
    
    public FiltroExtensionFile(String extension){
        this.extension=extension;
    }
    public boolean accept(File dir, String name){
        return name.endsWith(extension);
    }
}