package Window;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
		// this.setBounds(175, 25, 150, 200);
		this.setLayout(new GridLayout(6, 1));


		JLabel nbwordsLabel = new JLabel("Choose number of words :");
		this.nbwords.setToolTipText("If the number of words asked exceed "
				+ "\nthe number of words in the selected topics, "
				+ "\nthe minimum of both of thoses numbers will be taken.");
		this.add(nbwordsLabel);
		this.add(nbwords);

		this.add(new JLabel("Choose number propositions :"));
		this.nbprops.setToolTipText("Set 8 propositions risk to slow the game. : still in progress.");
		this.add(nbprops);

		JLabel nblifeLabel = new JLabel("Choose number of hearts :");
		this.nblife.setToolTipText("If 0 is selected, the game launch the infinite life mode !");
		this.add(nblifeLabel);
		this.add(nblife);
	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "WoodTopicBox.png"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
