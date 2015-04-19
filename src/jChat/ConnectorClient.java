package jChat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import content.Commands;
import content.ThreadWriterPrivateMessage;

/******************************************************************************
 * @ConnectorClient.java 
 * 
 * Clase encargada de iniciar el conector socket
 **/
public class ConnectorClient {

	private int     puerto    = 0;
	
	private String  servidor  = null;
	
	Socket          connector = null;
	
	GUIJChat         client    = null;
	
	private String username   = null;
	
	private String _IP   = null;
	private String _SO   = null;
	@SuppressWarnings("unused")
	private String _NAME = null;
	
	/**
	 * Constructor.
	 * 
	 * @param Interfaz
	 * @param Direccion del Servidor
	 * @param puerto
	 * */
	public ConnectorClient(
			GUIJChat c, 
			String s, 
			int p) {

		this.client = c;
		this.servidor = s;
		this.puerto = p;
		initConnector();
	}
	
	public final void initConnector() {
		
		try {
	    	//Obtener el nick del usuario
			this.username = JOptionPane.showInputDialog(client,
					"\n Cual es tu Nick: ? ",
					"Ingresa tu Nick", JOptionPane.INFORMATION_MESSAGE);
			username.trim();
			
			//Inicia el conector
			connector = new Socket(servidor, puerto);

			//Inicia el hilo de lectura
			new ThreadMainReader(connector, client);
			//Envia el nick del usuario
			client.sendMessage(this, "Conectado!");
			
			//Habilitar botones he items necesarios
			client.setEnableItemListPrivate();
			client.setEnableItemDisconnect();
			client.showStatus("| Conectado al Servidor ");
			client.setEnableButtonSendText();
			info();
	
		} catch (UnknownHostException e) {
			System.err.println(e);
			System.exit(0);
		} catch (IOException e) {
			content.Util.showException("??????", "Error al conectar al servidor","");
		} catch (Exception e) {
			content.Util.showException(this.getClass().getName(), "Error",e.toString());
			System.exit(0);
		}
	}
	
	public void info(){
		this._SO =System.getProperty("os.name");
        try {
			InetAddress address = InetAddress.getByName("localhost");
		this._IP = address.getHostAddress();
		this._NAME = username;
		client.sendMessage(this, content.Commands._COMMAND_WRITERINFO+"*?"+_IP+"*?"+_SO);
		
        } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	//@Metodo de envio de mensaje privado
	public void sendPrivateMenssage(String username, String to,
		String menssage, String font, int style, int color) {
		
		client.sendMessage(this, Commands._COMMAND_PRIVATE);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ThreadWriterPrivateMessage(
				connector,
				username, 
				to,
				menssage,
				font, 
				style,
				color);
	   }
}
