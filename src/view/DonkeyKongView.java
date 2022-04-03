package view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import app.config.Utilities;

public class DonkeyKongView {
	
	Image dKStart1;
	Image dKStart2;
	int dimX;
	int dimY;
	
	public DonkeyKongView() {
		dimX = Utilities.DIM_DONKEY;
		dimY = Utilities.DIM_DONKEY;
		try {
			dKStart1 = ImageIO.read(getClass().getResourceAsStream("/resources/dkStart1.png"));
			dKStart2 = ImageIO.read(getClass().getResourceAsStream("/resources/dkStart2.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "errore nella gestione della icona di donkey kong", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
