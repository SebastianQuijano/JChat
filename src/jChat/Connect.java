package jChat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/******************************************************************************
 * @Connect.java 
 * 
 * Dialogo que optiene los datos para conectar
 **/
public class Connect extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel     panel,       panel2;
	private JTextField textFieldIp, textFieldPort;
	private JLabel     labelIp,     labelPort;
	private JButton    buttonOk;
	private JButton    buttonCancel;
	private String     servidor = null;
	private int        puerto = 0;
	@SuppressWarnings("unused")
	private GUIJChat    client;

	public Connect(GUIJChat parent, boolean modal) {

		super(parent, modal);
		this.client = parent;
		
		this.setLayout(new FlowLayout());
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		textFieldIp = new JTextField(15);
		textFieldPort = new JTextField(5);
		textFieldPort.setText("8081");
		textFieldPort.setEditable(false);
		labelIp = new JLabel(" IP Servidor:");
		labelPort = new JLabel(" Puerto:");
		buttonOk = new JButton("Conectar");
		buttonCancel = new JButton("Cancelar");
		textFieldIp.setText("localhost");
		panel.setBorder(BorderFactory.createEtchedBorder());

		panel.add(labelIp);
		panel.add(textFieldIp);
		panel.add(labelPort);

		panel.add(textFieldPort);
		panel2.add(buttonOk);
		panel2.add(buttonCancel);

		getContentPane().add(panel, BorderLayout.NORTH);
		getContentPane().add(panel2);

		final ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonOk) {
					try {
						//Setear los valores
						servidor = textFieldIp.getText();
						puerto = Integer.parseInt(textFieldPort.getText());
						exit();
					} catch (Exception exception) {
						content.Util.showException("????", "Error ",exception.toString());
					}
				}
				if (e.getActionCommand().equalsIgnoreCase("Cancelar")) {
					System.out.println("exit");
					exit();
					System.exit(0);
				}
			}
		};

		buttonOk.addActionListener(actionListener);
		
		buttonCancel.addActionListener(actionListener);

		this.setTitle("Conectar al Servidor");
		this.setSize(450, 130);
		this.setLocationRelativeTo(parent);
		
		
		this.setVisible(true);
	}

	public boolean init() {
		boolean run = false;
		if (servidor == null || puerto == 0) {
			run = false;
		}
		if (servidor != null || puerto != 0) {
			run = true;
		}
		return run;
	}

	public void exit() {
		this.dispose();
	}

	public String getServer() {
		return servidor;
	}

	public void setServer(String server) {
		this.servidor = server;
	}

	public int getPuert() {
		return puerto;
	}

	public void setPuert(int puert) {
		this.puerto = puert;
	}
}
