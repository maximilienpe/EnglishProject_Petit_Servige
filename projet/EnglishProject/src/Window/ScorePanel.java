package Window;

import javax.swing.JLabel;
import javax.swing.JPanel;

import MainSystem.Modele;

public class ScorePanel extends JPanel {

	Modele mainmodele;

	JLabel score;

	public ScorePanel(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.setBounds(0, 0, 600, 100);
		score = new JLabel(mainmodele.getVocabGameGraphicScore() + "/" + mainmodele.getVocabGameGraphicScoreMax());
		this.add(score);
	}

	public void refresh() {
		score.setText((mainmodele.getVocabGameGraphicScore() + "/" + mainmodele.getVocabGameGraphicScoreMax()));
		this.validate();
	}

}
