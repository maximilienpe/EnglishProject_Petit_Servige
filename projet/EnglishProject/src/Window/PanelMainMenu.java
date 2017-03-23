package Window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import MainSystem.Modele;
import Music.BackgroundMusic;

public class PanelMainMenu extends JPanel {

	private Modele mainmodele;
	private JPanel gridPanel;
	private Dimension buttonSize;
	
	private JPanel mainPanel;
	private FlowLayout mainLayout;
	
	private JPanel volumePanel;
	private FlowLayout volumeLayout;
	private BackgroundMusic music;
	private PanelMusicVolume volumeManager;

	public PanelMainMenu(Modele mainmodele) {

		// initialize the "this panel"
		this.setLayout(new BorderLayout());
		this.mainmodele = mainmodele;
		Main.window.setSize(new Dimension(900,600));
		System.out.println(this.mainmodele.getTopics());

		//flow Panel
		this.mainLayout = new FlowLayout();
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(this.mainLayout);
		this.mainPanel.setOpaque(false);
		
		/*
		//volume Panel
		this.volumeLayout = new FlowLayout(FlowLayout.RIGHT);
		this.volumePanel = new JPanel();
		this.volumePanel.setLayout(volumeLayout);
		this.music = Main.window.getMusic();
		this.volumeManager = new PanelMusicVolume(this.music);
		this.volumePanel.add(this.volumeManager);
		this.mainPanel.add(this.volumePanel);
		*/
		
		// grid Panel
		this.gridPanel = new JPanel();
		this.gridPanel.setLayout(new GridLayout(7, 1));
		gridPanel.setOpaque(false);

		// button add to the main panel
		JPanel invi = new JPanel();
		invi.setOpaque(false);
		this.gridPanel.add(invi);

		this.buttonSize = new Dimension(500, 50);

		ButtonPlay buttonplay = new ButtonPlay(mainmodele);
		buttonplay.setPreferredSize(this.buttonSize);
		this.gridPanel.add(buttonplay);
		JPanel invi1 = new JPanel();
		invi1.setOpaque(false);
		this.gridPanel.add(invi1);

		ButtonLessons buttonlessons = new ButtonLessons(mainmodele, "Lessons");
		buttonlessons.setPreferredSize(this.buttonSize);
		this.gridPanel.add(buttonlessons);
		JPanel invi2 = new JPanel();
		invi2.setOpaque(false);
		this.gridPanel.add(invi2);

		ButtonMainMenuQuit buttonquit = new ButtonMainMenuQuit("Quit");
		buttonquit.setPreferredSize(this.buttonSize);
		this.gridPanel.add(buttonquit);
		JPanel invi3 = new JPanel();
		invi3.setOpaque(false);
		this.gridPanel.add(invi3);
		this.mainPanel.add(gridPanel);
		
		this.add(mainPanel, BorderLayout.CENTER);

	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "MainMenu2.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
