package GameCore;

import javax.swing.Timer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import MainSystem.Modele;
import VocabularySystem.TypeOfGame;


public class IGGame extends JPanel implements ActionListener, MouseListener {

	//modele game
	private Modele m;
	/*private JPanel propositionPanel1;
	private JPanel propositionPanel2;
	private ArrayList<JPanel> propositions1;
	private ArrayList<JPanel> propositions2;*/
	
	//params game
	private int FPS; // target frames per second
	private int frame; // time elapsed since last turn
	private Timer clock;
	
	//params IGGame general
	private String screenTitle = "TestIGGame";
	private static final Dimension SCREENSIZE = new Dimension(1620,800);
	private static int NUMBERPROPS = 10 ;

	
	public IGGame(Modele m, String topicTitle, TypeOfGame type, int numberOfWords, int numberOfPropositions) {
		
		//init JPanel
		//this.setLayout(new BorderLayout());
		setPreferredSize(SCREENSIZE);
		
		//init modele game
		this.m = m;
		this.m.launchVocabGame(topicTitle, type, numberOfWords);
		/*this.initializeModeleGame();
		this.add(this.propositionPanel1,BorderLayout.EAST);
		this.add(this.propositionPanel2, BorderLayout.WEST);*/	
		
		
		
		//init params game
		this.FPS = 30;
		this.frame = 0;
		this.clock = new Timer(1000/this.FPS,(ActionListener) this);
		
		addMouseListener(this);

		//init frame
		JFrame frame = new JFrame(this.screenTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, SCREENSIZE.width, SCREENSIZE.height);
		
		drawPropositionsStructure(g2d);
	
		
		if (!clock.isRunning()) { // start text
			g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
			g2d.setColor(Color.BLACK);
			g2d.drawString("CLICK TO START !", (SCREENSIZE.width/2)-150, (SCREENSIZE.height/2));
		}
	}
	
	public void drawPropositionsStructure(Graphics2D g2d) {
		for (int i =0 ; i < NUMBERPROPS; i++) {
			if (i < NUMBERPROPS/2) {
				g2d.drawRect(0, i*(SCREENSIZE.height/(NUMBERPROPS/2)), SCREENSIZE.width/6, SCREENSIZE.height/(NUMBERPROPS/2));
			}
			else {
				g2d.drawRect(5*SCREENSIZE.width/6, (i-(NUMBERPROPS/2))*(SCREENSIZE.height/(NUMBERPROPS/2)), SCREENSIZE.width/6, SCREENSIZE.height/(NUMBERPROPS/2));
			}
		}
	}
	
	public void initializePropositionsStructure() {
		
	}
	
	/*public void initializeModeleGame() {
		this.propositionPanel1 = new JPanel();
		this.propositionPanel2 = new JPanel();
		this.propositionPanel1.setLayout(new GridLayout(4,1));
		this.propositionPanel2.setLayout(new GridLayout(4,1));
		this.propositionPanel1.setPreferredSize(new Dimension(this.screenSizeX/6,this.screenSizeY));
		this.propositionPanel2.setPreferredSize(new Dimension(this.screenSizeX/6,this.screenSizeY));
		this.propositions1 = new ArrayList<JPanel>();
		this.propositions2 = new ArrayList<JPanel>();
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		for(int i=0; i < 4 ; i++) {
			JPanel proposition1 = new JPanel();
			JPanel proposition2 = new JPanel();
			proposition1.setPreferredSize(new Dimension(this.screenSizeX/6,this.screenSizeY/4));
			proposition2.setPreferredSize(new Dimension(this.screenSizeX/6,this.screenSizeY/4));
			proposition1.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
			proposition2.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
			//add first propositions
			JLabel prop1 = new JLabel("Test1");
			JLabel prop2 = new JLabel("Test2");
			proposition1.add(prop1);
			proposition2.add(prop2);
			this.propositions1.add(proposition1);
			this.propositions2.add(proposition2);
			this.propositionPanel1.add(proposition1);
			this.propositionPanel2.add(proposition2);
		}
	}*/
	
	public void initializeGalaxy() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (!clock.isRunning()) {
			clock.start();
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
	
	private static class Animate {
		
		private int numberOfFrames; //number of frames the object has to be animated
		private int x; //current position on X axis of the object
		private int y; //current position on Y axis of the object
		private int dx; //distance on X axis on each step
		private int dy; //distance on Y axis on each step
		
		public Animate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void nextStep() {
			this.x = this.x + dx;
			this.y = this.y + dy;
		}
		
		public void goTo(int toX, int toY, int numberFrames) {
			this.numberOfFrames = numberFrames ;
			this.dx = (this.x - toX) / this.numberOfFrames;
			this.dy = (this.y - toY) / this.numberOfFrames;
		}
		
	}
	
	
	/*
	 * 
	 * Test class IGGame
	 */
	public static void main(String args[]) {
		Modele modele = new Modele();
		//IGGame game = new IGGame(modele);
	}

	
}
