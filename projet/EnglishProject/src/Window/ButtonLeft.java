package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonLeft extends JButton implements MouseListener {

	Nenuphar choice;
	PanelRight right;
	PanelLeft left;
	ScorePanel score;
	Modele mainmodele;
	int rank;

	public ButtonLeft(Modele mainmodele, int rank, PanelLeft left) {
		this.mainmodele = mainmodele;
		this.rank = rank;
		this.left = left;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		answer();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setChoice(Nenuphar choice) {
		this.choice = choice;
	}

	public void setPanelRight(PanelRight right) {
		this.right = right;
	}

	public void setScorePanel(ScorePanel score) {
		this.score = score;
	}

	public void answer() {
		mainmodele.playVocabGameGraphic(choice.getText(), this.getText());
		choice.setVisible(false);
		right.setButtonsNull();
		left.setButtonsNull();
		score.refresh();
		Main.window.getContentPane().validate();
	}

}
