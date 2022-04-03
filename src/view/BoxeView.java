package view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import app.config.Utilities;

public class BoxeView {
	
	Image boxe;
	int dimX;
	int dimY;
	
	public BoxeView() {
		dimX = Utilities.DIM_X_TILE;
		dimY = Utilities.DIM_Y_TILE;
		try {
			boxe = ImageIO.read(getClass().getResourceAsStream("/resources/boxe.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "errore nella gestione delle boxe", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}
}
