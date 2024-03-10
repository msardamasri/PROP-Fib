package main.subgrup14_1.mastermind.presentacio.vistes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class IniciarSessioContrasenya extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;

	/**
	 * Constructora de la vista IniciarSessioContrasenya
	 * @param username Nom d'usuari
	 * @param uid Identificador de l'usuari
	 */
	public IniciarSessioContrasenya(String username, String uid) {
		setTitle("Inici de sessio");
		getContentPane().setBackground(new Color(219, 167, 119));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-300/2, screenSize.height/2-200/2, 300, 200);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(219, 167, 119));
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(7, 0, 0, 0));
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		panel_1.add(verticalGlue_2);
		
		JLabel lblEstasAPunt = new JLabel("Estas a punt d'iniciar sessio");
		lblEstasAPunt.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstasAPunt.setAlignmentX(0.5f);
		panel_1.add(lblEstasAPunt);
		
		JLabel lblPasswordFor = new JLabel("Escriu la contrasenya de l'usuari " + username);
		lblPasswordFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordFor.setAlignmentX(0.5f);
		panel_1.add(lblPasswordFor);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(219, 167, 119));
		panel_1.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		panel.add(horizontalStrut_1);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBackground(new Color(227, 186, 149));
		panel.add(passwordField);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		panel.add(horizontalStrut_2);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		panel_1.add(verticalGlue_1);
		
		JPanel buttonPane = new JPanel();
		panel_1.add(buttonPane);
		buttonPane.setBackground(new Color(219, 167, 119));
			
		JButton okButton = new JButton("Acceptar");
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		okButton.setBackground(new Color(227, 186, 149));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				String pwd = passwordField.getText();
				if (ControladorPresentacio.getInstance().carregarUsuari(uid, pwd)) {
					dispose();
					ControladorPresentacio.getInstance().showMenuPartida();
				}
			}
		});
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		
		Component horizontalGlue = Box.createHorizontalGlue();
		buttonPane.add(horizontalGlue);
		buttonPane.add(okButton);
	
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.setBackground(new Color(227, 186, 149));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ControladorPresentacio.getInstance().showMenuSeleccioUsuari();
			}
		});
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		buttonPane.add(horizontalStrut);
		
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		buttonPane.add(horizontalGlue_1);
	}
}
