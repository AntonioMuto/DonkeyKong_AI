package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import app.DLVRun;
import app.GameFrame;
import model.Game;

public class DeathPanel extends JPanel {

	private static final long serialVersionUID = 5073935177336492373L;

	public PanelChanger changer;
	public int state = 0;
	private Images img;
	JButton play;
	JButton exit;

	public DeathPanel(PanelChanger changer) {
		this.img = new Images();
		this.changer = changer;
		this.play = new JButton("CONTINUE");
		this.commandPlay(play);
		play.setBackground(Color.black);
		play.setForeground(Color.WHITE);
		play.setBounds(450, 450, 120, 40);
		setBackground(Color.black);
		this.exit = new JButton("MENU'");
		this.commandExit(exit);
		exit.setBackground(Color.black);
		exit.setForeground(Color.WHITE);
		this.setLayout(null);
		exit.setBounds(580, 450, 80, 40);
		this.setFocusable(true);
	}

	public JButton getPlay() {
		return play;
	}

	public JButton getExit() {
		return exit;
	}
	
	public void commandPlay(JButton b) {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().setEnd(false);
				Game.getInstance().getGhosts().clear();
				Game.getInstance().getMario().isDead = false;
				Game.getInstance().setLife(Game.getInstance().life - 1);
				Game.getInstance().setScore(0);
				Game.getInstance().setBonus(60000);
				Game.getInstance().showMap();
				Game.getInstance().getGhosts().clear();
				try {
					new DLVRun(GameFrame.mc);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				changer.showGame();
			}

		});
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
		g.drawImage(img.getGameOver(), 350, 120, 400, 300, null);
	}

}
