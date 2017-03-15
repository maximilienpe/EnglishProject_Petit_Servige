package Window;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelRight extends JPanel {

	Nenuphar choice;
	PanelLeft left;
	int props;
	Modele mainmodele;
	ArrayList<ButtonRight> allbuttons;

	public PanelRight(Modele mainmodele, int props) {
		this.mainmodele = mainmodele;
		this.props = props / 2;

		this.setBounds(500, 100, 100, 500);

		GridLayout layout = new GridLayout(props, 1);
		layout.setHgap(5);
		this.setLayout(layout);

		allbuttons = new ArrayList<ButtonRight>();

		for (int i = 0; i < this.props; i++) {
			ButtonRight k = new ButtonRight(mainmodele, i, this);
			this.add(k);
			allbuttons.add(k);
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

	public ArrayList<ButtonRight> getAllRightButtons() {
		return this.allbuttons;
	}

}