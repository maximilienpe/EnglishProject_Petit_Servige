package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonAddRemoveTopic extends JButton implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Modele mainmodele;

	String name;

	Boolean status;

	public ButtonAddRemoveTopic(Modele mainmodele, String name) {
		super(name);
		this.name = name;
		this.mainmodele = mainmodele;
		this.status = false;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (!this.status) {
			this.status = true;
		} else {
			this.status = false;
		}
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

	public Boolean getStatus() {
		return this.status;
	}

	public String getNameButton() {
		return this.name;
	}

}
