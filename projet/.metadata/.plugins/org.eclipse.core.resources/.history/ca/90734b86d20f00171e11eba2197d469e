package Window;

import javax.swing.JOptionPane;

import MainSystem.Modele;

public class WindowRetry {

	private Modele mainmodele;

	public WindowRetry(Modele mainmodele) {
		this.mainmodele = mainmodele;
		Object[] options = { "Retry", "Quit" };
		int output = JOptionPane.showOptionDialog(null,
				"Your score is : " + this.mainmodele.getVocabGameGraphicScore() + "/"
						+ this.mainmodele.getVocabGameGraphicScoreMax(),
				"End", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (output == 0) {
			Main.window.remove(Main.window.getContentPane());
			mainmodele.launchVocabGameGraphic(mainmodele.getAllTitleSelectedTopic(),
					mainmodele.getVocabGameGraphicTypeOfGame(), mainmodele.getVocabGameGraphicNumberOfWords(),
					mainmodele.getVocabGameGraphicNumberOfPropositions(), mainmodele.getVocabGameGraphicMaxLife());
			Main.window.setContentPane(
					new PanelGameWindow(mainmodele, mainmodele.getVocabGameGraphicNumberOfPropositions()));
			Main.window.getContentPane().validate();
			Main.window.getContentPane().repaint();
		}

		if (output == 1) {
			Main.window.remove(Main.window.getContentPane());
			Main.window.setContentPane(new PanelMainMenu(mainmodele));
			Main.window.getContentPane().validate();
			Main.window.getContentPane().repaint();
		}
	}

}
