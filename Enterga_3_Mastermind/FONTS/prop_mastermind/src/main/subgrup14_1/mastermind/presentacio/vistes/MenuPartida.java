package main.subgrup14_1.mastermind.presentacio.vistes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class MenuPartida extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructora de la vista MenuPartida
	 */
	public MenuPartida() {
		setForeground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-450/2, screenSize.height/2-300/2, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(227, 186, 149));
		contentPanel.setForeground(new Color(0, 0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		String username = ControladorPresentacio.getInstance().informacioUsuariCarregat().getUsername();
		
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Menu principal");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPanel.add(lblNewLabel);
		
		JLabel ranquing = new JLabel("Benvingut, usuari. Estas en la posicio 0 del ranquing!");
		ranquing.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ranquing.setAlignmentX(0.5f);
		contentPanel.add(ranquing);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		contentPanel.add(verticalStrut_2);
		
		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("Nova partida");
		panel_1.add(btnNewButton_1, BorderLayout.CENTER);
		btnNewButton_1.setBackground(new Color(227, 186, 149));
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ControladorPresentacio.getInstance().botoCrearPartida();
			}
		});
		
		Component verticalStrut = Box.createVerticalStrut(5);
		contentPanel.add(verticalStrut);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(219, 167, 119));
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Continuar partida");
		btnNewButton.setBackground(new Color(227, 186, 149));
		panel.add(btnNewButton, BorderLayout.CENTER);
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setEnabled(true);
		
		String uid = ControladorPresentacio.getInstance().informacioUsuariCarregat().getUid();
		if (ControladorPresentacio.getInstance().llistaPartidesUsuari(uid).isEmpty()) {
			btnNewButton.setEnabled(false);
		}
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ControladorPresentacio.getInstance().showSeleccioPartidaGuardada();
			}
		});
		
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		contentPanel.add(verticalStrut_1);
		
		JPanel panel_2 = new JPanel();
		contentPanel.add(panel_2);
		contentPanel.setBackground(new Color(219, 167, 119));
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2 = new JButton("Tancar sessio");
		btnNewButton_2.setBackground(new Color(227, 186, 149));
		panel_2.add(btnNewButton_2, BorderLayout.CENTER);
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorPresentacio.getInstance().logout();
				dispose();
				ControladorPresentacio.getInstance().showMenuUsuari();
			}
		});
		
		Component verticalStrut_3 = Box.createVerticalStrut(5);
		contentPanel.add(verticalStrut_3);
		
		JPanel panel_3 = new JPanel();
		contentPanel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JButton btnNewButton_3 = new JButton("Tancar aplicacio");
		btnNewButton_3.setBackground(new Color(227, 186, 149));
		panel_3.add(btnNewButton_3);
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		Component verticalGlue = Box.createVerticalGlue();
		contentPanel.add(verticalGlue);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		List<InformacioUsuari> rankingList = ControladorPresentacio.getInstance().llistaRanquingUsuaris();
		for(Integer i = 0; i<rankingList.size(); i++) {
			if(rankingList.get(i).getUid() == uid) {
				++i;
				ranquing.setText("Benvingut, " + username + ". Estas en la posicio " + i.toString() + " del ranquing!");
				break;
			}
		};
	}
}
