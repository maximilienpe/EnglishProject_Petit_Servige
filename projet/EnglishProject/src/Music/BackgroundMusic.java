package Music;

import java.io.*;
import javax.sound.sampled.*;
   
public class BackgroundMusic {
   
	private File music;
	private Clip clip;
	private AudioInputStream audioInput;
	private FloatControl volume;
	private float initialVolume;
	
	// Constructor
	public BackgroundMusic(String musicPath) throws LineUnavailableException {
		this.music = new File(musicPath);
		this.clip = AudioSystem.getClip();
		this.volume = null;
	}
   
	public void setMusic(String musicPath) {
		this.clip.close();
		this.music = new File(musicPath);
	}
	
	/**
	 * play launch the music in music file
	 * @param loopOption if equals to infinite, the music will restart until stop
	 */
	public void play(String loopOption) {
		try {
			audioInput = AudioSystem.getAudioInputStream(this.music);
			clip.open(audioInput);
			clip.start();
			this.volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			this.initialVolume = this.volume.getValue();
			if (loopOption.equals("infinite")) {
				this.clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Just play a music load in file music
	 */
	public void play() {
		try {
			this.volume.setValue(initialVolume);
			audioInput = AudioSystem.getAudioInputStream(this.music);
			clip.open(audioInput);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
   
	public void stop() {
		if (this.clip.isRunning()) {
			this.clip.stop();
		}
	}
	
	public void stopFondu() {
		while(this.volume.getValue() > -10.0) {
			this.volume.setValue(-0.1f);
			System.out.println(this.volume.getValue());
		}
		//this.stop();	
	}
	
	public void volumeOnOff() {
		if (this.volume.getValue() > 0) {
			this.volume.setValue(0);
		} else {
			this.volume.setValue(initialVolume);
		}
	}
	
	public float getVolume() {
		return this.volume.getValue();
	}
 }
