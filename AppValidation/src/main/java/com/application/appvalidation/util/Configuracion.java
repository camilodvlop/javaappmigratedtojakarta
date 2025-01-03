package com.application.appvalidation.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author lpena
 */
public class Configuracion {
    
    private final Logger LOGGER = Logger.getRootLogger();
    private String ruta;
    private static Configuracion INSTANCE;
    public Configuracion() {
    }
    
    public static Configuracion getInstance() {
        return INSTANCE = new Configuracion();
    }

    
    /**
     * Lee las propiedades del archivo config.properties
     * @param key
     * @return String
     */
    public String GetProperty (String key){
        try {
            Properties p = new Properties();
            if (getSO().toLowerCase().contains("windows")){
                ruta = "C:\\Pathtoproperties\\configAppValidation.properties";
            }else if (getSO().toLowerCase().contains("linux")){
                ruta = "/etc/glassfish4/glassfish/domains/domain1/config/config.properties";
            }
            FileInputStream fileInput = new FileInputStream(ruta);
            p.load(fileInput);
            return p.getProperty(key);
        } catch (IOException ex) {
            LOGGER.error(ex);
            return "";
        }
    }

     
     private String getSO(){
        return System.getProperty("os.name");
     }

}

