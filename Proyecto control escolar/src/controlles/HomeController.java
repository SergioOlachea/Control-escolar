package controlles;

import view.HomeView;
import view.ModuloEstudianteView;

public class HomeController {
	HomeView hv;
	ModuloEstudianteView me;
	
	public HomeController() {
		hv= new HomeView();
		me = new ModuloEstudianteView();
	}
	
	public void home() {
		hv.home();
	}
	public void moduloAlumnos() {
		me.moduloAlumnos();
	}
	
}
