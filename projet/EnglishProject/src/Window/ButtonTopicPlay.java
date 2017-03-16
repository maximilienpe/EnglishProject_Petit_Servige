package Window;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import MainSystem.Modele;
import VocabularySystem.TypeOfGame;

public class ButtonTopicPlay extends JButton implements MouseListener {

	Modele mainmodele;

	int nbwords;
	int nbprops;
	int life;
	BoxTopic boxtopic;

	public ButtonTopicPlay(Modele mainmodele, String nbwords, String nbprops, String life, BoxTopic boxtopic) {
		super("Play");
		this.nbwords = Integer.parseInt(nbwords);
		this.nbprops = Integer.parseInt(nbprops);
		this.life = Integer.parseInt(life);
		this.boxtopic = boxtopic;
		this.mainmodele = mainmodele;
		//this.setBounds(25, 400, 60, 30);
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(300,50));
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (boxtopic.getAllChoosedTopics().size() != 0) {
			mainmodele.launchVocabGameGraphic(boxtopic.getAllChoosedTopics(), TypeOfGame.ENGLISH, nbwords, nbprops,
					life);
			Main.window.remove(Main.window.getContentPane());
			Main.window.setContentPane(new PanelGameWindow(mainmodele, nbprops));
			Main.window.getContentPane().validate();
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

}
