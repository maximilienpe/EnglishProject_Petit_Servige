package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonRight extends JButton implements MouseListener {

	Nenuphar choice;
	PanelRight right;
	PanelLeft left;
	ScorePanel score;
	Modele mainmodele;
	int rank;
	PanelAnimation anim;

	private PanelLife lifePanel;
	
	public ButtonRight(Modele mainmodele, int rank, PanelRight right) {
		this.mainmodele = mainmodele;
		this.rank = rank;
		this.right = right;
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

	public void setPanelLeft(PanelLeft left) {
		this.left = left;
	}

	public void setScorePanel(ScorePanel score) {
		this.score = score;
	}

	public void setLifePanel(PanelLife lifepanel) {
		this.lifePanel = lifepanel;
	}
	
	public void setPanelAnim(PanelAnimation anim) {
		this.anim = anim;
	}

	public void answer() {
		this.mainmodele.playVocabGameGraphic(this.choice.getText(), this.getText());
		this.choice.setVisible(false);
		this.right.setButtonsNull();
		this.left.setButtonsNull();
		this.anim.removeNenu(choice);
		this.anim.WaitEnd();
		score.refresh();
		this.lifePanel.updateLife();
		Main.window.getContentPane().validate();
	}

}