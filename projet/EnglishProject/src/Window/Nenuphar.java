package Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import MainSystem.Modele;

public class Nenuphar extends JButton implements MouseListener {

	private int initialePosX = 300;
	private int initialePosY = 0;
	private int freq;
	private int posX;
	private int posY;
	private double time;
	private int r = 0;
	private int theta = 0;

	Modele mainmodele;

	PanelLeft left;

	PanelRight right;

	int p;

	public Nenuphar(String name, Modele mainmodele, PanelLeft left, PanelRight right, int p) {
		super(name);
		this.left = left;
		this.right = right;

		this.posX = this.initialePosX;
		this.posY = this.initialePosY;
		this.freq = 10;
		this.time = 0.0;
		
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
	
	public void nextPosition() {
		this.r++;
		this.theta++;
		this.posX = posX + (int) (this.r*Math.cos(this.theta));
		this.posY = posY + (int) (this.r*Math.sin(this.theta));
	}
	
	public void nextPositionSinus() {
		this.time = this.time + 0.01;
		this.posY = this.posY + 1;
		this.posX = this.initialePosX + (int) (150 *  Math.sin(this.freq*(this.time)));
		//System.out.println(this.posX);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		left.setChoice(this);
		right.setChoice(this);
		left.setProp(p);
		right.setProp(p);
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
