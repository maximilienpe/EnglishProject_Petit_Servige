package Window;

import java.util.ArrayList;

import MainSystem.Modele;

public class Nenuphar2 {

	
	private Modele modele;
	
	private int posX;
	private int posY;
	private int r;
	private int theta;
	private int freq;

	private ArrayList<Double> staticTrajectoryX;
	private int timeTolive;
	private int timeLived;
	
	private Boolean Alive;
	
	private String label;
	
	public Nenuphar2(Modele m,String label, int x, int y, int timeToLive) {
		
		this.modele = m;
		this.label = label;
		this.posX = x;
		this.posY = y;
		this.freq = 1;
		this.timeLived = 0;
		this.Alive = true;
		this.timeTolive = timeToLive;
		this.staticTrajectoryX = new ArrayList<Double>();
		for (int i=0; i < this.timeTolive ; i++) {
			//System.out.println(Math.sin(this.freq*(i)*0.01));
			this.staticTrajectoryX.add(Math.sin(this.freq * (i)*0.033));
		}
	}
	
	public void setFreq(int f) {
		this.freq = f;
	}
	
	public void setPosX(int x) {
		this.posX = x;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public void setPosY(int y) {
		this.posY = y;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public double getStaticTrajectoryX(int time) {
		return this.staticTrajectoryX.get(time);
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void increaseTimeLived() {
		if (this.timeLived+1 == this.timeTolive) {
			this.Alive = false;
		}
		this.timeLived++;
	}
	
	public void setTimeLived(int t) {
		this.timeLived = t;
	}
	
	public int getTimeLived() {
		return this.timeLived;
	}
	
	public Boolean isAlive() {
		return this.Alive;
	}
	
	public int getTimeToLive() {
		return this.timeTolive;
	}
	
}
