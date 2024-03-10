package main.subgrup14_1.mastermind.presentacio.vistes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class MenuPausa extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructora de la vista MenuPausa
	 * @param acabada Boolea que indica si la partida esta o no acabada
	 */
	public MenuPausa(Boolean acabada) {
		setTitle("Menu de pausa");
		getContentPane().setBackground(new Color(219, 167, 119));
		setBackground(new Color(219, 167, 119));
		setModal(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-400/2, screenSize.height/2-225/2, 400, 225);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		contentPanel.setBackground(new Color(219, 167, 119));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		Component verticalGlue = Box.createVerticalGlue();
		contentPanel.add(verticalGlue);
		{
			String titol = "Joc pausat";
			if (acabada) titol = "Joc finalitzat";
			JLabel lblNewLabel = new JLabel(titol);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel);
		}
		
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		contentPanel.add(verticalStrut_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(219, 167, 119));
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		if (!acabada) {
			JButton btnNewButton= new JButton("Continuar la partida");
			btnNewButton.setBackground(new Color(227, 186, 149));
			panel.add(btnNewButton);
			btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					ControladorPresentacio.getInstance().showJoc(true);
				}
			});
			Component verticalStrut_1 = Box.createVerticalStrut(5);
			contentPanel.add(verticalStrut_1);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(227, 186, 149));
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = null;
		if (!acabada) btnNewButton_1 = new JButton("Desar i sortir de la partida");
		else btnNewButton_1 = new JButton("Tornar al menu");
		btnNewButton_1.setBackground(new Color(227, 186, 149));
		panel_1.add(btnNewButton_1);
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorPresentacio.getInstance().guardarPartida();
				dispose();
				ControladorPresentacio.getInstance().showMenuPartida();		
			}
		});
		
		if (!acabada) {
			Component verticalStrut = Box.createVerticalStrut(5);
			contentPanel.add(verticalStrut);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(227, 186, 149));
			contentPanel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JButton btnNewButton_2 = new JButton("Abandonar partida");
			btnNewButton_2.setBackground(new Color(227, 186, 149));
			panel_2.add(btnNewButton_2);
			btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ControladorPresentacio.getInstance().abandonarPartida();
					dispose();
					ControladorPresentacio.getInstance().showMenuPartida();
				}
			});
		}
		else {
			Component verticalGlue2 = Box.createVerticalGlue();
			contentPanel.add(verticalGlue2);
		}
	}
}
