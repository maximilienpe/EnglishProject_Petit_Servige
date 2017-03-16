package Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonMainMenuQuit extends JButton implements ActionListener {

	
	public ButtonMainMenuQuit(String name) {
		super(name);
		this.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.window.dispose();
	}

	
	
	
}
