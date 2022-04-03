package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

import javax.swing.JButton;
import javax.swing.JPanel;

import app.DLVRun;
import app.GameFrame;
import model.Game;

public class WinPanel extends JPanel {

	private static final long serialVersionUID = 5073935177336492373L;

	public PanelChanger changer;
	private Images img;
	JButton exit;

	public WinPanel(PanelChanger changer) {
		this.img = new Images();
		this.changer = changer;
		setBackground(Color.black);
		this.exit = new JButton("MENU'");
		this.commandExit(exit);
		exit.setBackground(Color.black);
		exit.setForeground(Color.WHITE);
		exit.setBounds(500, 500, 80, 40);
		this.setLayout(null);
		this.setFocusable(true);
	}

	public JButton getExit() {
		return exit;
	}

	public void commandExit(JButton b) {
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().setLife(3);
				Game.getInstance().setScore(0);
				Game.getInstance().setBonus(60000);
				changer.showMenu();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img.getWin(), 350, 120, 400, 300, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Donkey Kong Classics (NES) (Extended)", Font.PLAIN, 30));
		drawString(g);}

	public void drawString(Graphics g) {
		g.drawString("YOU WIN", 250, 60);
		g.drawString("Score " + Game.getInstance().bonus, 550, 60);
	}
}
