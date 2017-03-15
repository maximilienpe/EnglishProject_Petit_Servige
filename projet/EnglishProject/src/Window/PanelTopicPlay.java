package Window;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelTopicPlay extends JPanel {

	Modele mainmodele;

	BoxTopic alltopics;

	JComboBox<String> nbwords;
	JComboBox<String> nbprops;
	JComboBox<String> nblife;

	ArrayList<String> topics = new ArrayList<String>();

	public PanelTopicPlay(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.alltopics = new BoxTopic(mainmodele);

		Main.window.setSize(600, 600);

		this.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 10));
		this.setLayout(null);

		this.add(this.alltopics);

		String[] choosewords = { "5", "10", "20" };
		JComboBox<String> nbwords = new JComboBox<String>(choosewords);
		this.nbwords = nbwords;

		String[] chooseprops = { "2", "4", "6" };
		JComboBox<String> nbprops = new JComboBox<String>(chooseprops);
		this.nbprops = nbprops;

		String[] chooselife = { "0", "3", "5" };
		JComboBox<String> nblife = new JComboBox<String>(chooselife);
		this.nblife = nblife;

		this.add(new OptionsGamePanel(nbwords, nbprops, nblife));

		this.add(new ButtonTopicPlay(mainmodele, nbwords.getSelectedItem().toString(),
				nbprops.getSelectedItem().toString(), nblife.getSelectedItem().toString(), alltopics));
	}

}