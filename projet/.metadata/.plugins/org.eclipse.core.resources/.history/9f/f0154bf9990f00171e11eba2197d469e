package Window;

import javax.swing.JFrame;

import MainSystem.Modele;

public class Main {

	public static JFrame window;

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
