package Window;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Music.BackgroundMusic;

public class PanelMusicVolume extends JPanel implements ActionListener {
	
	private BackgroundMusic music;
	private Boolean isOn;
	
	
	public PanelMusicVolume(BackgroundMusic m) {
		this.music = m;
		if (this.music.getVolume() > 0) {
			this.isOn = true;
		}
		else {
			this.isOn = false;
		}
		this.repaint();
		this.revalidate();
	}
	
	public void paintComponent(Graphics g) {
		try {
			if (this.isOn) {
				Image img = ImageIO.read(new File("Graphics" + File.separator + "volumeOn.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
			else {
				Image img = ImageIO.read(new File("Graphics" + File.separator + "volumeOff.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		this.music.volumeOnOff();
		this.repaint();
		this.revalidate();
	}
	
}
