package paquete;

public class PacDot {
	private int pos_x;
	private int pos_y;
	private boolean eated;
	private boolean is_power;
	
	public PacDot(int x, int y){
		this.pos_x=x;
		this.pos_y=y;
		this.eated=false;
	}
	
	public int getPos_x() {
		return pos_x;
	}
	
	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}
	
	public int getPos_y() {
		return pos_y;
	}
	
	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}
	
	public boolean isEated() {
		return eated;
	}
	
	public void setEated(boolean eated) {
		this.eated = eated;
	}

	
	public boolean is_power() {
		return is_power;
	}

	
	public void set_is_power(boolean is_power) {
		this.is_power = is_power;
	}	
}