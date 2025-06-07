package view;

import java.util.ArrayList;

import model.Estudiante;

public class alumnosAñadidos {
	private ArrayList<Estudiante> estudiantesAñadidos;
	
	public alumnosAñadidos() {
		
	}
	public alumnosAñadidos(ArrayList <Estudiante> e) {
		estudiantesAñadidos=e;
	}
	public void addEstudiantes(Estudiante estudiante) {
		estudiantesAñadidos.add(estudiante);
	}
	public ArrayList<Estudiante> listas(){
		return estudiantesAñadidos;
	}
}
