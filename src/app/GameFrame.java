package app;

import javax.swing.JButton;
import javax.swing.JFrame;

import app.config.Utilities;
import controller.MovementController;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.platforms.desktop.DesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import model.Game;
import view.DeathPanel;
import view.GamePanel;
import view.MenuPanel;
import view.PanelChanger;
import view.WinPanel;

import java.awt.*;

public class GameFrame extends JFrame implements PanelChanger {

	GamePanel gp;
	MenuPanel mp;
	DeathPanel dp;
	WinPanel wp;
	public static SoundLoader s;
	public static SoundLoader death;
	public static SoundLoader walking;
	public static MovementController mc;
	Utilities u = new Utilities();

	public static void main(String[] args) throws Exception {
		new GameFrame();
	}

	public GameFrame() throws Exception {
		setTitle("Donkey Kong");
		setSize(Utilities.WIDTH_SIZE, Utilities.HEIGHT_SIZE);
		this.gp = new GamePanel(this);
		this.dp = new DeathPanel(this);
		this.mp = new MenuPanel(this);
		this.wp = new WinPanel(this);
		mc = new MovementController(gp);
		getContentPane().setBackground(Color.black);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		showMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gp.addKeyListener(mc);
		GameLoop gameLoop = new GameLoop(mc);
		Thread t = new Thread(gameLoop);
		t.start();
	}

	@Override
	public void showMenu() {
		getContentPane().removeAll();
		getContentPane().add(mp);
		revalidate();
		mp.requestFocus();
		mp.repaint();
	}

	@Override
	public void showGame() {
		getContentPane().removeAll();
		getContentPane().add(gp);
		revalidate();
		initAllSounds();
		gp.requestFocus();
		gp.updateLoop();
		Game.getInstance().time();
		Game.getInstance().updateFall();
		gp.repaint();
	}

	@Override
	public void showDeath() {
		getContentPane().removeAll();
		getContentPane().add(dp);
		revalidate();
		if (Game.getInstance().life > 0) {
			dp.add(dp.getPlay());
			dp.getExit().setBounds(580, 450, 80, 40);
			dp.add(dp.getExit());
		}
		else {
			dp.remove(dp.getPlay());
			dp.getExit().setBounds(515, 450, 80, 40);
		}
		dp.requestFocus();
		dp.repaint();
	}
	
	@Override
	public void showWin() {
		getContentPane().removeAll();
		getContentPane().add(wp);
		revalidate();
		wp.add(wp.getExit());
		wp.requestFocus();
		wp.repaint();
	}

	public void initAllSounds() {
		this.s = new SoundLoader("soundtrack.wav");
		this.death = new SoundLoader("death.wav");
		this.walking = new SoundLoader("walking.wav");
		s.start();
		s.loop();
		s.reduceVolume();
	}
	
	public MovementController getMovementController() {
		return mc;
	}

}
