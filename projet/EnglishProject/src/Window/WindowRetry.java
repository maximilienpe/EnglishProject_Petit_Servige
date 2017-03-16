package Window;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import MainSystem.Modele;

public class WindowRetry {

	JPanel pan;

	Modele mainmodele;

	public WindowRetry(Modele mainmodele) {
		this.mainmodele = mainmodele;
		SetPanel();
		Object[] options = { "Retry", "Quit" };
		int output = JOptionPane.showOptionDialog(null,
				"Your score is : " + this.mainmodele.getVocabGameGraphicScore() + "/"
						+ this.mainmodele.getVocabGameGraphicScoreMax(),
				"End", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
		if (output == 0) {
			Main.window.remove(Main.window.getContentPane());
			// modele.launchVocabGameGraphic();
			Main.window.setContentPane(new PanelGameWindow(mainmodele, 4));
			Main.window.getContentPane().validate();
		}

		if (output == 1) {
			Main.window.remove(Main.window.getContentPane());
			Main.window.setContentPane(new PanelMainMenu(mainmodele));
			Main.window.getContentPane().validate();
		}
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
