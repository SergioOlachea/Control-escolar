package controlles;

import model.Estudiante;
import view.ModuloEstudianteView;

public class ModuloEstudianteController {
	ModuloEstudianteView mev;
	
	public ModuloEstudianteController(){
		mev = new ModuloEstudianteView();
	}
	
	public void ModuloEstudiante() {
		mev.moduloAlumnos();
	}
	public void crarEstudiante() {
		mev.crear();
	}
	public void modificar(Estudiante estudiante) {
		mev.modificar(estudiante);
	}
	public void datosGenerales() {
		mev.datos();
	}
	public void credencial() {
		mev.credencial();
	}

}
