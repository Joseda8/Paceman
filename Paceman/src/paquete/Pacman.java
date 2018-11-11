package paquete;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Pacman {
	private double x = 223;
	private double y = 433;
	private double xa = 0;
	private double ya = 0;
	
	private double speed=2;
	private Game game;
	BufferedImage pacman_image;
	
	private Vector<Rectangle> bounds;
	PacDots pac_dots = new PacDots();
	
	public Pacman(Game game) {
		this.game = game;
		this.bounds = game.set_bounds();
		pac_dots.setBounds(bounds);
		pac_dots.set_dots();
		
		try {
			load_image();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load_image() throws IOException {
		pacman_image = ImageIO.read(new File("C:/Users/jdmon/OneDrive/Escritorio/Paceman File/Paceman/src/paquete/pacman.png"));
	}
	
	public void move() {
		if(!collision()){
			if(x + xa <= 17 && y + ya >= 240 && y + ya < 290){
				x = 430 + xa;
				y = y + ya;
			}
			if(x + xa > 439 && y + ya >= 250 && y + ya < 300){
				x = xa + 15;
				y = y + ya;
			}
			if(x + xa > 6 && x + xa < 440) {
				x = x + xa;
			}
			if(y + ya > 6 && y + ya < 580) {
				y = y + ya;
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println(x);
			System.out.println(y);
			System.out.println("");
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			xa = speed*-1;
			ya = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			xa = speed;
			ya = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			ya = speed*-1;
			xa = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			ya = speed;
			xa = 0;
		}
	}
	
	private boolean collision() {
		for(int i=0; i<bounds.size(); i++) {
			if(bounds.get(i).intersects(getBounds())) {
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int ) (x+xa), (int) (y+ya), 20, 20);
	}

	public void paint(Graphics2D g){
		g.drawImage(pacman_image, (int ) x, (int) y, null);
		pac_dots.paint(g);
	}
	
	public void set_speed(int speed) {
		this.speed = speed;
	}
}