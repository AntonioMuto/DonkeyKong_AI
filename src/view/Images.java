package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Images {

 private Image logo;
 private Image dkm1;
 private Image dkm2;
 private Image bonus;
 private Image gameOver; 
 private Image win; 
 
 
 
	public Images(){
		 try {
			this.dkm1 = ImageIO.read(getClass().getResourceAsStream("/resources/dk1.png"));
			this.dkm2 = ImageIO.read(getClass().getResourceAsStream("/resources/dk2.png"));
			this.logo = ImageIO.read(getClass().getResourceAsStream("/resources/logo.png"));
			this.bonus = ImageIO.read(getClass().getResourceAsStream("/resources/bonus.png"));
			this.gameOver = ImageIO.read(getClass().getResourceAsStream("/resources/gameOver.png"));
			this.win = ImageIO.read(getClass().getResourceAsStream("/resources/win.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	}
	
	
 public Image getDk1() {
	return dkm1;
 }
 
 public Image getDk2() {
		return dkm2;
 }

 public Image getLogo() {
	return logo;
 }
 
 public Image getBonus() {
	return bonus;
}
 public Image getGameOver() {
	return gameOver;
}
 public Image getWin() {
	return win;
}
 
}
