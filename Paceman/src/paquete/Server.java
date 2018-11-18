package paquete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import org.json.JSONObject;
import org.json.JSONException;

public class Server extends Thread{

    private	ServerSocket servidor;
    private Game game;
    
    public Server(Game game){    	
    	this.game=game;
    }
    
	public void run(){
        try {
            servidor = new ServerSocket(4000);

            while (true){
                try {
                    new ManejadorMensajes(servidor.accept(), game).start();
                }catch (SocketException s){
                    //
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    public void close_socket() throws IOException{
        servidor.close();
    }
    
    public static class ManejadorMensajes extends Thread{

        public static Socket jugador;
        private Game game;
        
        private BufferedReader in;
        private static PrintWriter out;
        public static boolean hay_conexion = false;

        public ManejadorMensajes(Socket jugador, Game game){
        	this.game=game;
            this.jugador = jugador;
            hay_conexion = true;
            new Thread(this).start();
        }

        public void run(){
            try{
                in = new BufferedReader(new InputStreamReader(jugador.getInputStream()));
                out = new PrintWriter(jugador.getOutputStream(), true);
            }catch (IOException s){
                //
            }

            while (true){
                try {
                    String posiciones_jugador = leer();
                    JSONObject identifier = new JSONObject(posiciones_jugador);
                    
                    set_move(identifier.getDouble("x"), identifier.getDouble("y"));
                  //  System.out.println(identifier.getDouble("x"));
                    //System.out.println(identifier.getDouble("y"));
                }catch (JSONException e) {
                	e.printStackTrace();
                }
            }
        }
        
        private void set_move(double x, double y) {
        	if(Math.abs(x)>Math.abs(y)+1) {
                if(x>0) {
                	game.pacman.setXa(-1);
                }else {
                	game.pacman.setXa(1);
                }
        	}else {
                if(y>0) {
                	game.pacman.setYa(1);
                }else {
                	game.pacman.setYa(-1);
                }
        	}
        }
        
        public String  leer(){
            String mensaje = "";
            try {
                mensaje = in.readLine();
            }catch (IOException e){
                //
            }
            return mensaje;
        }

        public static void enviar(String mensaje){
                out.println(mensaje);
                out.flush();
        }
    }
}