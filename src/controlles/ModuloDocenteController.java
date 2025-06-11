package controlles;

import model.Docente;
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
	public void modificar(Docente docente) {
		mdv.modificar(docente);
	}
	public void credencial(Docente docente) {
		mdv.credencial(docente);
	}
	public void datos(Docente docente) {
		mdv.datos(docente);
	}
}
