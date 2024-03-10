package main.subgrup14_1.mastermind.presentacio.vistes;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.domini.enums.Rol;
import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class MenuConfigPartida extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructora de la vista MenuConfigPartida
	 */
	public MenuConfigPartida() {
		getContentPane().setBackground(new Color(219, 167, 119));
		setBackground(new Color(219, 167, 119));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-450/2, screenSize.height/2-300/2, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBackground(new Color(219, 167, 119));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JLabel lblNovaPartida = new JLabel("Nova partida");
		lblNovaPartida.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNovaPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(lblNovaPartida);
		
		Component verticalGlue = Box.createVerticalGlue();
		contentPanel.add(verticalGlue);
		
		JLabel lblNewLabel_1 = new JLabel("Escollir dificultat");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(lblNewLabel_1);
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(219, 167, 119));
		contentPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_3);
		
		JComboBox<Object> diffComboBox = new JComboBox<Object>(Dificultat.values());
		diffComboBox.setBackground(new Color(227, 186, 149));
		panel.add(diffComboBox);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		contentPanel.add(verticalStrut_1);
		
		JLabel lblNewLabel_2 = new JLabel("Escollir rol");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(lblNewLabel_2);
		lblNewLabel_2.setAlignmentX(0.5f);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(219, 167, 119));
		contentPanel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_2);
		
		JComboBox<Object> roleComboBox = new JComboBox<Object>(Rol.values());
		roleComboBox.setBackground(new Color(227, 186, 149));
		panel_1.add(roleComboBox);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_1);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		contentPanel.add(verticalGlue_2);
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(219, 167, 119));
		getContentPane().add(buttonPane);
		
		JButton okButton = new JButton("Iniciar partida");
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		okButton.setBackground(new Color(227, 186, 149));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorPresentacio.getInstance().crearPartida((Dificultat)diffComboBox.getSelectedItem(), (Rol)roleComboBox.getSelectedItem());
				if((Rol)roleComboBox.getSelectedItem() == Rol.CODEBREAKER) {
					dispose();
					ControladorPresentacio.getInstance().showJoc(true);
				}
				else {
					dispose();
					ControladorPresentacio.getInstance().showSeleccioCodiSecret();
				};
			}
		});
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.setBackground(new Color(227, 186, 149));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MenuPartida dialog = new MenuPartida();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		buttonPane.add(horizontalStrut);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
	}
}
