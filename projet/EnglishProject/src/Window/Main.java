package Window;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import MainSystem.Modele;

public class Main {

	public static WindowGame window;

	public static void main(String[] args) {
		Modele mainmodele = new Modele();
		mainmodele.loadTopics();
		window = new WindowGame();
		window.setContentPane(new PanelMainMenu(mainmodele));
		window.validate();
		window.setResizable(true);
		window.setLocationRelativeTo(null);

	}

}
