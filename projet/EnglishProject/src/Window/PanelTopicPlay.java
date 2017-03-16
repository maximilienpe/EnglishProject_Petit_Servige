package Window;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
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
	
	private JPanel optionPanel;
	private JPanel buttonTopicPanel;
	private JPanel topicPanel;
	private JPanel gridPanel;
	private JPanel encapsulateGridPanel;

	ArrayList<String> topics = new ArrayList<String>();

	public PanelTopicPlay(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.alltopics = new BoxTopic(mainmodele);

		Main.window.setSize(899, 600);

		this.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 10));
		this.setLayout(new FlowLayout());
		//this.setLayout(null);
		
		this.optionPanel = new JPanel();
		this.buttonTopicPanel = new JPanel();
		this.topicPanel = new JPanel();
		this.gridPanel = new JPanel();
		this.encapsulateGridPanel = new JPanel();
		
		this.optionPanel.setLayout(new FlowLayout());
		this.buttonTopicPanel.setLayout(new FlowLayout());
		this.topicPanel.setLayout(new FlowLayout());
		this.gridPanel.setLayout(new GridLayout(1,2));
		this.encapsulateGridPanel.setLayout(new GridLayout(2,1));
		
		this.gridPanel.add(this.alltopics);

		String[] choosewords = { "5", "10", "20" };
		JComboBox<String> nbwords = new JComboBox<String>(choosewords);
		this.nbwords = nbwords;

		String[] chooseprops = { "2", "4", "6" };
		JComboBox<String> nbprops = new JComboBox<String>(chooseprops);
		this.nbprops = nbprops;

		String[] chooselife = { "0", "3", "5" };
		JComboBox<String> nblife = new JComboBox<String>(chooselife);
		this.nblife = nblife;

		//this.add(new OptionsGamePanel(nbwords, nbprops, nblife));
		this.optionPanel.add(new OptionsGamePanel(nbwords, nbprops, nblife));
		this.gridPanel.add(this.optionPanel);
		this.encapsulateGridPanel.add(this.gridPanel);
		
		//this.add(new ButtonTopicPlay(mainmodele, nbwords.getSelectedItem().toString(),
		//		nbprops.getSelectedItem().toString(), nblife.getSelectedItem().toString(), alltopics));
		this.buttonTopicPanel.add(new ButtonTopicPlay(mainmodele, nbwords.getSelectedItem().toString(),
			nbprops.getSelectedItem().toString(), nblife.getSelectedItem().toString(), alltopics));
		this.encapsulateGridPanel.add(this.buttonTopicPanel);
		
		this.add(this.encapsulateGridPanel);
	
	}

}