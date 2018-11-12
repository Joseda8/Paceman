package paquete;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

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
	Fruit fruit = new Fruit();
	
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
	
	public void move(){
		if(!wall_collision()){
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
		eat_dot((int)x, (int)y);
		ghost_collision();
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println(((int)x)/35);
			System.out.println(((int)y)/35);
			System.out.println("");
			pac_dots.getPac_dots().get(0).set_is_power(true);
			fruit.setIs_on(true);
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
	
	public void keyReleased(KeyEvent e) {
		xa = 0;
		ya = 0;
	}
	
	private void eat_dot(int x, int y) {
		if(new Rectangle(x, y, 20, 20).intersects(new Rectangle(220, 290, 30, 30)) && fruit.get_is_on()) {
			fruit.setIs_on(false);
		}else {
			for(int i=0; i<pac_dots.getPac_dots().size(); i++) {
				if(new Rectangle(x, y, 20, 20).intersects(new Rectangle(pac_dots.getPac_dots().get(i).getPos_x(), pac_dots.getPac_dots().get(i).getPos_y(), 20, 20))){
					pac_dots.getPac_dots().get(i).setEated(true);
				}
			}
		}
	}
	
	private boolean ghost_collision() {
		for(int i=0; i<game.ghosts.size(); i++) {
			if(new Rectangle((int)game.ghosts.get(i).getX(), (int)game.ghosts.get(i).getY(), 15, 15).intersects(getBounds())) {
				game.game_over();
				return true;
			}
		}
		return false;
	}
	
	private boolean wall_collision() {
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
		fruit.paint(g);
	}
	
	public void set_speed(int speed) {
		this.speed = speed;
	}

	public double getX() {
		return x;
		//return ((int)x)/35;
	}

	public double getY() {
		return y;
		//return ((int)y)/35;
	}
	
	
}