package Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import MainSystem.Modele;

public class PanelAnimation2 extends JPanel implements ActionListener, MouseListener {

	private PanelAnimation2 pane;
	private Modele modele;
	private PanelLeft left;
	private PanelRight right;
	private ScorePanel score;
	
	
	//ArrayList content
	private int numberOfNenuphars;
	private ArrayList<Integer> startFrameNenu;
	private ArrayList<Integer> startXNenu;
	private ArrayList<Nenuphar2> nenuphars;
	private int nenuTimeToLive;
	private int selectedNenu;
	
	//nenuphar selector
	private int hupperThreshold;
	private int lowerThreshold;

	//image
	private static final Image IMG_NENUPHAR = ImageLoader.loadImage("Graphics" + File.separator + "smallWaterlily.png");
	
	//Timer
	private int fps;
	private int frame;
	private Timer tempo;
	
	//music
	private boolean isMusicLaunched;
	private String pathMusic;
	
	private boolean isFinished;
	
	public PanelAnimation2(Modele mainmodele, PanelLeft left, PanelRight right, ScorePanel score) {
		this.isFinished = false;
		this.pathMusic = "Music" + File.separator + "bgm017.wav";
		this.isMusicLaunched = false;
		this.pane = this;
		this.left = left;
		this.right = right;
		this.left.setAllChoice(this);
		this.right.setAllChoice(this);
		this.score = score;
		this.modele = mainmodele;
		this.numberOfNenuphars = this.modele.getAllTheAskedWordsVocabGameGraphic().size();
		this.frame = 1 ;
		
		this.fps = 30;
		this.tempo = new Timer(1000/this.fps,this);
		
		this.selectedNenu = 0;
		this.nenuTimeToLive = 450;
		initializeNenuphars();
		
		addMouseListener(this);
	}

	public void nextFrame() {
		this.frame++;
		if (this.hupperThreshold < this.numberOfNenuphars) {
			if (this.frame == this.startFrameNenu.get(this.hupperThreshold)) {
				this.hupperThreshold++;
			}
		}	
		if ( (this.lowerThreshold == this.numberOfNenuphars || this.modele.getVocabGameGraphicLife() == 0 ) && !this.isFinished) {
			//end
			this.isFinished = true;
			this.tempo.stop();
			Main.window.getMusic().stopMusic();
			this.modele.setVocabGameGraphicEndded(true);
			new WindowRetry(this.modele, Main.window);
		}
		this.repaint();
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Graphics2D graphic2D = (Graphics2D) g;
		try {
			Image img = ImageIO.read(new File("Graphics" + File.separator + "water.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		drawNenuphars(g);
		
		if (!this.tempo.isRunning()) {
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
			g.setColor(Color.WHITE);
			g.drawString("Click here !", (Main.window.getWidth()-400)/2-100, (Main.window.getHeight()-50)/2-50);
		}
	}

	public void initializeNenuphars() {
		this.hupperThreshold = 0;
		this.lowerThreshold = 0;
		this.nenuphars = new ArrayList<Nenuphar2>();
		this.startFrameNenu = new ArrayList<Integer>();
		this.startXNenu = new ArrayList<Integer>();
		int randomNum = 45 + (int)(Math.random() * 30); 
		this.startFrameNenu.add(randomNum);
		randomNum = -150 + (int)(Math.random() * 200);
		this.startXNenu.add(randomNum);
		this.nenuphars.add(new Nenuphar2(
				this.modele, 
				this.modele.getAllTheAskedWordsVocabGameGraphic().get(0),
				(Main.window.getWidth()-2*200)/2 + randomNum,
				25, 
				this.nenuTimeToLive)) ;
		//System.out.println(this.numberOfNenuphars);
		
		for (int i=1; i < this.numberOfNenuphars ;i++) {
			randomNum = -150 + (int)(Math.random() * 200);
			System.out.println(randomNum);
			this.startXNenu.add(randomNum);
			this.nenuphars.add(new Nenuphar2(
					this.modele, 
					this.modele.getAllTheAskedWordsVocabGameGraphic().get(i),
					(Main.window.getWidth()-2*200)/2 + randomNum,
					25, 
					this.nenuTimeToLive)) ;
			randomNum = 60 + (int)(Math.random() * 120); 
			//System.out.println("Rand : " +randomNum);
			this.startFrameNenu.add(this.startFrameNenu.get(i-1) + randomNum);
		}
		
	}
	
	public void drawNenuphars(Graphics g) {
		for (int i=this.lowerThreshold ; i < this.hupperThreshold ; i++) {
			//System.out.println(this.nenuphars.get(i).getTimeLived()+1);
			if (this.nenuphars.get(i).getTimeLived()+1 != this.nenuTimeToLive) {
				//System.out.println(i + " : Position x : " + this.nenuphars.get(i).getPosX() + " position y : " + this.nenuphars.get(i).getPosY());
				g.drawImage(this.IMG_NENUPHAR, this.nenuphars.get(i).getPosX(), this.nenuphars.get(i).getPosY(), null);
				
				Font font = new Font("Verdana", Font.BOLD, 12);
				drawCenteredText(g, this.nenuphars.get(i).getLabel(), font, this.nenuphars.get(i).getPosX()+50,this.nenuphars.get(i).getPosY()+60 );
				//g.drawString(this.nenuphars.get(i).getLabel(), this.nenuphars.get(i).getPosX(), this.nenuphars.get(i).getPosY()+50);
				
				this.nenuphars.get(i).increaseTimeLived();
				//System.out.println(this.nenuphars.get(i).getTimeLived() );
				this.nenuphars.get(i).setPosX(   (int) ((Main.window.getWidth()-2*200)/2 + this.startXNenu.get(i) + (150* this.nenuphars.get(i).getStaticTrajectoryX(this.nenuphars.get(i).getTimeLived())) ) );
				this.nenuphars.get(i).setPosY(this.nenuphars.get(i).getPosY()+1);
			}
			else {
				System.out.println(this.lowerThreshold);
				this.lowerThreshold++;
			}
		}
	}
	
	public void drawCenteredText(Graphics g, String text, Font font, int centerX, int centerY) {
		FontMetrics metric = g.getFontMetrics(font);
		int x = centerX - metric.stringWidth(text)/2 ;
		int y = centerY - metric.getHeight()/2;
		g.setFont(font);
		g.drawString(text, x, y);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("Click");
		Handler(e);
		if (! this.tempo.isRunning()) {
			//System.out.println("Start tempo");
			this.tempo.start();
			Main.window.getMusic().stopMusic();
			Main.window.getMusic().setMusic(this.pathMusic);
			Main.window.getMusic().play("infinite");
		}
		
	}
	
	public void Handler(MouseEvent e) {
		for (int i = this.lowerThreshold ; i < this.hupperThreshold ; i++) {
			if ( (e.getX() - this.nenuphars.get(i).getPosX()-50 < 50) && (e.getY() - this.nenuphars.get(i).getPosY()-50 < 50)) {
				this.selectedNenu = i;
				int p = this.modele.getAllTheAskedWordsVocabGameGraphic().indexOf(this.nenuphars.get(i).getLabel());
				left.setProp(p);
				right.setProp(p);
				left.validate();
				right.validate();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.tempo) {
			//System.out.println("Next frame");
			this.nextFrame();
		}
		
	}
	
	public static class ImageLoader {

		public static Image loadImage (String filename) {
			Image image = null;
			try {
				image = ImageIO.read(new File(filename)); 
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return image; 
		}
	}
	
	public Nenuphar2 getSelectedNenuphar() {
		return this.nenuphars.get(this.selectedNenu);
	}

}

