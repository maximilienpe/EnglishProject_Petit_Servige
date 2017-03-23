package Window;

import javax.swing.JOptionPane;

import MainSystem.Modele;

public class WindowRetry {

	private Modele modele;
	private WindowGame mainwindow;

	public WindowRetry(Modele mainmodele, WindowGame main) {
		this.modele = mainmodele;
		this.mainwindow = main;
		Object[] options = { "Retry", "Quit" };
		int output = JOptionPane.showOptionDialog(null,
				"Your score is : " + this.modele.getVocabGameGraphicScore() + "/"
						+ this.modele.getVocabGameGraphicScoreMax(),
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
			this.mainwindow.removeAll();
			this.mainwindow.validate();
			this.mainwindow.repaint();
			this.mainwindow.setVisible(true);
			this.mainwindow.setContentPane(new PanelMainMenu(mainmodele));
			this.mainwindow.getContentPane().setVisible(true);
			this.mainwindow.getContentPane().validate();
			this.mainwindow.getContentPane().repaint();
		}
	}

}
