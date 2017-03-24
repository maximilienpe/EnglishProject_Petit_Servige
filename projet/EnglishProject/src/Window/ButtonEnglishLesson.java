package Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonEnglishLesson extends JButton implements ActionListener {

	private Modele modele;
	private PanelLesson2 pl;
	
	public ButtonEnglishLesson(Modele m, PanelLesson2 panelLesson) {
		this.modele = m;
		this.pl = panelLesson;
		this.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.pl.gettypeOfScreen() != 1) {
			System.out.println("test");
			this.pl.setTypeOfScreen(1);
			this.pl.update();
		}	
	}
	
}
