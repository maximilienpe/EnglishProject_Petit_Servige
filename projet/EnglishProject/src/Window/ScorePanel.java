package Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainSystem.Modele;

public class ScorePanel extends JPanel {

	private Modele mainmodele;

	private JLabel score;

	public ScorePanel(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.setBounds(0, 0, 600, 100);
		this.score = new JLabel((mainmodele.getVocabGameGraphicScore() + "/" + mainmodele.getVocabGameGraphicScoreMax()));
		//this.add(score);
	}

	public void refresh() {
		score.setText((mainmodele.getVocabGameGraphicScore() + "/" + mainmodele.getVocabGameGraphicScoreMax()));
		this.validate();
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "MainMenuButton.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			
			Font f = new Font("default",Font.BOLD, 18);
			g.setColor(Color.black);
			drawCenteredText(g, mainmodele.getVocabGameGraphicScore() + "/" + mainmodele.getVocabGameGraphicScoreMax(), f, this.getWidth()/2, this.getHeight()/2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawCenteredText(Graphics g, String text, Font font, int centerX, int centerY) {
		FontMetrics metric = g.getFontMetrics(font);
		int x = centerX - metric.stringWidth(text)/2 ;
		int y = centerY - metric.getHeight()/2;
		g.setFont(font);
		g.drawString(text, x, y+15);
	}

}
