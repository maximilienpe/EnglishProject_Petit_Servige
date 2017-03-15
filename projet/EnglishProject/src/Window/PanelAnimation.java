package Window;

import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelAnimation extends JPanel {

	private PanelAnimation pane;

	Modele mainmodele;

	PanelLeft left;

	PanelRight right;

	public PanelAnimation(Modele mainmodele, PanelLeft left, PanelRight right) {

		this.pane = this;

		this.left = left;
		this.right = right;

		this.mainmodele = mainmodele;

		this.setLayout(null);

		Runnable r = new Runnable() {
			public void run() {
				for (int p = 0; p < mainmodele.getAllTheAskedWordsVocabGameGraphic().size(); p++) {
					Nenuphar n = new Nenuphar(mainmodele.getAllTheAskedWordsVocabGameGraphic().get(p), mainmodele, left, right, p);
					pane.add(n);
					runItem(n);
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		};

		(new Thread(r)).start();

	}

	public void runItem(Nenuphar n) {
		Runnable r2 = new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				for (int j = 50; j < Main.window.getHeight(); j++) {
					Main.window.validate();
					n.move(n.getPosX(), n.getPosY());
					int y = n.getPosY();
					y++;
					n.setPosY(y);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		(new Thread(r2)).start();
	}

}
