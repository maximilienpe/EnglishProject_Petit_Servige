package Window;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelGameWindow extends JPanel {

	Modele mainmodele;

	public PanelGameWindow(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.setLayout(new BorderLayout());
		ButtonLeft left = new ButtonLeft(mainmodele);
		ButtonRight right = new ButtonRight(mainmodele);
		left.setRightButtons(right);
		right.setLeftButtons(left);
		this.add(new ScorePanel(mainmodele), BorderLayout.NORTH);
		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
		this.add(new PanelAnimation(mainmodele, left, right));
	}

}
