package Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonPlay extends JButton implements MouseListener {

	Modele mainmodele;

	public ButtonPlay(Modele mainmodele) {
		super("Play");
		this.mainmodele = mainmodele;
		// this.setBounds(45, 50, 90, 30);
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

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "MainMenuButton.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.setColor(Color.black);
			g.drawString("Play", this.getWidth() / 2 - (this.getWidth() / 2 / 14), (this.getHeight() / 2) + 6);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
