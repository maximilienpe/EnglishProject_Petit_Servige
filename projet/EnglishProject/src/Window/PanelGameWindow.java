package Window;

import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelGameWindow extends JPanel {

	Modele mainmodele;

	int nbprops;

	public PanelGameWindow(Modele mainmodele, int nbprops) {
		this.nbprops = nbprops;
		this.mainmodele = mainmodele;
		Main.window.setSize(600, 600);

		this.setLayout(null);

		ScorePanel score = new ScorePanel(mainmodele);

		PanelLeft left = new PanelLeft(mainmodele, this.nbprops);
		PanelRight right = new PanelRight(mainmodele, this.nbprops);

		left.setPanelRight(right);
		right.setPanelLeft(left);

		left.setScorePanel(score);
		right.setScorePanel(score);

		score.refresh();

		this.add(score);

		this.add(left);
		this.add(right);

		this.add(new PanelAnimation(mainmodele, left, right, score));
	}

}
