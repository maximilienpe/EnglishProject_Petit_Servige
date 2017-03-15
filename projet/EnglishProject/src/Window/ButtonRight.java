package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonRight extends JButton implements MouseListener {

	Nenuphar choice;
	ButtonLeft buttonleft;
	Modele mainmodele;

	public ButtonRight(Modele mainmodele) {
		this.mainmodele = mainmodele;
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

	public void setLeftButtons(ButtonLeft buttonleft) {
		this.buttonleft = buttonleft;
	}

	public void answer() {
		mainmodele.playVocabGameGraphic(choice.getText(), this.getText());
		choice.setVisible(false);
		this.setText(null);
		buttonleft.setText(null);
		Main.window.getContentPane().validate();
	}

}