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
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;

import MainSystem.Modele;
import VocabularySystem.TypeOfGame;

public class ButtonTopicPlay extends JButton implements MouseListener {

	Modele mainmodele;

	private int nbwords;
	private int nbprops;
	private int life;
	private BoxTopic boxtopic;

	public ButtonTopicPlay(Modele mainmodele, String nbwords, String nbprops, String life, BoxTopic boxtopic) {
		super("Play");
		// this.setPreferredSize(new Dimension(300, 20));
		this.nbwords = Integer.parseInt(nbwords);
		this.nbprops = Integer.parseInt(nbprops);
		this.life = Integer.parseInt(life);
		this.boxtopic = boxtopic;
		this.mainmodele = mainmodele;
		// this.setBounds(25, 400, 60, 30);
		this.addMouseListener(this);
	}

	public void setNbWords(int nbw) {
		this.nbwords = nbw;
	}

	public void setNbProps(int nbp) {
		this.nbprops = nbp;
	}

	public void setNbLife(int nbl) {
		this.life = nbl;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (boxtopic.getAllChoosedTopics().size() != 0) {
			Main.window.getMusic().stopFondu();
			Main.window.getMusic().setMusic("Music" + File.separator + "add072.wav");
			Main.window.getMusic().play();
			if (this.life == 0) {
				mainmodele.launchVocabGameGraphic(this.boxtopic.getAllChoosedTopics(), TypeOfGame.ENGLISH, this.nbwords,
						this.nbprops, -1);
			} else {
				mainmodele.launchVocabGameGraphic(this.boxtopic.getAllChoosedTopics(), TypeOfGame.ENGLISH, this.nbwords,
						this.nbprops, life);
			}

			System.out.println("A new game launch with parameters : " + TypeOfGame.ENGLISH + ", " + nbwords + ", "
					+ nbprops + ", " + life);
			Main.window.remove(Main.window.getContentPane());
			Main.window.setContentPane(new PanelGameWindow(mainmodele, nbprops));
			Main.window.getContentPane().validate();
			Main.window.getMusic().setMusic("Music" +File.separator + "bgm002.wav");
			Main.window.getMusic().play("infinite");
		}
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
			g.drawString("Play", this.getWidth() / 2 - (this.getWidth() / 2 / 10), (this.getHeight() / 2) + 6);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
