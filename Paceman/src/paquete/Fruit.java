package paquete;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fruit {
	private boolean is_on=false;
	BufferedImage fruit_image;
	
	public Fruit(){
		try {
			load_image();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load_image() throws IOException {
		fruit_image = ImageIO.read(new File("C:/Users/jdmon/OneDrive/Escritorio/Paceman File/Paceman/src/paquete/banana.png"));
	}
	
	public void paint(Graphics2D g){
		if(is_on){
			g.drawImage(fruit_image, 215, 285, null);
		}
	}

	public boolean get_is_on() {
		return is_on;
	}

	public void setIs_on(boolean is_on) {
		this.is_on = is_on;
	}
}
