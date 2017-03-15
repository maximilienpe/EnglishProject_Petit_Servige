package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonLeft extends JButton implements MouseListener {

	Nenuphar choice;
	ButtonRight buttonright;
	Modele mainmodele;

	public ButtonLeft(Modele mainmodele) {
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

	public void setRightButtons(ButtonRight buttonright) {
		this.buttonright = buttonright;
	}

	public void answer() {
		mainmodele.playVocabGameGraphic(choice.getText(), this.getText());
		choice.setVisible(false);
		this.setText(null);
		buttonright.setText(null);
		Main.window.getContentPane().validate();
		System.out.println(mainmodele.getVocabGameGraphicScore());
	}

}
