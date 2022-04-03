package view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import app.config.Utilities;

public class FloorView {
	
	Image floor;
	int dimX;
	int dimY;
	
	public FloorView() {
		dimX = Utilities.DIM_X_TILE;
		dimY = Utilities.DIM_Y_TILE;
		try {
			floor = ImageIO.read(getClass().getResourceAsStream("/resources/floor.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "errore nella gestione della icona di donkey kong", "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
