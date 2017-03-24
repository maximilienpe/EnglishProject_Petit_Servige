package Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerRightButtonLesson implements ActionListener {

	private PanelLesson2 panel;
	
	public ListenerRightButtonLesson(PanelLesson2 pl) {
		this.panel = pl;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int index;
		if (this.panel.getTopicIndex() >= this.panel.getTopics().size() -1) {
			index = 0;
		}
		else {
			index = this.panel.getTopicIndex() +1;
		}
		this.panel.setTopicIndex(index);
		this.panel.update();
	}

}
