package Window;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "marron.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
