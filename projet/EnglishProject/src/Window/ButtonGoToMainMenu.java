package Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonGoToMainMenu extends JButton implements ActionListener {

	private Modele modele;
	
	public ButtonGoToMainMenu(Modele m, String name) {
		super(name);
		this.modele = m;
		this.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.window.remove(Main.window.getContentPane());
		Main.window.setContentPane(new PanelMainMenu(modele));
		Main.window.getContentPane().validate();
	}

}
