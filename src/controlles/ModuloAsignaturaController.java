package controlles;

import model.Asignatura;
import model.Grupo;
import view.ModuloAsignturaView;

public class ModuloAsignaturaController {
	ModuloAsignturaView mav;
	public ModuloAsignaturaController() {
		mav = new ModuloAsignturaView();
}
	
	public void moduloAsignatura() {
		mav.ModuloAsignatura();
	}
	public void crear() {
		mav.crear();
	}
	public void modificar(Asignatura asignatura) {
		mav.modificar(asignatura);
	}
	public void datos(Asignatura asignatura, Grupo grupo) {
		mav.datos(asignatura, grupo);
	}

}
