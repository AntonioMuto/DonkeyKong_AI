package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.time.Instant;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

import app.config.Utilities;
import model.Bonus;
import model.Boxe;
import model.Direction;
import model.Floor;
import model.Game;
import model.Ghost;
import model.Ladder;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -4084599839941304788L;

	public static PanelChanger p;
	private DonkeyKongView dk = new DonkeyKongView();
	private FloorView fv = new FloorView();
	private LadderView lv = new LadderView();
	private BonusView bv = new BonusView();
	private GhostView gv = new GhostView();
	private BoxeView boxv = new BoxeView();
	private static MarioView mv = new MarioView();
	private PrincessView pv = new PrincessView();
	private static int state = 0;
	private Images image;

	public GamePanel(PanelChanger p) {
		this.p = p;
		this.image = new Images();
		setBackground(Color.black);
		dkStart();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(
					Font.createFont(Font.TRUETYPE_FONT, new File("Font/donkey-kong-classics-nes-extended.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		var g2d = (Graphics2D) g;
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setFont(new Font("Donkey Kong Classics (NES) (Extended)", Font.PLAIN, 30));
		g2.setColor(Color.red);
		g2.drawString("SCORE", 900, 50);
		g2d.drawImage(image.getBonus(), 170, 10, 140, 90, null);
		if (Instant.now().getEpochSecond() % 2 == 0)
			g2.drawString("UP", 50, 50);
		for (int i = 0; i < Game.getInstance().life; i++) {
			g2.drawImage(mv.imagesDX.get(2), 40 + (30 * i), 60, 30, 30, null);
		}
		g2.setColor(Color.white);
		g2.drawString(String.valueOf(Game.getInstance().score), 960, 100);
		g2.setFont(new Font("Donkey Kong Classics (NES) (Extended)", Font.PLAIN, 17));
		g2.drawString(String.valueOf(Game.getInstance().bonus), 190, 70);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		drawFloor(g2d);
		drawLadder(g2d);
		drawMario(g2d);
		drawDonkey(g2d);
		drawPrincess(g2d);
		drawBonus(g2d);
		drawBoxes(g2d);
		drawGhosts(g2d);
		g2d.setColor(Color.yellow);
//		g2d.drawRect(Game.getInstance().getMario().getX(), Game.getInstance().getMario().getY(), Utilities.DIM_X_MARIO,
//				Utilities.DIM_Y_MARIO);
//		g2d.drawRect(Game.getInstance().getMario().getX(),Game.getInstance().getMario().getY() + Utilities.DIM_Y_TILE * 2, Utilities.DIM_X_TILE,
//				Utilities.DIM_Y_TILE);
//		int x = (int) Game.getInstance().getDk().getRectangle().getX();
//		int y = (int) Game.getInstance().getDk().getRectangle().getY();
	}

	private void drawGhosts(Graphics2D g2d) {
		Vector<Ghost> ghosts = Game.getInstance().getGhosts();
		for (int i = 0; i < ghosts.size(); i++) {
			gv.setImage(ghosts.get(i).getD(), state);
			g2d.drawImage(gv.currentImage, ghosts.get(i).getX(), ghosts.get(i).getY(), gv.dimX, gv.dimY, null);
		}
	}

	// disegna il floor in base alle coordinate prese nel vector di floor generato
	// nel Game
	public void drawFloor(Graphics2D g2d) {
		Vector<Floor> floors = Game.getInstance().getFloors();
		for (int i = 0; i < floors.size(); i++) {
			g2d.drawImage(fv.floor, floors.get(i).getX(), floors.get(i).getY(), fv.dimX, fv.dimY, null);
		}
	}

	public void drawPrincess(Graphics2D g2d) {
		g2d.drawImage(pv.pStart1, Game.getInstance().princess.getX(), Game.getInstance().princess.getY(), pv.dimX,
				pv.dimY, null);
	}

	public void drawBonus(Graphics2D g2d) {
		for (Bonus b : Game.getInstance().getBonusH()) {
			g2d.drawImage(bv.bonusHammer, b.getX(), b.getY(), bv.dimX, bv.dimY, null);
		}
	}

	public void drawBoxes(Graphics2D g2d) {
		for (Boxe b : Game.getInstance().getBoxes()) {
			g2d.drawImage(boxv.boxe, b.getX(), b.getY(), boxv.dimX, boxv.dimY, null);
		}
	}
	
	public void drawDonkey(Graphics2D g2d) {
		if (state == 0) {
			g2d.drawImage(dk.dKStart1, Game.getInstance().dk.getX(), Game.getInstance().dk.getY(), dk.dimX, dk.dimY,
					null);
		} else {
			g2d.drawImage(dk.dKStart2, Game.getInstance().dk.getX(), Game.getInstance().dk.getY(), dk.dimX, dk.dimY,
					null);
		}
	}

	public void drawLadder(Graphics2D g2d) {
		Vector<Ladder> ladders = Game.getInstance().getLadder();
		for (int i = 0; i < ladders.size(); i++) {
			g2d.drawImage(lv.ladder, ladders.get(i).getX(), ladders.get(i).getY(), lv.dimX, lv.dimY, null);
		}
	}

	public void drawMario(Graphics2D g2d) {
		g2d.drawImage(mv.currentImage, Game.getInstance().getMario().getX(), Game.getInstance().getMario().getY(),
				mv.dimX, mv.dimY, null);
//		g2d.drawRect(Game.getInstance().getMario().getX(), Game.getInstance().getMario().getY(),
//				mv.dimX, mv.dimY);
	}

	public void dkStart() {
		new Thread() {
			boolean update = true;

			@Override
			public void run() {
				while (update) {
					if (state == 0) {
						state = 1;
						repaint();
					} else if (state == 1) {
						state = 0;
						repaint();
					}
					try {
						Thread.sleep(160);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	public void move() {
		mv.move();
	}

	public void turn() {
		mv.turn();
	}

	public void climb() {
		mv.climb();
	}

	public void jumpDx() {
		mv.jumpDx();
	}

	public void jumpSx() {
		mv.jumpSx();
	}

	public static void stop() {
		mv.stop();
	}

	public void update() {
		repaint();
	}

	public void updateLoop() {
		new Thread() {
			@Override
			public void run() {
				while (!Game.getInstance().end) {
					repaint();
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	public MarioView getMarioView() {
		return mv;
	}

	public static void Death() {
		p.showDeath();
	}
	
	public static void win() {
		p.showWin();
	}

}
