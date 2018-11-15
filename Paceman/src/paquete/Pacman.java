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
	
	BufferedImage pacman_image;
	BufferedImage pacman_lifes_image;
	private int width_pacman=25;
	private int heigth_pacman=25;

	private boolean power_on=false;
	private double speed=3;
	private int lifes=2;
	
	private Vector<Rectangle> bounds;
	
	private Game game;
	PacDots pac_dots;
	Fruit fruit;
	
	public Pacman(Game game) {
		this.game = game;
		this.bounds = game.set_bounds();
		this.pac_dots=game.pac_dots;
		this.fruit=game.fruit;
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
		pacman_lifes_image = ImageIO.read(new File("C:/Users/jdmon/OneDrive/Escritorio/Paceman File/Paceman/src/paquete/pacman_life.png"));
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
		if(new Rectangle(x, y, width_pacman, heigth_pacman).intersects(new Rectangle(220, 290, 30, 30)) && fruit.get_is_on()) {
			fruit.setIs_on(false);
			game.setScore(game.getScore()+100);
		}else {
			for(int i=0; i<pac_dots.getPac_dots().size(); i++) {
				if(new Rectangle(x, y, width_pacman, heigth_pacman).intersects(new Rectangle(pac_dots.getPac_dots().get(i).getPos_x(), pac_dots.getPac_dots().get(i).getPos_y(), 20, 20))
						&& !pac_dots.getPac_dots().get(i).isEated()){
					pac_dots.getPac_dots().get(i).setEated(true);
					if(pac_dots.getPac_dots().get(i).is_power()) {
						this.power_on=true;
						game.setScore(game.getScore()+10);
						pacman_power t1 = new pacman_power (game);
						t1.start();
					}else {
						game.setScore(game.getScore()+1);
					}
				}
			}
		}
	}
	
	private boolean ghost_collision() {
		for(int i=0; i<game.ghosts.size(); i++) {
			if(game.ghosts.get(i).getBounds_without_moving().intersects(getBounds_without_moving())) {
				if(this.power_on) {
					game.ghosts.get(i).setX(193);
					game.ghosts.get(i).setY(224);
					game.setScore(game.getScore()+50);
				}else if(lifes==0){
					game.game_over();
				}else {
					restart_level();
				}
				return true;
			}
		}
		return false;
	}
	
	private void restart_level() {
		lifes--;
		x=223;
		y=433;
		pac_dots.set_dots();
		for(int i=0; i<game.ghosts.size(); i++) {
			game.ghosts.get(i).restart_position();
		}
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
		return new Rectangle((int ) (x+xa), (int) (y+ya), width_pacman, heigth_pacman);
	}
	
	public Rectangle getBounds_without_moving() {
		return new Rectangle((int )x, (int)y, width_pacman, heigth_pacman);
	}

	public void paint(Graphics2D g){
		g.drawImage(pacman_image, (int ) x, (int) y, null);
		draw_lifes(g);
	}
	
	private void draw_lifes(Graphics2D g) {
		for(int i=0; i<lifes; i++) {
			g.drawImage(pacman_lifes_image, 250+20*i, 650, null);
		}
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

	public boolean isPower_on() {
		return power_on;
	}

	public void setPower_on(boolean power_on) {
		this.power_on = power_on;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	
	
	
}