package controlles;

import view.View;

public class Controller {
	View view;
	
	public Controller() {
		view = new View();
	}
	
	public void despliegue() {
		view.login();
	}
}
