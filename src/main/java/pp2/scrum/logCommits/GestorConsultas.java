package pp2.scrum.logCommits;

import java.util.ArrayList;

import pp2.scrum.dominio.entidad.Tarea;

public class GestorConsultas {
	ArrayList<Tarea> tareas; 
	
	public GestorConsultas(){
		tareas=new ArrayList<Tarea>();
	}
	
	public Tarea getTarea(String id){
		return null;
	}
	
	public void guardarModificacionTarea(Tarea tarea){
		tareas.add(tarea);
	}
	public ArrayList<Tarea> getTarea(){
		return tareas;
	}
}