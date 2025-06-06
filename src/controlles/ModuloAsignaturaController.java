package controlles;

import model.Asignatura;
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
	public void datos(Asignatura asignatura) {
		mav.datos(asignatura);
	}

}
