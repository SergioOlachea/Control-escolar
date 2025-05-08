package controlles;

import view.HomeView;

public class HomeController {
	HomeView hv;
	
	public HomeController() {
		hv= new HomeView();
	}
	
	public void home() {
		hv.home();
	}
	
}
