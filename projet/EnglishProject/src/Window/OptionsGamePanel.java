package Window;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionsGamePanel extends JPanel {

	JComboBox<String> nbwords;
	JComboBox<String> nbprops;
	JComboBox<String> nblife;

	public OptionsGamePanel(JComboBox<String> nbwords, JComboBox<String> nbprops, JComboBox<String> nblife) {

		this.nbwords = nbwords;
		this.nbprops = nbprops;
		this.nblife = nblife;
		//this.setBounds(175, 25, 150, 200);
		this.setLayout(new GridLayout(6, 1));

		this.add(new JLabel("Choose nb words"));
		this.add(nbwords);

		this.add(new JLabel("Choose nb prop"));
		this.add(nbprops);

		this.add(new JLabel("Choose nb life"));
		this.add(nblife);
	}

}
