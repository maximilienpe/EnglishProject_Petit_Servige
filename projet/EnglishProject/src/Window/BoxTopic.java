package Window;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainSystem.Modele;

public class BoxTopic extends JPanel {

	Modele mainmodele;

	ArrayList<String> topics = new ArrayList<String>();

	ArrayList<ButtonAddRemoveTopic> allchoosedtopics;

	ArrayList<String> alltruetopics;

	JPanel pane;

	public BoxTopic(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.topics = mainmodele.getAllTopics();
		this.setBounds(25, 25, 125, 200);
		this.setOpaque(false);
		GridLayout layout = new GridLayout(topics.size() + 1, 1);
		layout.setHgap(5);
		this.setLayout(layout);

		JLabel text = new JLabel("Choose topics");

		text.setOpaque(false);

		this.add(text, BorderLayout.WEST);

		allchoosedtopics = new ArrayList<ButtonAddRemoveTopic>();

		for (int f = 0; f < topics.size() - 1; f++) {
			ButtonAddRemoveTopic toadd = new ButtonAddRemoveTopic(mainmodele, topics.get(f));
			toadd.setOpaque(false);
			this.add(toadd);
			this.allchoosedtopics.add(toadd);
		}
	}

	public ArrayList<ButtonAddRemoveTopic> getAllTopics() {
		return this.allchoosedtopics;
	}

	public ArrayList<String> getAllChoosedTopics() {
		alltruetopics = new ArrayList<String>();
		for (int j = 0; j < allchoosedtopics.size(); j++) {
			if (allchoosedtopics.get(j).getStatus()) {
				// System.out.println(allchoosedtopics.get(j).getNameButton());
				alltruetopics.add(allchoosedtopics.get(j).getNameButton());
			}
		}
		return alltruetopics;
	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "white.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
