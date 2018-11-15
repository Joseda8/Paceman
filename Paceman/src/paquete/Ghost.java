package paquete;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Ghost extends GhostBacktracking {
	private double x = 193;
	private double y = 224;
	private double xa = 0;
	private double ya = 0;
	
	private int width_ghost=25;
	private int heigth_ghost=25;
	
	private int ghost_num;
	
	private int count_control_move_var;
	private int control_move_var=2;
	
	private double speed=1;
	BufferedImage ghost_image;
	private Game game;
	//int maze[][] = GhostBacktracking.fill_matriz(17, 13);
	
	private Vector<Rectangle> bounds;
	
	public Ghost(Game game, int ghost_num) {
		this.game = game;
		this.bounds = game.set_bounds();
		this.ghost_num = ghost_num;

		try {
			load_image(ghost_num);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load_image(int i) throws IOException {
		switch(i) {
			case 0:
				ghost_image = ImageIO.read(new File("C:/Users/jdmon/OneDrive/Escritorio/Paceman File/Paceman/src/paquete/Blinky.png"));
				break;
			case 1:
				ghost_image = ImageIO.read(new File("C:/Users/jdmon/OneDrive/Escritorio/Paceman File/Paceman/src/paquete/Clyde.png"));
				break;
			case 2:
				ghost_image = ImageIO.read(new File("C:/Users/jdmon/OneDrive/Escritorio/Paceman File/Paceman/src/paquete/Inky.png"));
				break;
			case 3:
				ghost_image = ImageIO.read(new File("C:/Users/jdmon/OneDrive/Escritorio/Paceman File/Paceman/src/paquete/Pinky.png"));
				break;
			case 4:
				ghost_image = ImageIO.read(new File("C:/Users/jdmon/OneDrive/Escritorio/Paceman File/Paceman/src/paquete/scary.png"));
		}
	}
	
	public void move() {
		if(ghost_num==0) {
			way_finding();
		}else if(ghost_num==1){
			way_finding1();
		}else{
			way_finding((int) (Math.random() * (ghost_num*10+15)));
		}
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
	}
	
	private void way_finding1() {
		if(count_control_move_var !=75) {
			switch(control_move_var) {
				case 0:
					xa = speed*-1;
					ya = 0;
					break;
				case 1:
					xa = speed;
					ya = 0;
					break;
				case 2:
					ya = speed*-1;
					xa = 0;
					break;
				case 3:
					ya = speed;
					xa = 0;
					break;
			}
			count_control_move_var++;
		}else {
			count_control_move_var=0;
			control_move_var=(int) (Math.random() * 4);
		}
	}
	
	private void way_finding(int num_rand) {
		int num_rand_2=(int) (Math.random() * 3);
		switch(num_rand) {
			case 0:
				if(x<game.pacman.getX()) {
					if(wall_collision(x+speed, y)) {
						if(num_rand_2==0) {
							xa = speed*-1;
							ya = 0;
						}else if(num_rand_2==1) {
							xa = 0;
							ya = speed;
						}if(num_rand_2==2) {
							xa = 0;
							ya = speed*-1;
						}
					}else {
						xa = speed;
						ya = 0;
					}
				}else if(x>game.pacman.getX()) {
					if(wall_collision(x-speed, y)) {
						if(num_rand_2==0) {
							xa = speed;
							ya = 0;
						}else if(num_rand_2==1) {
							xa = 0;
							ya = speed;
						}if(num_rand_2==2) {
							xa = 0;
							ya = speed*-1;
						}
					}else {
						xa = speed*-1;
						ya = 0;
					}
				}else if(y<game.pacman.getY()) {
					if(wall_collision(x, y+speed)) {
						if(num_rand_2==0) {
							xa = speed*-1;
							ya = 0;
						}else if(num_rand_2==1) {
							xa = speed;
							ya = 0;
						}if(num_rand_2==2) {
							xa = 0;
							ya = speed*-1;
						}
					}else {
						ya = speed;
						xa = 0;
					}
				}else if(y>game.pacman.getY()) {
					if(wall_collision(x, y-speed)) {
						if(num_rand_2==0) {
							xa = speed*-1;
							ya = 0;
						}else if(num_rand_2==1) {
							xa = 0;
							ya = speed;
						}if(num_rand_2==2) {
							xa = speed;
							ya = 0;
						}
					}else {
						ya = speed*-1;
						xa = 0;
					}
				}
			case 1:
				if(x<game.pacman.getX() && !wall_collision(x+speed, y)) {
					xa = speed;
					ya = 0;
				}else if(x>game.pacman.getX() && !wall_collision(x-speed, y)) {
					xa = speed*-1;
					ya = 0;
				}else if(y<game.pacman.getY() && !wall_collision(x, y+speed)) {
					ya = speed;
					xa = 0;
				}else if(y>game.pacman.getY() && !wall_collision(x, y-speed)) {
					ya = speed*-1;
					xa = 0;
				}
		}
		
		/*
		if(x<game.pacman.getX() && !wall_collision(x+speed, y)) {
			xa = speed;
			ya = 0;
		}else if(x>game.pacman.getX() && !wall_collision(x-speed, y)) {
			xa = speed*-1;
			ya = 0;
		}else if(y<game.pacman.getY() && !wall_collision(x, y+speed)) {
			ya = speed;
			xa = 0;
		}else if(y>game.pacman.getY() && !wall_collision(x, y-speed)) {
			ya = speed*-1;
			xa = 0;
		}
		*/
	}
	
	private void way_finding() {
		if(x<game.pacman.getX() && !wall_collision(x+speed, y)) {
			xa = speed;
			ya = 0;
		}else if(x>game.pacman.getX() && !wall_collision(x-speed, y)) {
			xa = speed*-1;
			ya = 0;
		}else if(y<game.pacman.getY() && !wall_collision(x, y+speed)) {
			ya = speed;
			xa = 0;
		}else if(y>game.pacman.getY() && !wall_collision(x, y-speed)) {
			ya = speed*-1;
			xa = 0;
		}
	}
	
	private boolean wall_collision(double x2, double y2) {
		for(int i=0; i<bounds.size(); i++) {
			if(bounds.get(i).intersects(new Rectangle((int) (x2), (int) (y2), width_ghost, heigth_ghost))) {
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
		return new Rectangle((int ) (x+xa), (int) (y+ya), width_ghost, heigth_ghost);
	}
	
	public Rectangle getBounds_without_moving() {
		return new Rectangle((int )x+5, (int)y, width_ghost-10, heigth_ghost-10);
	}
	
	public void paint(Graphics2D g){
		g.drawImage(ghost_image, (int ) x, (int) y, null);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth_ghost() {
		return width_ghost;
	}

	public int getHeigth_ghost() {
		return heigth_ghost;
	}
	
	public void restart_position() {
		x = 193;
		y = 224;
	}
}
