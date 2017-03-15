package Window;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelGameWindow extends JPanel {

	Modele mainmodele;

	int nbprops;

	public PanelGameWindow(Modele mainmodele, int nbprops) {
		this.nbprops = nbprops;
		this.mainmodele = mainmodele;
		Main.window.setSize(600, 600);

		this.setLayout(new BorderLayout());

		PanelLeft left = new PanelLeft(mainmodele, this.nbprops);
		PanelRight right = new PanelRight(mainmodele, this.nbprops);

		left.setPanelRight(right);
		right.setPanelLeft(left);

		this.add(new ScorePanel(mainmodele), BorderLayout.NORTH);

		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);

		this.add(new PanelAnimation(mainmodele, left, right));
	}

}
