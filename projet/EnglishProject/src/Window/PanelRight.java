package Window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelRight extends JPanel {

	Nenuphar choice;
	PanelLeft left;
	int props;
	Modele mainmodele;
	ArrayList<ButtonRight> allbuttons;

	private ScorePanel scorepanel;
	private PanelLife lifepanel;

	public PanelRight(Modele mainmodele, int props, ScorePanel scorepanel, PanelLife lifepanel) {
		this.mainmodele = mainmodele;
		this.props = props / 2;

		this.scorepanel = scorepanel;
		this.lifepanel = lifepanel;

		// this.setBounds(500, 100, 100, 500);
		this.setPreferredSize(new Dimension(200, 400));

		GridLayout layout = new GridLayout(props + 2, 1);
		layout.setHgap(2);
		this.setLayout(layout);

		JPanel invi = new JPanel();
		invi.setOpaque(false);
		this.add(invi);

		allbuttons = new ArrayList<ButtonRight>();

		for (int i = 0; i < this.props; i++) {
			ButtonRight k = new ButtonRight(mainmodele, i, this);
			this.add(k);
			allbuttons.add(k);
		}

		JPanel invi2 = new JPanel();
		invi2.setOpaque(false);
		this.add(invi2);
	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "PanelGame2.jpg"));
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

	public void setPanelLeft(PanelLeft left) {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setPanelLeft(left);
		}
	}

	public void setButtonsNull() {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setText(null);
		}
	}

	public void setProp(int p) {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setText(mainmodele.getRightPropositionsVocabGameGraphic(p).get(j));
		}
	}

	public void setScorePanel(ScorePanel score) {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setScorePanel(score);
		}
	}

	public void setAllChoice(PanelAnimation anim) {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setPanelAnim(anim);
		}
	}
	
	public void setAllChoice(PanelAnimation2 anim) {
		for (int j = 0; j < this.allbuttons.size(); j++) {
			allbuttons.get(j).setPanelAnim2(anim);
		}
	}

	public void setPanelLife(PanelLife lifepanel) {
		for (int i = 0; i < this.allbuttons.size(); i++) {
			allbuttons.get(i).setLifePanel(lifepanel);
		}
	}

	public ArrayList<ButtonRight> getAllRightButtons() {
		return this.allbuttons;
	}

}