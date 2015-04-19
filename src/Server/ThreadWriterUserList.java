package Server;
/**********************************************
 * @Hilo SendUserList.java : 
 * 
 * Hilo encargado de enviar la lista de usuarios
 * cada cierto tiempo
 **/
public class ThreadWriterUserList
extends Thread{
	private Server server        = null;

    //Tiempo de retraso
	public static final int DELAY = 10000;
	
	/***
	 * Constructor
	 * 
	 * @param Servidor
	 * 
	 **/
	public ThreadWriterUserList(Server s){
		
	this.server = s; 
	
	setPriority(Thread.MAX_PRIORITY);
	}
	private boolean active = true;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void run() {
		
		while(active){
            //Enviamos la lista de usuarios
			server.sendList();	
			
			try {
				Thread.sleep(DELAY);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}