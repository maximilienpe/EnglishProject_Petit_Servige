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
			this.mainwindow.remove(Main.window.getContentPane());
			this.mainwindow.setSize(899,600);
			mainmodele.launchVocabGameGraphic(mainmodele.getAllTitleSelectedTopic(),
					mainmodele.getVocabGameGraphicTypeOfGame(), mainmodele.getVocabGameGraphicNumberOfWords(),
					mainmodele.getVocabGameGraphicNumberOfPropositions(), mainmodele.getVocabGameGraphicMaxLife());
			this.mainwindow.setContentPane(
					new PanelGameWindow(mainmodele, mainmodele.getVocabGameGraphicNumberOfPropositions()));
			this.mainwindow.setSize(900,600);
			this.mainwindow.getContentPane().validate();
			this.mainwindow.getContentPane().repaint();
		}

		if (output == 1) {
			this.mainwindow.remove(this.mainwindow.getContentPane());
			this.mainwindow.validate();
			this.mainwindow.repaint();
			this.mainwindow.setSize(899,600);
			this.mainwindow.setContentPane(new PanelMainMenu(mainmodele));
			this.mainwindow.setSize(900,600);
			this.mainwindow.getContentPane().validate();
			this.mainwindow.getContentPane().repaint();
		}
	}

}
