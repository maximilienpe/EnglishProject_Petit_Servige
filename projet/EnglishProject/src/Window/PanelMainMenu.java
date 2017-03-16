package Window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import MainSystem.Modele;


public class PanelMainMenu extends JPanel {

	private Modele mainmodele;
	private JPanel gridPanel;
	private Dimension buttonSize;

	public PanelMainMenu(Modele mainmodele) {
		
		
		
		//initialize the "this panel"
		this.setLayout(new FlowLayout());
		this.mainmodele = mainmodele;
		Main.window.setSize(900, 600);
		
		//grid Panel
		this.gridPanel = new JPanel();
		this.gridPanel.setLayout(new GridLayout(7,1));
		
		//button add to the main panel
		this.gridPanel.add(new JPanel());
		
		this.buttonSize = new Dimension(500, 50);
		
		ButtonPlay buttonplay = new ButtonPlay(mainmodele);
		buttonplay.setPreferredSize(this.buttonSize);
		this.gridPanel.add(buttonplay);
		this.gridPanel.add(new JPanel());
		
		ButtonLessons buttonlessons = new ButtonLessons(mainmodele,"Lessons");
		buttonlessons.setPreferredSize(this.buttonSize);
		this.gridPanel.add(buttonlessons);
		this.gridPanel.add(new JPanel());
		
		ButtonMainMenuQuit buttonquit = new ButtonMainMenuQuit("Quit");
		buttonquit.setPreferredSize(this.buttonSize);
		this.gridPanel.add(buttonquit);
		this.gridPanel.add(new JPanel());
		this.add(gridPanel);
	}
	
}
