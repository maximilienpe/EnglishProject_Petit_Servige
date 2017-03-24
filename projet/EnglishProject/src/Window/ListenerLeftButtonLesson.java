package Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerLeftButtonLesson implements ActionListener {

	private PanelLesson2 panel;
	
	public ListenerLeftButtonLesson(PanelLesson2 pl) {
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
