package view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import app.config.Utilities;

public class PrincessView {
	
	Image pStart1;
	Image pStart2;
	int dimX;
	int dimY;
	
	public PrincessView() {
		dimX = Utilities.DIM_PRINCESS;
		dimY = Utilities.DIM_PRINCESS;
		try {
			pStart1 = ImageIO.read(getClass().getResourceAsStream("/resources/princess1.png"));
			pStart2 = ImageIO.read(getClass().getResourceAsStream("/resources/princess2.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "errore nella gestione della icona di donkey kong", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}
}
