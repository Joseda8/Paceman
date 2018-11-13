package paquete;

import java.io.IOException;

public class pacman_power extends Thread {
	Game game;
	Pacman pacman;
	
	public pacman_power(Game game){
		this.game=game;
		this.pacman=game.pacman;
	}
	
	private void scare_ghost(boolean scare_ghost) throws IOException {
		if(scare_ghost) {
			for(int i=0; i<game.ghosts.size(); i++) {
				game.ghosts.get(i).load_image(4);
			}
		}else {
			for(int i=0; i<game.ghosts.size(); i++) {
				game.ghosts.get(i).load_image(i);
			}
		}
	}
	
	private void change_ghost_image(boolean scare_ghost) {
		try {
			scare_ghost(scare_ghost);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		Cronometer timer = new Cronometer();
		change_ghost_image(true);
		timer.Contar();
		while(timer.getSegundos()<6) {
			System.out.println("");
		}
		pacman.setPower_on(false);
		change_ghost_image(false);
		timer.Detener();
	}
}