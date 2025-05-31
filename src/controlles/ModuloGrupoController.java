package controlles;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import model.Estudiante;
import view.ModuloGrupoView;

public class ModuloGrupoController {
	ModuloGrupoView mgv = new ModuloGrupoView();
		
	public void moduloGrupo() {
		mgv.moduloGrupo();
	}
	public void crear() {
		mgv.crear();
	}
	public void modificar() {
		mgv.modificar();
	}
	public void datos() {
		mgv.detalles();
	}
	public void addDocente() {
		mgv.addDocente();
	}
	public void AddEstudiante() {
		mgv.addAlumno();
	}
	public void addAsignatura() {
		mgv.addAsignatura();
	}
	public void datosGenerales(Estudiante estudiante) {
		mgv.datoEstudiante(estudiante);
	}
	public void datosGeneralesd(Estudiante estudiante/*Docente docente*/) {
		mgv.datosDocente(estudiante/*docente*/);
	}
		/*public void datosGenerales(Asignatura asignatura) {
		mgv.datoAsignatura(asignatura);
	}*/


}
