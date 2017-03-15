package Window;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelMainMenu extends JPanel {

	Modele mainmodele;

	public PanelMainMenu(Modele mainmodele) {
		this.setLayout(new GridLayout(5,1));
		this.mainmodele = mainmodele;
		Main.window.setSize(200, 400);
		this.add(new JPanel());
		ButtonPlay buttonplay = new ButtonPlay(mainmodele);
		this.add(buttonplay);
		this.add(new JPanel());
		ButtonLessons buttonlessons = new ButtonLessons(mainmodele,"Lessons");
		this.add(buttonlessons);
		this.add(new JPanel());
	}

}
