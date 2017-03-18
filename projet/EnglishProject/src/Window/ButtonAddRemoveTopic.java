package Window;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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

	public void paintComponent(Graphics g) {
		try {
			Image img;
			if (!this.status) {
				img = ImageIO.read(new File("Graphics" + File.separator + "rougeb.jpg"));
				g.drawString(this.name, this.getWidth() / 2, this.getHeight() / 2);
			} else {
				img = ImageIO.read(new File("Graphics" + File.separator + "vertb.jpg"));
			}
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			g.drawString(this.name, 20, 17);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (!this.status) {
			this.status = true;
		} else {
			this.status = false;
		}
		this.setContentAreaFilled(false);
		this.setText(this.getText());
		this.repaint();
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
