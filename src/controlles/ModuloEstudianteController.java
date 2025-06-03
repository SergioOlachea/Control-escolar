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
	public void datosGenerales(Estudiante estudiante) {
		mev.datos(estudiante);
	}
	public void credencial(Estudiante estudiante) {
		mev.credencial(estudiante);
	}

}
