package Window;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelGameWindow extends JPanel {

	Modele mainmodele;

	int nbprops;
	
	//Panels
	//north
	private JPanel northPanel;
	private BoxLayout northLayout;
	
	//content
	private ScorePanel scorepanel;
	private PanelLife lifepanel;

	public PanelGameWindow(Modele mainmodele, int nbprops) {
		this.nbprops = nbprops;
		this.mainmodele = mainmodele;
		Main.window.setSize(900, 600);

		//this.setLayout(null);
		this.setLayout(new BorderLayout());

		//initialize north panel
		this.northPanel = new JPanel();
		this.northLayout = new BoxLayout(this.northPanel, BoxLayout.LINE_AXIS);
		this.northPanel.setLayout(this.northLayout);
		
		this.scorepanel = new ScorePanel(mainmodele);
		this.lifepanel = new PanelLife(mainmodele);
		this.northPanel.add(this.scorepanel);
		this.northPanel.add(this.lifepanel);

		PanelLeft left = new PanelLeft(mainmodele, this.nbprops, this.scorepanel, this.lifepanel);
		PanelRight right = new PanelRight(mainmodele, this.nbprops, this.scorepanel, this.lifepanel);

		left.setPanelRight(right);
		right.setPanelLeft(left);

		left.setScorePanel(this.scorepanel);
		left.setPanelLife(this.lifepanel);
		right.setScorePanel(this.scorepanel);
		right.setPanelLife(this.lifepanel);

		this.scorepanel.refresh();

		this.add(this.northPanel, BorderLayout.NORTH);

		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);

		this.add(new PanelAnimation(mainmodele, left, right, this.scorepanel), BorderLayout.CENTER);
	}

}
