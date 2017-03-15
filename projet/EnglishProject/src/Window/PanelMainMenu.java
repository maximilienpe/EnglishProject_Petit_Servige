package Window;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelMainMenu extends JPanel {

	Modele mainmodele;

	public PanelMainMenu(Modele mainmodele) {
		this.mainmodele = mainmodele;
		ButtonPlay buttonplay = new ButtonPlay(mainmodele);
		this.add(buttonplay, BorderLayout.CENTER);
		ButtonLessons buttonlessons = new ButtonLessons(mainmodele,"Lessons");
		this.add(buttonlessons, BorderLayout.CENTER);
	}

}
