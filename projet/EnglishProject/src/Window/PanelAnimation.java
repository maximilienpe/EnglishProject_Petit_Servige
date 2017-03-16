package Window;

import java.util.ArrayList;

import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelAnimation extends JPanel {

	private PanelAnimation pane;

	Modele mainmodele;

	PanelLeft left;

	PanelRight right;

	ScorePanel score;

	ArrayList<Nenuphar> allchoice;

	private Nenuphar justforend;

	public PanelAnimation(Modele mainmodele, PanelLeft left, PanelRight right, ScorePanel score) {

		this.pane = this;

		this.left = left;
		this.right = right;

		this.mainmodele = mainmodele;

		//this.setBounds(100, 100, 400, 400);

		this.setLayout(null);

		allchoice = new ArrayList<Nenuphar>();
		justforend = new Nenuphar(mainmodele.getAllTheAskedWordsVocabGameGraphic().get(0), mainmodele, left, right, 0);

		allchoice.add(justforend);

		Runnable r = new Runnable() {
			public void run() {
				for (int p = 0; p < mainmodele.getAllTheAskedWordsVocabGameGraphic().size(); p++) {
					Nenuphar n = new Nenuphar(mainmodele.getAllTheAskedWordsVocabGameGraphic().get(p), mainmodele, left,
							right, p);
					pane.add(n);
					allchoice.add(n);
					if (p == mainmodele.getAllTheAskedWordsVocabGameGraphic().size() - 1) {
						allchoice.remove(justforend);
					}
					left.setAllChoice(pane);
					right.setAllChoice(pane);
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
					/*int y = n.getPosY();
					y++;
					n.setPosY(y);*/
					n.nextPosition();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (allchoice.contains(n)) {
					allchoice.remove(n);
					WaitEnd();
					n.setVisible(false);
					score.refresh();
				}
				;
			}
		};

		(new Thread(r2)).start();

	}

	public void WaitEnd() {
		System.out.println(allchoice.size());
		if (allchoice.size() == 0) {
			new WindowRetry(mainmodele);
		}
	}

	public void removeNenu(Nenuphar n) {
		this.allchoice.remove(n);
	}

}
