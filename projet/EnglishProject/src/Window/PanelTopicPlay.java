package Window;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

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
	final JPanel gridPanel;
	final GridLayout gridPanelLayout;
	final JPanel encapsulateGridPanel;
	final GridLayout encapsulateGridLayout;
	private JPanel returnMainMenuPanel;
	private GridLayout returnMainMenuGridLayout;
	private Border border;

	ArrayList<String> topics = new ArrayList<String>();

	public PanelTopicPlay(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.alltopics = new BoxTopic(mainmodele);
		this.alltopics.setOpaque(false);

		Main.window.setSize(899, 600);
		
		this.border = BorderFactory.createEmptyBorder(Main.window.getHeight() / 10, 25, 10, 10);

		this.setBorder(this.border);
		this.setLayout(new FlowLayout());
		// this.setLayout(null);

		this.optionPanel = new JPanel();
		this.optionPanel.setOpaque(false);
		this.buttonTopicPanel = new JPanel();
		this.buttonTopicPanel.setOpaque(false);
		this.topicPanel = new JPanel();
		this.topicPanel.setOpaque(false);
		this.gridPanel = new JPanel();
		this.encapsulateGridPanel = new JPanel();
		this.encapsulateGridPanel.setOpaque(false);

		this.optionPanel.setLayout(new FlowLayout());
		this.buttonTopicPanel.setLayout(new FlowLayout());
		this.topicPanel.setLayout(new FlowLayout());
		// this.gridPanel.setLayout(new GridLayout(1,2));
		this.gridPanelLayout = new GridLayout(1, 2);
		this.gridPanelLayout.setHgap(Main.window.getWidth() / 5);
		this.gridPanel.setLayout(this.gridPanelLayout);
		this.gridPanel.setOpaque(false);
		// this.encapsulateGridPanel.setLayout(new GridLayout(2,1));
		this.encapsulateGridLayout = new GridLayout(3, 1);
		this.encapsulateGridLayout.setVgap(Main.window.getHeight() / 3 - 90);
		this.encapsulateGridPanel.setLayout(this.encapsulateGridLayout);
		//
		this.returnMainMenuPanel = new JPanel();
		this.returnMainMenuPanel.setOpaque(false);
		this.returnMainMenuGridLayout = new GridLayout(1, 3);

		this.gridPanel.add(this.alltopics);
		this.gridPanel.add(new JScrollPane(this.alltopics, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		this.gridPanel.setPreferredSize(new Dimension(750, 250));

		String[] choosewords = { "5", "10", "20", "50", "75", "100" };
		nbwordsComboBox = new JComboBox<String>(choosewords);
		nbwordsComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nbwords = Integer.parseInt((String) nbwordsComboBox.getSelectedItem());
				// System.out.println("nbwords is now of : " + nbwords);
				buttonPlay.setNbWords(nbwords);
			}

		});

		String[] chooseprops = { "2", "4", "6", "8" };
		nbpropsComboBox = new JComboBox<String>(chooseprops);
		nbpropsComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nbprops = Integer.parseInt((String) nbpropsComboBox.getSelectedItem());
				// System.out.println("nbprops is now of : " + nbprops);
				buttonPlay.setNbProps(nbprops);
			}

		});

		String[] chooselife = { "0", "1", "3", "5", "7", "9" };
		nblifeComboBox = new JComboBox<String>(chooselife);
		nblifeComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nblife = Integer.parseInt((String) nblifeComboBox.getSelectedItem());
				// System.out.println("nblife is now of : " + nblife);
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
		this.buttonTopicPanel.setLayout(new GridLayout(4, 1));
		JPanel invi = new JPanel();
		invi.setOpaque(false);
		this.add(invi);
		buttonPlay = new ButtonTopicPlay(mainmodele, nbwordsComboBox.getSelectedItem().toString(),
				nbpropsComboBox.getSelectedItem().toString(), nblifeComboBox.getSelectedItem().toString(), alltopics);
		this.buttonTopicPanel.add(buttonPlay);
		this.buttonTopicPanel.add(new ButtonGoToMainMenu(this.mainmodele, "Previous"));
		JPanel invi2 = new JPanel();
		invi2.setOpaque(false);
		this.add(invi2);
		this.encapsulateGridPanel.add(this.buttonTopicPanel);

		this.add(this.encapsulateGridPanel);

		Main.window.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				gridPanelLayout.setHgap(Main.window.getWidth() / 5);
				encapsulateGridLayout.setVgap(Main.window.getHeight() / 3 - 90);
				border = BorderFactory.createEmptyBorder(Main.window.getHeight() / 10, 25, 10, 10);
				setBorder(border);
				gridPanel.setLayout(gridPanelLayout);
				encapsulateGridPanel.setLayout(encapsulateGridLayout);
				gridPanel.repaint();
				encapsulateGridPanel.repaint();
				gridPanel.validate();
				encapsulateGridPanel.validate();
				Main.window.revalidate();
				Main.window.repaint();
			}
		});

	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "MainMenuSky.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}