package Window;

import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelMainMenu extends JPanel {

	Modele mainmodele;

	public PanelMainMenu(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.setLayout(null);
		ButtonPlay buttonplay = new ButtonPlay(mainmodele);
		this.add(buttonplay);

	}

}
