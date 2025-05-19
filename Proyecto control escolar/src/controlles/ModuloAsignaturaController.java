package controlles;

import view.ModuloAsignturaView;

public class ModuloAsignaturaController {
	ModuloAsignturaView mav;
	public ModuloAsignaturaController() {
		mav = new ModuloAsignturaView();
}
	
	public void moduloAsignatura() {
		mav.ModuloAsignatura();
	}

}
