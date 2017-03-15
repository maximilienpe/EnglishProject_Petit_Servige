package Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerLeftButtonLesson implements ActionListener {

	private PanelLessons panel;
	
	public ListenerLeftButtonLesson(PanelLessons pl) {
		this.panel = pl;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int index;
		if (this.panel.getTopicIndex() <= 0) {
			index = this.panel.getTopics().size() -1;
		}
		else {
			index = this.panel.getTopicIndex() -1;
		}
		this.panel.setTopicIndex(index);
		this.panel.update();
	}

}
