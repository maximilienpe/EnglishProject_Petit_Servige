package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonPlay extends JButton implements MouseListener {

	Modele mainmodele;

	public ButtonPlay(Modele mainmodele) {
		super("Play");
		this.mainmodele = mainmodele;
		//this.setBounds(45, 50, 90, 30);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Main.window.remove(Main.window.getContentPane());
		Main.window.setContentPane(new PanelTopicPlay(mainmodele));
		Main.window.getContentPane().validate();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
