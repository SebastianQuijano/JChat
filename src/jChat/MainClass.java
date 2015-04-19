package jChat;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/******************************************************************************
 * @MainClass.java
 * 
 **/
public class MainClass {
	
	public static void main(String[] args) {JDialog.setDefaultLookAndFeelDecorated(true);
	final String SO = new String(System.getProperty("os.name"));
	if (SO.indexOf("Win") != -1) {
			try {
			      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			      
			    } catch (Exception e) {
			    	content.Util.showException("main", "Error",e.toString());
			    }
	} else{
		try {
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		      
		    } catch (Exception e) {
		    	content.Util.showException("main", "Error",e.toString());
		    }
	}
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				final GUIJChat GUI = new GUIJChat();
				GUI.init();
				
				GUI.addWindowListener(new WindowAdapter() {
					
					public void windowClosing(WindowEvent evento) {
						
						if (JOptionPane.showConfirmDialog(GUI,
								"Seguro que desea salir ?", "Confirmacion",
								JOptionPane.YES_NO_OPTION) != JOptionPane.OK_OPTION) {
							
							return; } else 
						   {
							GUI.dispose();
							
							System.exit(0);
						}
					}
				}
			);
		}
	}
);}
	/** --------@Fin Main--------------------------------- */
}
