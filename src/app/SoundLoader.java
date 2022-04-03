package app;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class SoundLoader {

	private AudioInputStream audioIn;
	private Clip cTheme;

	public SoundLoader(String name) {
		try {
			audioIn = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sounds/" + name));
			cTheme = AudioSystem.getClip();
			cTheme.open(audioIn);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			cTheme = null;
			JOptionPane.showMessageDialog(null, "errore nella gestione dei file audio", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void loop() {
		if (cTheme != null) {
			cTheme.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void start() {
		if (cTheme != null) {
			if (cTheme.getFramePosition() == cTheme.getFrameLength())
				cTheme.setFramePosition(0);
			cTheme.start();
		}
	}

	public void stop() {
		if (cTheme != null) {
			cTheme.stop();
		}
	}

	public void restart() {
		if (cTheme != null) {
			cTheme.stop();
			cTheme.setFramePosition(0);
			cTheme.start();
		}
	}

	public void reduceVolume() {
		if (cTheme != null) {
			FloatControl gainControl = (FloatControl) cTheme.getControl(FloatControl.Type.MASTER_GAIN);
			float value = gainControl.getValue();
			value -= 30f;
			if (value >= gainControl.getMinimum())
				gainControl.setValue(value);
		}
	}
	
}
