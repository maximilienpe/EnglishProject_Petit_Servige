package Window;

import javax.swing.JLabel;
import javax.swing.JPanel;

import MainSystem.Modele;

public class ScorePanel extends JPanel {

	Modele mainmodele;

	public ScorePanel(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.add(new JLabel(mainmodele.getVocabGameScore() + "/" + mainmodele.getVocabGameGraphicScoreMax()));
	}

}
