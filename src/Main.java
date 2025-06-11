import java.awt.Color;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controlles.Controller;

public class Main {

	public static void main(String[] args) {
		FlatLightLaf.setup();
		UIManager.put("Table.showVerticalLines", true);
		UIManager.put("Table.showHorizontalLines", true);
		UIManager.put("Table.gridColor", Color.LIGHT_GRAY);
		UIManager.put("TableHeader.background", Color.LIGHT_GRAY);
		Controller control = new Controller();
		control.despliegue();
	}

}
