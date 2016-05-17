package com.ungs.pp2.scrPP2.Plugins;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.ungs.pp2.scrPP2.Controller.UserStoryHelper;
import com.ungs.pp2.scrPP2.Dominio.Interfaz.IExporter;

public class ExportToText implements IExporter {
	private String rutaTxt;
	private String extension = ".txt"; 
	
	@Override
	public void export(String ruta, List<UserStoryHelper> userStoriesHlpr) {
		this.rutaTxt = ruta + extension;
		if (userStoriesHlpr.size()==0) throw new RuntimeException("No existen historias de usuario para exportar.");
//		// TODO Auto-generated method stub
		try {

			String content = userStoriesHlpr.toString();

			File file = new File(rutaTxt);

			//si no existe el archivo, lo creo
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();	

		} catch (IOException e) {
			e.printStackTrace();
		//}
	}
	
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exportar a texto";
	}
}
