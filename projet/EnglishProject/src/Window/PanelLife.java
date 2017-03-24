package Window;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelLife extends JPanel {

	// Panels and Layout
	private GridLayout mainGridLayout;
	private ArrayList<JPanel> lifePanels;
	private ArrayList<JLabel> lifeLabel;

	// content
	private int maxLife;
	private int life;
	private Modele modele;

	// images
	private String imageDirPath = "Graphics";
	private ImageIcon heart;
	private ImageIcon deadheart;

	public PanelLife(Modele m) {
		// System.out.println("System of life start");
		// initialize content
		this.modele = m;
		//System.out.println("Number of life : " + this.modele.getVocabGameGraphicMaxLife());
		this.setOpaque(false);
		this.maxLife = this.modele.getVocabGameGraphicMaxLife();
		this.life = this.maxLife;

		this.lifeLabel = new ArrayList<JLabel>();
		this.lifePanels = new ArrayList<JPanel>();

		// initialize Panel and layout
		if (this.maxLife >= 0) {
			this.heart = new ImageIcon(imageDirPath + File.separator + "smallheart2.png");
			this.deadheart = new ImageIcon(imageDirPath + File.separator + "smalldeadheart2.png");
			this.mainGridLayout = new GridLayout(1, this.maxLife);
			for (int i = 0; i < this.maxLife; i++) {
				JPanel lp = new JPanel();
				JLabel ll = new JLabel();
				ll.setOpaque(false);
				lp.setOpaque(false);
				ll.setIcon((Icon) this.heart);
				lp.add(ll);
				this.lifeLabel.add(ll);
				this.lifePanels.add(lp);
				this.add(lp);
			}
		} else {
			this.mainGridLayout = new GridLayout(1, this.maxLife);
			JPanel lp = new JPanel();
			lp.setOpaque(false);
			JLabel ll = new JLabel("INFINI");
			lp.add(ll);
			this.lifeLabel.add(ll);
			this.lifePanels.add(lp);
			this.add(lp);
		}
	}

	public void updateLife() {
		if (this.life > this.modele.getVocabGameGraphicLife()) {
			this.life = this.modele.getVocabGameGraphicLife();
			this.lifeLabel.get(this.life).setIcon(this.deadheart);
			// System.out.println(this.life);
			// this.lifeLabel.get(this.life).setText("");
		}
		if (this.life < this.modele.getVocabGameGraphicLife()) {
			this.lifeLabel.get(this.life).setIcon(this.heart);
			this.life = this.modele.getVocabGameGraphicLife();
			// System.out.println(this.life);
			// this.lifeLabel.get(this.life).setText("");
		}
	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "MainMenuButton.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}