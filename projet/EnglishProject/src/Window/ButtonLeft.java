package Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import MainSystem.Modele;

public class ButtonLeft extends JButton implements MouseListener {

	Nenuphar choice;
	PanelRight right;
	PanelLeft left;
	ScorePanel score;
	Modele mainmodele;
	int rank;
	PanelAnimation anim;

	private PanelLife lifePanel;

	public ButtonLeft(Modele mainmodele, int rank, PanelLeft left) {
		this.setSize(new Dimension(200, 100));
		this.mainmodele = mainmodele;
		this.rank = rank;
		this.left = left;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		answer();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setChoice(Nenuphar choice) {
		this.choice = choice;
	}

	public void setPanelRight(PanelRight right) {
		this.right = right;
	}

	public void setScorePanel(ScorePanel score) {
		this.score = score;
	}

	public void setLifePanel(PanelLife lifepanel) {
		this.lifePanel = lifepanel;
	}

	public void setPanelAnim(PanelAnimation anim) {
		this.anim = anim;
	}

	public void answer() {
		mainmodele.playVocabGameGraphic(choice.getText(), this.getText());
		choice.setVisible(false);
		right.setButtonsNull();
		left.setButtonsNull();
		this.anim.removeNenu(choice);
		this.anim.WaitEnd();
		score.refresh();
		this.lifePanel.updateLife();

		Main.window.getContentPane().validate();
	}

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "PanelGame.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			g.setFont(new Font("default", Font.BOLD, 12));
			g.setColor(Color.white);
			if (this.getText() != null) {
				g.drawString(this.getText(), this.getWidth() / 2 - (this.getWidth() / 2 - 10),
						(this.getHeight() / 2) + 6);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
