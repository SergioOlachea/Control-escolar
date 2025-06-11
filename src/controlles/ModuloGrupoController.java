package controlles;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import model.Asignatura;
import model.Docente;
import model.Estudiante;
import model.Grupo;
import view.ModuloGrupoView;

public class ModuloGrupoController {
	ModuloGrupoView mgv;
		
	public void moduloGrupo() {
		mgv = new ModuloGrupoView();
		mgv.moduloGrupo();
	}
	public void crear() {
		mgv = new ModuloGrupoView();
		mgv.crear();
	}
	public void modificar(Grupo grupo) {
		mgv = new ModuloGrupoView();
		mgv.modificar(grupo);
	}
	public void datosGenerales(Grupo grupo) {
		mgv = new ModuloGrupoView();
		mgv.detalles(grupo);
	}
	
	public void datosGenerales(Estudiante estudiante) {
		mgv = new ModuloGrupoView();
		mgv.detallesE(estudiante);
	}
	public void datosGenerales(Docente docente) {
		mgv = new ModuloGrupoView();
		mgv.detallesD(docente);
	}
	public void datosGenerales(Asignatura asignatura,Grupo grupo) {
		mgv = new ModuloGrupoView();
		mgv.detallesA(asignatura, grupo);
	}
}
