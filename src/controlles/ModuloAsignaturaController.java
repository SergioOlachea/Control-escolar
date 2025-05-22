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
	public void crear() {
		mav.crear();
	}
	public void modificar() {
		mav.modificar();
	}
	public void datos() {
		mav.datos();
	}

}
