package Window;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelTopicPlay extends JPanel {

	Modele mainmodele;

	BoxTopic alltopics;

	private JComboBox<String> nbwordsComboBox;
	private JComboBox<String> nbpropsComboBox;
	private JComboBox<String> nblifeComboBox;
	private ButtonTopicPlay buttonPlay;

	private int nbwords;
	private int nbprops;
	private int nblife;

	private JPanel optionPanel;
	private JPanel buttonTopicPanel;
	private JPanel topicPanel;
	private JPanel gridPanel;
	private GridLayout gridPanelLayout;
	private JPanel encapsulateGridPanel;
	private GridLayout encapsulateGridLayout;
	private JPanel returnMainMenuPanel;
	private GridLayout returnMainMenuGridLayout;

	ArrayList<String> topics = new ArrayList<String>();

	public PanelTopicPlay(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.alltopics = new BoxTopic(mainmodele);

		Main.window.setSize(899, 600);

		this.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 10));
		this.setLayout(new FlowLayout());
		// this.setLayout(null);

		this.optionPanel = new JPanel();
		this.optionPanel.setOpaque(false);
		this.buttonTopicPanel = new JPanel();
		this.buttonTopicPanel.setOpaque(false);
		this.topicPanel = new JPanel();
		this.topicPanel.setOpaque(false);
		this.gridPanel = new JPanel();
		this.gridPanel.setOpaque(false);
		this.encapsulateGridPanel = new JPanel();
		this.encapsulateGridPanel.setOpaque(false);

		this.optionPanel.setLayout(new FlowLayout());
		this.buttonTopicPanel.setLayout(new FlowLayout());
		this.topicPanel.setLayout(new FlowLayout());
		// this.gridPanel.setLayout(new GridLayout(1,2));
		this.gridPanelLayout = new GridLayout(1, 2);
		this.gridPanelLayout.setHgap(200);
		this.gridPanel.setLayout(this.gridPanelLayout);
		// this.encapsulateGridPanel.setLayout(new GridLayout(2,1));
		this.encapsulateGridLayout = new GridLayout(3, 1);
		this.encapsulateGridLayout.setVgap(250);
		this.encapsulateGridPanel.setLayout(this.encapsulateGridLayout);
		//
		this.returnMainMenuPanel = new JPanel();
		this.returnMainMenuPanel.setOpaque(false);
		this.returnMainMenuGridLayout = new GridLayout(1, 3);

		this.gridPanel.add(this.alltopics);

		String[] choosewords = { "5", "10", "20" };
		nbwordsComboBox = new JComboBox<String>(choosewords);
		nbwordsComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nbwords = Integer.parseInt((String) nbwordsComboBox.getSelectedItem());
				System.out.println("nbwords is now of : " + nbwords);
				buttonPlay.setNbWords(nbwords);
			}

		});

		String[] chooseprops = { "2", "4", "6" };
		nbpropsComboBox = new JComboBox<String>(chooseprops);
		nbpropsComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nbprops = Integer.parseInt((String) nbpropsComboBox.getSelectedItem());
				System.out.println("nbprops is now of : " + nbprops);
				buttonPlay.setNbProps(nbprops);
			}

		});

		String[] chooselife = { "0", "3", "5" };
		nblifeComboBox = new JComboBox<String>(chooselife);
		nblifeComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nblife = Integer.parseInt((String) nblifeComboBox.getSelectedItem());
				System.out.println("nblife is now of : " + nblife);
				buttonPlay.setNbLife(nblife);
			}

		});

		// this.add(new OptionsGamePanel(nbwords, nbprops, nblife));
		this.optionPanel.add(new OptionsGamePanel(nbwordsComboBox, nbpropsComboBox, nblifeComboBox));
		this.gridPanel.add(this.optionPanel);
		this.encapsulateGridPanel.add(this.gridPanel);

		// this.add(new ButtonTopicPlay(mainmodele,
		// nbwords.getSelectedItem().toString(),
		// nbprops.getSelectedItem().toString(),
		// nblife.getSelectedItem().toString(), alltopics));
		buttonPlay = new ButtonTopicPlay(mainmodele, nbwordsComboBox.getSelectedItem().toString(),
				nbpropsComboBox.getSelectedItem().toString(), nblifeComboBox.getSelectedItem().toString(), alltopics);
		this.buttonTopicPanel.add(buttonPlay);
		this.encapsulateGridPanel.add(this.buttonTopicPanel);

		this.add(this.encapsulateGridPanel);

	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Image" + File.separator + "rose.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}