package Window;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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

		this.score = score;

		this.mainmodele = mainmodele;

		// this.setBounds(100, 100, 400, 400);

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
						Thread.sleep(1000);
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
				for (int j = 50; j < Main.window.getHeight() - 200; j++) {
					Main.window.validate();
					n.setLocation(n.getPosX(), n.getPosY());
					/*
					 * int y = n.getPosY(); y++; n.setPosY(y);
					 */
					n.nextPositionCardioide();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (allchoice.contains(n)) {
					System.out.println("test end");
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
			this.mainmodele.setVocabGameGraphicEndded(true);
			new WindowRetry(mainmodele);
			// we have to add some lines to stop the threads
		}
		if (this.mainmodele.getVocabGameGraphicLife() == 0) {
			new WindowRetry(mainmodele);
			// we have to add some lines to stop the threads
		}
	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Image" + File.separator + "bleu.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeNenu(Nenuphar n) {
		this.allchoice.remove(n);
	}

}
