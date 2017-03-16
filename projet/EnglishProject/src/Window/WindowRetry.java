package Window;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainSystem.Modele;

public class WindowRetry extends JPanel {

	JPanel pan;

	Modele mainmodele;

	public WindowRetry(Modele mainmodele) {
		this.mainmodele = mainmodele;
		Main.window.getContentPane().removeAll();
		Main.window.setSize(250, 500);
		SetPanel();
		Main.window.setContentPane(pan);
		Main.window.getContentPane().validate();
	}

	public void SetPanel() {

		JPanel text = new JPanel();
		text.setLayout(new BoxLayout(text, BoxLayout.LINE_AXIS));
		text.add(new JLabel("Your score is : " + this.mainmodele.getVocabGameGraphicScore() + "/"
				+ this.mainmodele.getVocabGameGraphicScoreMax()));

		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
		buttons.add(new JButton("Retry"));
		buttons.add(new ButtonMainMenuQuit("Quit"));

		pan = new JPanel();
		this.pan.setLayout(new BoxLayout(this.pan, BoxLayout.PAGE_AXIS));
		this.pan.add(text);
		this.pan.add(buttons);
	}

}
