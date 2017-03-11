package Window;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelGameWindow extends JPanel {

	Modele mainmodele;

	public PanelGameWindow(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.setLayout(new BorderLayout());
		ButtonLeft left = new ButtonLeft();
		ButtonRight right = new ButtonRight();
		this.add(left, BorderLayout.WEST);
		this.add(new ButtonRight(), BorderLayout.EAST);
		this.add(new PanelAnimation(mainmodele, left, right));
	}

}
