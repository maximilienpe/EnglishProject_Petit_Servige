package Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonFrenchLesson extends JButton implements ActionListener  {

	private Modele modele;
	private PanelLesson2 pl;
	
	public ButtonFrenchLesson(Modele m, PanelLesson2 panelLesson) {
		this.modele = m;
		this.pl = panelLesson;
		this.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.pl.gettypeOfScreen() != 2) {
			this.pl.setTypeOfScreen(2);
			this.pl.update();
		}	
	}
	
}
