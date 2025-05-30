package controlles;

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
		//mgv.addDocente();
	}
	public void AddEstudiante() {
		mgv.addAlumno();
	}
	public void addAsignatura() {
		mgv.detalles();
	}


}
