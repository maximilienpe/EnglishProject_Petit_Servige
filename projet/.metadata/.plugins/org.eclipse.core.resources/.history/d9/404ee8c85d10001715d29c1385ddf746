package Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonGoToMainMenu extends JButton implements ActionListener {

	private Modele modele;

	String name;

	public ButtonGoToMainMenu(Modele m, String name) {
		super(name);
		this.name = name;
		this.modele = m;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.window.remove(Main.window.getContentPane());
		Main.window.setContentPane(new PanelMainMenu(modele));
		Main.window.getContentPane().validate();
	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "MainMenuButton.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.setColor(Color.black);
			g.drawString(this.name, this.getWidth() / 2 - (this.getWidth() / 2 / 10), (this.getHeight() / 2) + 6);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
