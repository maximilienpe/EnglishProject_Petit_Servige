package Window;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelTopicPlay extends JPanel {

	Modele mainmodele;

	ArrayList<String> topics = new ArrayList<String>();

	public PanelTopicPlay(Modele mainmodele) {
		this.mainmodele = mainmodele;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		topics = mainmodele.getAllTopics();
		for (int f = 0; f < topics.size() - 1; f++) {
			this.add(new ButtonTopicPlay(mainmodele, topics.get(f)));
		}
	}

}