package view;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import app.DLVRun;
import app.GameFrame;
import model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 5073935177336492373L;
	
	public PanelChanger changer;
	public int state = 0;
	private Images img;
	
	public MenuPanel(PanelChanger changer) {
      this.img = new Images();
      this.changer = changer;
      Game.getInstance().life = 3;
      Game.getInstance().bonus = 60000;
      Game.getInstance().score = 0;
	  setBackground(Color.black); 
	  JButton play = new JButton("PLAY");
	  JButton exit = new JButton ("EXIT");
	  commandPlay(play);
	  commandExit(exit);
	  play.setBackground(Color.black);
	  play.setForeground(Color.WHITE);
	  exit.setBackground(Color.black);
	  exit.setForeground(Color.WHITE);
	  this.setLayout(null);
	  play.setBounds(450, 450, 80, 40);
	  exit.setBounds(550, 450, 80, 40);
	  this.add(play);
	  this.add(exit);
	  this.setFocusable(true);
	  dkStart();
	}
	
	public void commandPlay(JButton b) {
	 b.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Game.getInstance().setEnd(false);
			Game.getInstance().setScore(0);
			Game.getInstance().setBonus(60000);
			Game.getInstance().showMap();
			Game.getInstance().getMario().isDead = false;
			Game.getInstance().getGhosts().clear();
			try {
				new DLVRun(GameFrame.mc);
			} catch (Exception e1) {
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
				System.exit(0);
			}
		 });
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(state == 0)
		 g.drawImage(img.getDk1(), 400,200,300,200, null);
		else
		 g.drawImage(img.getDk2(), 400,200,300,200, null);
		 g.drawImage(img.getLogo(), 400,0,300,200, null);
    }

	public void dkStart() {
		new Thread() {
			  boolean update = true;
				@Override
				public void run() {
				   while(update) {	
					   if(state == 0) {
						state = 1;
					    repaint();
					   }
					   else {
						state = 0;
					    repaint();
					   }
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						try {
							Thread.sleep(60);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				   }
				};
			}.start();
	}
	
	
}
