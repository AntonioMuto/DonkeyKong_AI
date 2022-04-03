package app;

import java.util.Iterator;

import javax.swing.JFileChooser;

import controller.MovementController;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import model.BeAbleClimbDown;
import model.BoxHigh;
import model.BoxLower;
import model.BoxesDiff;
import model.ClimbingUP;
import model.CordX;
import model.CordY;
import model.Direction;
import model.Game;
import model.GhostLevel;
import model.GhostNearSX;
import model.GhostTile;
import model.Jumping;
import model.Level;
import model.Map;
import model.Mario;
import model.MarioTile;
import model.Move;
import model.Movement;
import model.NearestStairsDown;
import model.PeachTile;
import model.StairsDiff;
import model.StairsDiffDown;
import model.TypeTile;
import model.GhostNearDX;
import model.Precedente;
import view.GamePanel;

public class DLVRun {

	private static Handler handler;
	MovementController controller;

	public DLVRun(MovementController controller) throws Exception {
		this.controller = controller;
		loop();
		// executeDLV();
	}

	public void executeDLV() throws Exception {
		// OptionDescriptor option = new OptionDescriptor("-n 0");
		InputProgram variableProgram = new ASPInputProgram();
		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));
		// handler.addOption(option);
		ASPMapper.getInstance().registerClass(Mario.class);
		ASPMapper.getInstance().registerClass(Map.class);
		ASPMapper.getInstance().registerClass(Move.class);
		ASPMapper.getInstance().registerClass(Movement.class);
		ASPMapper.getInstance().registerClass(MarioTile.class);
		ASPMapper.getInstance().registerClass(GhostTile.class);
		ASPMapper.getInstance().registerClass(Level.class);
//		ASPMapper.getInstance().registerClass(BoxHigh.class);
//		ASPMapper.getInstance().registerClass(BoxLower.class);
//		ASPMapper.getInstance().registerClass(BoxesDiff.class);
//		ASPMapper.getInstance().registerClass(StairsDiff.class);
		ASPMapper.getInstance().registerClass(CordX.class);
		ASPMapper.getInstance().registerClass(CordY.class);
		ASPMapper.getInstance().registerClass(Jumping.class);
//		ASPMapper.getInstance().registerClass(GhostNearSX.class);
//		ASPMapper.getInstance().registerClass(GhostLevel.class);
		ASPMapper.getInstance().registerClass(GhostNearDX.class);
		ASPMapper.getInstance().registerClass(Precedente.class);
		ASPMapper.getInstance().registerClass(TypeTile.class);
//		ASPMapper.getInstance().registerClass(BeAbleClimbDown.class);

		if (Game.getInstance().getMario().isjumping) {
			variableProgram.addObjectInput(new Jumping(1));
		} else if (!Game.getInstance().getMario().isjumping) {
			variableProgram.addObjectInput(new Jumping(0));
		}

		for (int i = 1; i < 7; i++) {
			// System.out.println("level(" + i + ")." );
			variableProgram.addObjectInput(new Level(i));
		}

		for (int i = 0; i < 25; i++) {
			variableProgram.addObjectInput(new CordX(i));
		}

		for (int i = 0; i < 32; i++) {
			variableProgram.addObjectInput(new CordY(i));
		}

		for (int i = 0; i < 9; i++) {
			variableProgram.addObjectInput(new TypeTile(i));
		}

		int[][] map = new int[32][25];
		int level = 0;
		map = Game.getInstance().getMap();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (i >= 27 && i <= 32)
					level = 1;
				else if (i >= 22 && i <= 26)
					level = 2;
				else if (i >= 17 && i <= 21)
					level = 3;
				else if (i >= 12 && i <= 16)
					level = 4;
				else if (i >= 7 && i <= 11)
					level = 5;
				else if (i >= 0 && i <= 6)
					level = 6;
				Map m = new Map(j, i, map[i][j], level);
				// System.out.println("map(" + j + ","+ i + "," + m.getType() + "," +
				// m.getLevel() + ")." );
				variableProgram.addObjectInput(m);
			}
		}
		for (int i = 0; i < 5; i++) {
			// System.out.println("move(" + i + ")." );
			variableProgram.addObjectInput(new Move(i));
		}

		variableProgram.addObjectInput(new Precedente(Game.getInstance().precedente));

		int direction = 0;
		for (int i = 0; i < Game.getInstance().getGhosts().size(); i++) {
			if (Game.getInstance().getGhosts().get(i).getD() == Direction.right)
				direction = 1;
			else if (Game.getInstance().getGhosts().get(i).getD() == Direction.left)
				direction = 0;
			variableProgram.addObjectInput(new GhostTile(Game.getInstance().getGhosts().get(i).getX(),
					Game.getInstance().getGhosts().get(i).getY(), direction,
					Game.getInstance().getGhosts().get(i).getClimbStatus()));
		}

		// if(Game.getInstance().getGhosts().size() > 0) {
		// variableProgram.addObjectInput(new
		// GhostTile(Game.getInstance().getGhosts().get(0).getX(),Game.getInstance().getGhosts().get(0).getY()));
		// }

		// MarioTile m = new
		// MarioTile(Game.getInstance().mario.getX(),Game.getInstance().mario.getY());
		int azione = 0;
		int directionMario = 7;
		int invincible = 100;
		
		if (!Game.getInstance().mario.isInvincible)
			invincible = 0;
		if (Game.getInstance().mario.isInvincible)
			invincible = 1;
		if (Game.getInstance().mario.isClimbingUp())
			azione = 1;
		if (Game.getInstance().mario.isClimbingDown())
			azione = 2;
		if (Game.getInstance().getMario().getDirection() == Direction.right)
			directionMario = 1;
		if (Game.getInstance().getMario().getDirection() == Direction.left)
			directionMario = 0;
		MarioTile mario = new MarioTile(Game.getInstance().mario.getX(), Game.getInstance().mario.getY(), azione,
				directionMario,invincible);
		// System.out.println("mariotile(" + mario.getX() + "," + mario.getY() + "," +
		// mario.getLevel() + "," + azione + ")." );
		variableProgram.addObjectInput(mario);
		variableProgram.addFilesPath("src/resources/DLV/DonkeyKong");

		handler.addProgram(variableProgram);
		// RISPOSTA
		Output output = handler.startSync();
		AnswerSets answers = (AnswerSets) output;
		int n = 0;
		for (AnswerSet a : answers.getAnswersets()) {
			try {
				for (Object obj : a.getAtoms()) {
					if (obj instanceof Movement) {
						Movement movement = (Movement) obj;
						if (movement.getX() == 0 && n != 1) {
							System.out.println("MOVE LEFT");
							controller.moveLeft();
						} else if (movement.getX() == 1 && n != 1) {
							System.out.println("MOVE RIGHT");
							controller.moveRight();
						} else if (movement.getX() == 2 && n != 1) {
							System.out.println("JUMP");
							n++;
							controller.jump();
						} else if (movement.getX() == 3 && n != 1) {
							System.out.println("UP");
							controller.moveUp();
						} else if (movement.getX() == 4 && n != 1) {
							System.out.println("DOWN");
							controller.moveDown();
						} else if (movement.getX() == 5 && n != 1) {
							System.out.println("STILL");
							controller.standStill();
						}
						Game.getInstance().precedente = movement.getX();
					}
					if (obj instanceof MarioTile) {
						MarioTile marios = (MarioTile) obj;
						// System.out.println("Mario Tile: " + marios.getX() + " " + marios.getY() + " "
						// + marios.getLevel() + " " + marios.getAzione() + " " +
						// marios.getDirection());
//						System.out.println("Mario Tile: " + marios.getX() + " " + marios.getY() + " "
//								+ marios.getLevel() + " " + marios.getAzione() + " " + marios.getDirection());
					}
					if (obj instanceof GhostTile) {
						GhostTile ghost = (GhostTile) obj;
//						 System.out.println("Ghost: " + ghost.getX() + " " + ghost.getY() + " " +
//						 ghost.getDirection() + " " + ghost.getLevel() + " " +
//						 ghost.getClimbStatus());
//						if (ghost.getDirection() == 0)
//							System.out.println("Ghost: " + ghost.getX() + " " + ghost.getY() + " " + "LEFT" + " "
//									+ ghost.getLevel() + " " + ghost.getClimbStatus());
//						if (ghost.getDirection() == 1)
//							System.out.println("Ghost: " + ghost.getX() + " " + ghost.getY() + " " + "RIGHT" + " "
//									+ ghost.getLevel() + " " + ghost.getClimbStatus());
					}
					if (obj instanceof GhostNearDX) {
						GhostNearDX b = (GhostNearDX) obj;
//						if(b.getN() != 0)
//						System.out.println("GHOST RIGHT: " + b.getN());
					}
					if (obj instanceof GhostNearSX) {
						GhostNearSX b = (GhostNearSX) obj;
//						if(b.getX() != 0)
//						System.out.println("BOX HIGH: " + b.getX());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void loop() {
		new Thread() {
			@Override
			public void run() {
				while (!Game.getInstance().end) {
					try {
						executeDLV();
						// sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

}
