package controlles;

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
	public void modificar() {
		mev.modificar();
	}
	public void datosGenerales() {
		mev.datos();
	}
	public void credencial() {
		mev.credencial();
	}

}
