package main.subgrup14_1.mastermind.presentacio.vistes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class MenuRegistrarUsuari extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField userField;
	private JPasswordField pwdField;
	private JPasswordField repPwdField;
	
	/**
	 * Constructora de la vista MenuRegistrarUsuari
	 */
	public MenuRegistrarUsuari() {
		getContentPane().setBackground(new Color(219, 167, 119));
		setModal(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-450/2, screenSize.height/2-300/2, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBackground(new Color(219, 167, 119));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		contentPanel.add(verticalGlue_2);
		
		JLabel titol = new JLabel("Registrar un nou usuari");
		titol.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPanel.add(titol);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		contentPanel.add(verticalGlue_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(219, 167, 119));
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(3, 2, 10, 5));
		
		JLabel lblNomDusuari = new JLabel("Nom d'usuari:");
		lblNomDusuari.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNomDusuari);
		
		userField = new JTextField();
		userField.setBackground(new Color(227, 186, 149));
		userField.setColumns(10);
		panel.add(userField);
		{
			JLabel lblNewLabel_1 = new JLabel("Contrasenya:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lblNewLabel_1);
		}
		
		pwdField = new JPasswordField();
		pwdField.setBackground(new Color(227, 186, 149));
		panel.add(pwdField);
		
		JLabel lblNewLabel_2 = new JLabel("Repeteix la contrasenya:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_2);
		
		repPwdField = new JPasswordField();
		repPwdField.setBackground(new Color(227, 186, 149));
		panel.add(repPwdField);
		
		Component verticalGlue = Box.createVerticalGlue();
		getContentPane().add(verticalGlue);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(219, 167, 119));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Registrar usuari");
				okButton.setBackground(new Color(227, 186, 149));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						@SuppressWarnings("deprecation")
						String pwd = pwdField.getText();
						@SuppressWarnings("deprecation")
						String pwd2 = repPwdField.getText();
						if (ControladorPresentacio.getInstance().crearUsuari(userField.getText(), pwd, pwd2)) {
							dispose();
							ControladorPresentacio.getInstance().showMenuUsuari();
						};
					}
					
				});
				buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBackground(new Color(227, 186, 149));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						ControladorPresentacio.getInstance().showMenuUsuari();
					}
				});
				
				Component horizontalStrut = Box.createHorizontalStrut(20);
				buttonPane.add(horizontalStrut);
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}
}
