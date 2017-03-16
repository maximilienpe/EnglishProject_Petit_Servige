package Window;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelLife extends JPanel {

	//Panels and Layout
	private GridLayout mainGridLayout;
	private ArrayList<JPanel> lifePanels;
	private ArrayList<JLabel> lifeLabel;

	//content
	private int maxLife;
	private int life;
	private Modele modele;
	
	public PanelLife(Modele m){
		//System.out.println("System of life start");
		//initialize content
		this.modele = m;
		System.out.println("Number of life : " + this.modele.getVocabGameGraphicMaxLife());
		this.maxLife = this.modele.getVocabGameGraphicMaxLife();
		this.life = this.maxLife;
		
		this.lifeLabel = new ArrayList<JLabel>();
		this.lifePanels = new ArrayList<JPanel>();
		
		//initialize Panel and layout
		this.mainGridLayout = new GridLayout(1,this.maxLife);
		for (int i = 0; i < this.maxLife  ; i++) {
			JPanel lp = new JPanel();
			JLabel ll = new JLabel("<3");
			lp.add(ll);
			this.lifeLabel.add(ll);
			this.lifePanels.add(lp);
			this.add(lp);
		}
	}
	
	public void updateLife() {

		this.life = this.modele.getVocabGameGraphicLife();
		System.out.println(this.life);
		this.lifeLabel.get(this.life).setText("");
	}
	
	
}
