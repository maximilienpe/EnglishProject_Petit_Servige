package Window;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelLeft extends JPanel {

	PanelRight right;
	int props;
	Modele mainmodele;
	ArrayList<ButtonLeft> allbuttons;

	private ScorePanel scorepanel;
	private PanelLife lifepanel;

	public PanelLeft(Modele mainmodele, int props, ScorePanel scorepanel, PanelLife lifepanel) {
		this.mainmodele = mainmodele;
		this.props = props / 2;

		this.scorepanel = scorepanel;
		this.lifepanel = lifepanel;

		// this.setBounds(0, 100, 100, 500);

		GridLayout layout = new GridLayout(props, 1);
		layout.setHgap(5);
		this.setLayout(layout);

		allbuttons = new ArrayList<ButtonLeft>();

		for (int i = 0; i < this.props; i++) {
			ButtonLeft k = new ButtonLeft(mainmodele, i, this);
			this.add(k);
			allbuttons.add(k);
		}
	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "vert.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setChoice(Nenuphar choice) {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setChoice(choice);
		}
	}

	public void setAllChoice(PanelAnimation anim) {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setPanelAnim(anim);
		}
	}

	public void setPanelRight(PanelRight right) {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setPanelRight(right);
		}
	}

	public void setButtonsNull() {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setText(null);
		}
	}

	public void setScorePanel(ScorePanel score) {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setScorePanel(score);
		}
	}

	public void setPanelLife(PanelLife lifepanel) {
		for (int i = 0; i < this.allbuttons.size(); i++) {
			allbuttons.get(i).setLifePanel(lifepanel);
		}
	}

	public void setProp(int p) {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setText(mainmodele.getLeftPropositionsVocabGameGraphic(p).get(j));
		}
	}

	public ArrayList<ButtonLeft> getAllLeftButtons() {
		return this.allbuttons;
	}

}
