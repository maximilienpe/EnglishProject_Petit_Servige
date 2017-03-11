package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class Nenuphar extends JButton implements MouseListener {

	private int posX = 30;
	private int posY = 50;

	Modele mainmodele;

	ButtonLeft left;

	ButtonRight right;

	int p;

	public Nenuphar(String name, Modele mainmodele, ButtonLeft left, ButtonRight right, int p) {
		super(name);
		this.left = left;
		this.right = right;

		this.mainmodele = mainmodele;
		this.p = p;
		this.setBounds(posX, posY, 80, 40);
		this.addMouseListener(this);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(mainmodele.getRightPropositionsVocabGameGraphic(p).size());
		left.setChoice(this);
		right.setChoice(this);
		left.setText("test");
		// left.setText(mainmodele.getLeftPropositionsVocabGameGraphic(p).get(0));
		// right.setText(mainmodele.getRightPropositionsVocabGameGraphic(p).get(0));
		left.validate();
		right.validate();
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
}
