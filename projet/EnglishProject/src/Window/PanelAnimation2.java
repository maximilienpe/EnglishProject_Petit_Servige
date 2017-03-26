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
	private ArrayList<Nenuphar2> onFrameNenuphars;
	private int nenuTimeToLive;
	private int selectedNenu;
	
	//nenuphar selector
	private int upperThreshold;
	private int lowerThreshold;

	//images
	private static final Image IMG_NENUPHAR = ImageLoader.loadImage("Graphics" + File.separator + "smallWaterlily.png");
	private static final Image IMG_NENUPHAR_Yelow = ImageLoader.loadImage("Graphics" + File.separator + "smallYellowWaterlily.png");	
	private static final Image IMG_NENU_SELECTED = ImageLoader.loadImage("Graphics" + File.separator + "selectorHaloVert.png");
	private static final Image IMG_NENU_SELECTED_Yellow = ImageLoader.loadImage("Graphics" + File.separator + "selectorHaloYellow.png");
	
	//Timer
	private int fps;
	private int frame;
	private Timer tempo;
	
	//music
	private boolean isMusicLaunched;
	private String pathMusic;
	
	private boolean isFinished;
	private boolean hasStarted;
	
	private boolean justDeadNenu;
	
	
	public PanelAnimation2(Modele mainmodele, PanelLeft left, PanelRight right, ScorePanel score) {
		this.justDeadNenu = false;
		this.hasStarted = false;
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
		
		this.selectedNenu = -1;
		this.nenuTimeToLive = 450;
		initializeNenuphars();
		
		addMouseListener(this);
	}

	public void nextFrame() {
		this.frame++;
		if (this.upperThreshold < this.numberOfNenuphars) {
			if (this.frame == this.startFrameNenu.get(this.upperThreshold)) {
				this.onFrameNenuphars.add(this.nenuphars.get(this.upperThreshold));
				this.upperThreshold++;
				this.hasStarted = true;
			}
		}	
		if ( (this.onFrameNenuphars.size() == 0 && this.hasStarted && this.lowerThreshold == this.numberOfNenuphars|| this.modele.getVocabGameGraphicLife() == 0 ) && !this.isFinished) {
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
			g.setFont(new Font(Font.SERIF, Font.BOLD, 45));
			g.setColor(Color.GREEN);
			g.drawString("Click here !", (Main.window.getWidth()-400)/2-90, (Main.window.getHeight()-50)/2-40);
		}
	}

	public void initializeNenuphars() {
		this.onFrameNenuphars = new ArrayList<Nenuphar2>();
		this.upperThreshold = 0;
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
		for (int i=0 ; i < this.onFrameNenuphars.size() ; i++) {
			if (this.onFrameNenuphars.get(i).getTimeLived()+1 == this.nenuTimeToLive) {
				this.onFrameNenuphars.remove(i);
				this.lowerThreshold++;
				//System.out.println("End of nenuphar number : " + i);
			}
		}
		
		for (int i=0 ; i < this.onFrameNenuphars.size() ; i++) {
			//System.out.println(this.nenuphars.get(i).getTimeLived()+1);
			if (this.onFrameNenuphars.get(i).getTimeLived()+1 != this.nenuTimeToLive) {
				if (this.onFrameNenuphars.get(i).isSuperNenu()) {
					if (this.selectedNenu == this.nenuphars.indexOf(this.onFrameNenuphars.get(i))) {
						g.drawImage(this.IMG_NENU_SELECTED_Yellow, this.onFrameNenuphars.get(i).getPosX()-38, this.onFrameNenuphars.get(i).getPosY()-38, null);
					}
					g.drawImage(this.IMG_NENUPHAR_Yelow, this.onFrameNenuphars.get(i).getPosX(), this.onFrameNenuphars.get(i).getPosY(), null);
				} else  {
					if (this.selectedNenu == this.nenuphars.indexOf(this.onFrameNenuphars.get(i))) {
						g.drawImage(this.IMG_NENU_SELECTED, this.onFrameNenuphars.get(i).getPosX()-38, this.onFrameNenuphars.get(i).getPosY()-38, null);
					}
					g.drawImage(this.IMG_NENUPHAR, this.onFrameNenuphars.get(i).getPosX(), this.onFrameNenuphars.get(i).getPosY(), null);
				}
				//System.out.println("The nenuphar number " + i + " is repaint.");
				Font font = new Font("Verdana", Font.BOLD, 18);
				drawCenteredText(g, this.onFrameNenuphars.get(i).getLabel(), font, this.onFrameNenuphars.get(i).getPosX()+50,this.onFrameNenuphars.get(i).getPosY()+60 );
				//g.drawString(this.nenuphars.get(i).getLabel(), this.nenuphars.get(i).getPosX(), this.nenuphars.get(i).getPosY()+50);
				
				this.onFrameNenuphars.get(i).increaseTimeLived();
				//System.out.println(this.nenuphars.get(i).getTimeLived() );
				this.onFrameNenuphars.get(i).setPosX(   (int) ((Main.window.getWidth()-2*200)/2 + this.startXNenu.get(this.nenuphars.indexOf(this.onFrameNenuphars.get(i))) + (150* this.onFrameNenuphars.get(i).getStaticTrajectoryX(this.onFrameNenuphars.get(i).getTimeLived())) ) );
				this.onFrameNenuphars.get(i).setPosY(this.onFrameNenuphars.get(i).getPosY()+1);
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
		for (int i = 0 ; i < this.onFrameNenuphars.size() ; i++) {
			if ( (e.getX() - this.onFrameNenuphars.get(i).getPosX()-50 < 50) && (e.getY() - this.onFrameNenuphars.get(i).getPosY()-50 < 50)) {
				this.selectedNenu = this.nenuphars.indexOf(this.onFrameNenuphars.get(i));
				int p = this.modele.getAllTheAskedWordsVocabGameGraphic().indexOf(this.onFrameNenuphars.get(i).getLabel());
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

