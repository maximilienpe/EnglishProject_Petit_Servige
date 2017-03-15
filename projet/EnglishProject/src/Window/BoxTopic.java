package Window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import MainSystem.Modele;

public class BoxTopic extends JPanel {

	Modele mainmodele;

	ArrayList<String> topics = new ArrayList<String>();

	ArrayList<ButtonAddRemoveTopic> allchoosedtopics;

	ArrayList<ButtonAddRemoveTopic> alltruetopics;

	public BoxTopic(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.topics = mainmodele.getAllTopics();
		this.setBounds(25, 25, 125, 200);

		GridLayout layout = new GridLayout(topics.size() + 1, 1);
		layout.setHgap(5);
		this.setLayout(layout);

		this.add(new JLabel("Choose topics"), BorderLayout.WEST);

		allchoosedtopics = new ArrayList<ButtonAddRemoveTopic>();

		for (int f = 0; f < topics.size() - 1; f++) {
			ButtonAddRemoveTopic toadd = new ButtonAddRemoveTopic(mainmodele, topics.get(f));
			this.add(toadd);
			this.allchoosedtopics.add(toadd);
		}
	}

	public ArrayList<ButtonAddRemoveTopic> getAllTopics() {
		return this.allchoosedtopics;
	}

	public ArrayList<ButtonAddRemoveTopic> getAllChoosedTopics() {
		alltruetopics = new ArrayList<ButtonAddRemoveTopic>();
		for (int j = 0; j < allchoosedtopics.size(); j++) {
			if (allchoosedtopics.get(j).getStatus()) {
				alltruetopics.add(allchoosedtopics.get(j));
			}
		}
		return alltruetopics;
	}
}
