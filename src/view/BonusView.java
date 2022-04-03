package view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import app.config.Utilities;

public class BonusView {
	
	Image bonusHammer;
	int dimX;
	int dimY;
	
	public BonusView() {
		dimX = Utilities.DIM_BONUS;
		dimY = Utilities.DIM_BONUS;
		try {
			bonusHammer = ImageIO.read(getClass().getResourceAsStream("/resources/hammer.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "errore nella gestione della icona di martello", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}
}
