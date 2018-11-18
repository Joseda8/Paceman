package paquete;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Clase main que instancia al resto
 */

@SuppressWarnings("serial")
public class Game extends JPanel {
	
	private Vector<Rectangle> bounds;
	Pacman pacman;
	PacDots pac_dots = new PacDots();
	Fruit fruit = new Fruit();
	
	Server socket_pacman;
	
	ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	
	private int score = 0;
	private boolean sensor_game=false;

	//Constructor de la clase
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//pacman.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_E) {
					System.exit(0);
				}else if(e.getKeyCode() == KeyEvent.VK_S) {
					if(!sensor_game) {
						socket_move();
						sensor_game=true;
					}else{
						close_socket(socket_pacman);
						sensor_game=false;
					}
				}
				if(!sensor_game) {
					pacman.keyPressed(e);
				}
			}
		});
		this.bounds=set_bounds();
		add_ghost(0);
		add_ghost(1);
		add_ghost(2);
		add_ghost(3);
		pacman = new Pacman(this);
		setFocusable(true);
	}
	
	private void socket_move() {
		socket_pacman = new Server(this);
		socket_pacman.start();
	}
	
	private void close_socket(Server server) {
		try {
			server.close_socket();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Invoca los métodos de movimiento de pacman y de los fantasmas
	private void move() {
		pacman.move();
		for(int i=0; i<ghosts.size(); i++) {
			ghosts.get(i).move();
		}
	}
	
	//Invoca los métodos paint de cada clase necesaria
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
		pac_dots.paint(g2d);
		fruit.paint(g2d);
		paint_ghosts(g2d);
		paint_score(g2d);
	}
	
	//Dibujar el puntaje
	private void paint_score(Graphics2D g2d) {
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf("Puntaje: " + getScore()), 10, 665);
	}
	
	private void paint_ghosts(Graphics2D g2d) {
		for(int i=0; i<ghosts.size(); i++) {
			ghosts.get(i).paint(g2d);
		}
	}

	//Crea el mapa del juego
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
	
	//Método para agregar nuevos fantasmas
	//Recibe por parámetro el fantasma que se quiere dibujar (Blinky, Inky, Pinky y Clyde)
	public void add_ghost(int num_ghost) {
		this.ghosts.add(new Ghost(this, num_ghost));
	}
	
	//Vuelve a las condiciones iniciales del juego
	public void restart_level() {
		pacman.setLifes(pacman.getLifes()-1);
		pacman.setX(223);
		pacman.setY(433);
		pac_dots.set_dots();		
		for(int i=0; i<ghosts.size(); i++) {
			ghosts.get(i).restart_position();
		}
	}
	
	//Dibujar en pantalla el mensaje de derrota
	public void game_over() {
		JOptionPane.showMessageDialog(this, "Game Over", null, JOptionPane.YES_NO_OPTION);
		System.exit(0);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
