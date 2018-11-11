package paquete;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	
	private Vector<Rectangle> bounds;
	
	Pacman pacman = new Pacman(this);

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_E) {
					System.exit(0);
				}
				pacman.keyPressed(e);
			}
		});
		this.bounds=set_bounds();
		setFocusable(true);
	}
	
	private void move() {
		pacman.move();
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	    g2d.setColor(Color.BLUE);
		g2d.drawRect (0, 0, 465, 605);
		g2d.drawRect (5, 5, 455, 595);
		
		for(int i=0; i<bounds.size(); i++) {
			g2d.draw(bounds.get(i));
		}
		pacman.paint(g2d);
	}

	public Vector<Rectangle> set_bounds(){
		Vector<Rectangle> bounds = new Vector<Rectangle>(100);
		
		bounds.add(new Rectangle(40, 40, 35, 35));
		bounds.add(new Rectangle(110, 40, 35, 35));
		bounds.add(new Rectangle(145, 40, 35, 35));
		bounds.add(new Rectangle(215, 5, 35, 35));
		bounds.add(new Rectangle(215, 40, 35, 35));
		bounds.add(new Rectangle(285, 40, 35, 35));
		bounds.add(new Rectangle(320, 40, 35, 35));
		bounds.add(new Rectangle(390, 40, 35, 35));
		
		bounds.add(new Rectangle(40, 110, 35, 35));
		bounds.add(new Rectangle(110, 110, 35, 35));
		bounds.add(new Rectangle(110, 145, 35, 35));
		bounds.add(new Rectangle(110, 180, 35, 35));
		bounds.add(new Rectangle(145, 180, 35, 35));
		bounds.add(new Rectangle(110, 215, 35, 35));
		bounds.add(new Rectangle(180, 110, 35, 35));
		bounds.add(new Rectangle(215, 110, 35, 35));
		bounds.add(new Rectangle(215, 145, 35, 35));
		bounds.add(new Rectangle(215, 180, 35, 35));
		bounds.add(new Rectangle(250, 110, 35, 35));
		bounds.add(new Rectangle(320, 110, 35, 35));
		bounds.add(new Rectangle(320, 145, 35, 35));
		bounds.add(new Rectangle(285, 180, 35, 35));
		bounds.add(new Rectangle(320, 180, 35, 35));
		bounds.add(new Rectangle(320, 215, 35, 35));
		bounds.add(new Rectangle(390, 110, 35, 35));
		
		bounds.add(new Rectangle(5, 180, 35, 35));
		bounds.add(new Rectangle(5, 215, 35, 35));
		bounds.add(new Rectangle(40, 180, 35, 35));
		bounds.add(new Rectangle(40, 215, 35, 35));
		bounds.add(new Rectangle(390, 180, 35, 35));
		bounds.add(new Rectangle(390, 215, 35, 35));
		bounds.add(new Rectangle(425, 180, 35, 35));
		bounds.add(new Rectangle(425, 215, 35, 35));
		
		bounds.add(new Rectangle(5, 285, 35, 35));
		bounds.add(new Rectangle(5, 320, 35, 35));
		bounds.add(new Rectangle(40, 285, 35, 35));
		bounds.add(new Rectangle(40, 320, 35, 35));
		
		bounds.add(new Rectangle(110, 285, 35, 35));
		bounds.add(new Rectangle(110, 320, 35, 35));
		bounds.add(new Rectangle(320, 285, 35, 35));
		bounds.add(new Rectangle(320, 320, 35, 35));
		
		bounds.add(new Rectangle(180, 250, 35, 35));
		bounds.add(new Rectangle(215, 250, 35, 35));
		bounds.add(new Rectangle(250, 250, 35, 35));
		
		bounds.add(new Rectangle(180, 320, 35, 35));
		bounds.add(new Rectangle(215, 320, 35, 35));
		bounds.add(new Rectangle(250, 320, 35, 35));
		bounds.add(new Rectangle(215, 355, 35, 35));
		bounds.add(new Rectangle(215, 390, 35, 35));
		
		bounds.add(new Rectangle(390, 285, 35, 35));
		bounds.add(new Rectangle(390, 320, 35, 35));
		bounds.add(new Rectangle(425, 285, 35, 35));
		bounds.add(new Rectangle(425, 320, 35, 35));
		
		bounds.add(new Rectangle(40, 390, 35, 35));
		bounds.add(new Rectangle(40, 425, 35, 35));
		bounds.add(new Rectangle(390, 390, 35, 35));
		bounds.add(new Rectangle(390, 425, 35, 35));
		
		bounds.add(new Rectangle(110, 390, 35, 35));
		bounds.add(new Rectangle(145, 390, 35, 35));
		bounds.add(new Rectangle(285, 390, 35, 35));
		bounds.add(new Rectangle(320, 390, 35, 35));
		
		bounds.add(new Rectangle(110, 460, 35, 35));
		bounds.add(new Rectangle(110, 495, 35, 35));
		bounds.add(new Rectangle(110, 530, 35, 35));
		bounds.add(new Rectangle(40, 530, 35, 35));
		bounds.add(new Rectangle(75, 530, 35, 35));
		bounds.add(new Rectangle(145, 530, 35, 35));
		
		bounds.add(new Rectangle(180, 460, 35, 35));
		bounds.add(new Rectangle(215, 460, 35, 35));
		bounds.add(new Rectangle(215, 495, 35, 35));
		bounds.add(new Rectangle(215, 530, 35, 35));
		bounds.add(new Rectangle(250, 460, 35, 35));
		
		bounds.add(new Rectangle(320, 460, 35, 35));
		bounds.add(new Rectangle(320, 495, 35, 35));
		bounds.add(new Rectangle(285, 530, 35, 35));
		bounds.add(new Rectangle(320, 530, 35, 35));
		bounds.add(new Rectangle(355, 530, 35, 35));
		bounds.add(new Rectangle(390, 530, 35, 35));
		
		bounds.add(new Rectangle(40, 460, 35, 35));
		bounds.add(new Rectangle(390, 460, 35, 35));
		
		return bounds;
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Paceman");
		Game game = new Game();
		frame.add(game);
		frame.setSize(600, 720);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setBackground(Color.BLACK);
		
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}

}
