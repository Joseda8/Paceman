package paquete;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Vector;

public class PacDots {
	
	private ArrayList<PacDot> pac_dots = new ArrayList<PacDot>();
	private Vector<Rectangle> bounds;
	private int num_dots;
	
	public void paint(Graphics2D g){
		for(int i=0; i<pac_dots.size(); i++) {
			if(!pac_dots.get(i).isEated()) {
				if(pac_dots.get(i).is_power()) {
					g.setColor(new Color(255, 50, 0));
					g.fillOval(pac_dots.get(i).getPos_x(), pac_dots.get(i).getPos_y(), 20, 20);
				}else {
					g.setColor(new Color(255, 255, 50));
					g.fillOval(pac_dots.get(i).getPos_x(), pac_dots.get(i).getPos_y(), 20, 20);
				}
			}
		}
	}

	public void set_dots() {
		for(int i=0; i<13; i++) {
			for(int j=0; j<17; j++) {
				if(!collision(i*35+10, j*35+10) && !(i*35+10 == 220 && (j*35+10 == 430 || j*35+10 == 290))) {
					pac_dots.add(new PacDot(i*35+10, j*35+10));
				}
			}
		}
		setNum_dots(pac_dots.size());
	}
	
	private boolean collision(int x, int y) {
		for(int i=0; i<bounds.size(); i++) {
			if(bounds.get(i).intersects(new Rectangle(x, y, 20, 20))) {
				return true;
			}
		}
		return false;
	}

	public Vector<Rectangle> getBounds() {
		return bounds;
	}

	public void setBounds(Vector<Rectangle> bounds) {
		this.bounds = bounds;
	}

	public ArrayList<PacDot> getPac_dots() {
		return pac_dots;
	}

	public int getNum_dots() {
		return num_dots;
	}

	public void setNum_dots(int num_dots) {
		this.num_dots = num_dots;
	}
	
	
	
}