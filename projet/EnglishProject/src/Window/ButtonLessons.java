package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import MainSystem.Modele;
import VocabularySystem.TypeOfGame;

public class ButtonLessons extends JButton implements MouseListener {

	Modele mainmodele;

	String name;

	public ButtonLessons(Modele mainmodele, String name) {
		super(name);
		this.name = name;
		this.mainmodele = mainmodele;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Main.window.remove(Main.window.getContentPane());
		Main.window.setSize(600, 600);
		Main.window.setContentPane(new PanelLessons(mainmodele));
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
