package sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	private Clip clip;

	public void playClickSound() {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound.class.getResource("/sound/click.wav")));
			clip.start();
		}catch(Exception e) {
			System.out.println("Failed to load sound: " + e.getMessage());
		}
	}
}