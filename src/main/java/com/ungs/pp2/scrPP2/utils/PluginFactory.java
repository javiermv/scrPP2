package com.ungs.pp2.scrPP2.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ungs.pp2.scrPP2.Dominio.Interfaz.IExporter;


public class PluginFactory
{
	private static Properties propiedades = new Properties();
	private static String nombrePaquete = "";
	private static List<IExporter> plugins;
	 
	
	static {

			try {
				//Cargo del archivo el nombre del paquete donde están los plugins.
				propiedades.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/propertiesPluginPackage"));
				nombrePaquete = propiedades.getProperty("paquetePlugins").trim();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public static List<IExporter> getPlugins()
	{
		plugins = new ArrayList<>();
		try
		{
		    File directorio = null;
		    		    
		    String rutaRelPaquete = nombrePaquete.replace('.', '/'); //->com.ungs.pp2.scrPP2.Plugins

		    URL resource = ClassLoader.getSystemClassLoader().getResource(rutaRelPaquete); //->file:/C:/Users/W7/git/PP2/target/classes/com/ungs/pp2/scrPP2/Plugins

		    directorio = new File(resource.toURI()); 
		    
		    if (directorio != null && directorio.exists()) {
		    	
		        //Obtiene lista de archivos que hay en el paquete
		        String[] archivos = directorio.list(); //-> [ExportToExcel.class, ExportToText.class]
		        
		        for (String archivo : archivos)
		        	if (archivo.endsWith(".class"))
		        	{
		        		//Borra extensión .class
		                String className = nombrePaquete + '.' + archivo.substring(0, archivo.length() - 6);

		                try {
		                	Object objTemp = Class.forName(className).newInstance();

		                	if (objTemp instanceof IExporter)
		                		plugins.add((IExporter) objTemp);
		                    
		                } catch (ClassNotFoundException e) {
		                    throw new RuntimeException("Error al cargar la clase: " + className);
		                }
		        	}
		    }
		    return plugins;
		}
		catch(Exception e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}
	
//	public static Object getPlugin(Class iface) 
//	{
//		//Busca el nombre de la implementacion
//		String implName = propiedades.getProperty(iface.getName());
//		if (implName == null) {
//			throw new RuntimeException("implementation not specified for " + iface.getName() + " in PluginFactory propeties.");
//		}
//		try {
//			//retorna nueva instancia de la implementacion de la clase
//			return Class.forName(implName).newInstance();
//		} catch (Exception ex) {
//			throw new RuntimeException("factory unable to construct instance of " + iface.getName());
//		}
//	}
}