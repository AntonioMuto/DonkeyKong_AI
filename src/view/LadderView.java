package view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import app.config.Utilities;

public class LadderView {

	Image ladder;
	int dimX;
	int dimY;
	
	public LadderView() {
		dimX = Utilities.DIM_X_TILE;
		dimY = Utilities.DIM_Y_TILE;
		try {
			ladder = ImageIO.read(getClass().getResourceAsStream("/resources/ladder.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "errore nella gestione della icona delle scale", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
