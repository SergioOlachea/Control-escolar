package controlles;

import view.ModuloDocenteView;

public class ModuloDocenteController {
	ModuloDocenteView mdv;
	
	public ModuloDocenteController() {
		mdv = new ModuloDocenteView();
	}
	
	public void moduloDocente() {
		mdv.moduloDocente();
	}
	public void crear() {
		mdv.crear();
	}
	public void modificar() {
		mdv.modificar();
	}
	public void credencial() {
		mdv.credencial();
	}
	public void datos() {
		mdv.datos();
	}
}
