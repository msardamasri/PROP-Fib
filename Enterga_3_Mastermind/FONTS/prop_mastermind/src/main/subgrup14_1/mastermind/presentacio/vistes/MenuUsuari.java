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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class MenuUsuari extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructora de la vista MenuUsuari
	 */
	public MenuUsuari() {
		setTitle("MASTERMIND");
		getContentPane().setBackground(new Color(219, 167, 119));
		setBackground(new Color(219, 167, 119));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-450/2, screenSize.height/2-300/2, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(219, 167, 119));
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel lblNewLabel = new JLabel("Benvingut a");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("MASTERMIND");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_1.setAlignmentX(0.5f);
		panel.add(lblNewLabel_1);
		
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		panel.add(verticalStrut_2);
		
		JPanel panell_carregar_perfil = new JPanel();
		panell_carregar_perfil.setBackground(new Color(219, 167, 119));
		panel.add(panell_carregar_perfil);
		panell_carregar_perfil.setLayout(new BorderLayout(0, 0));
		
		JButton loginButton = new JButton("Iniciar sessio");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		loginButton.setEnabled(true);
		loginButton.setBackground(new Color(227, 186, 149));
		loginButton.setAlignmentX(0.5f);
		loginButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		    	ControladorPresentacio.getInstance().botoIniciarSessio();
		    }
		});
		panell_carregar_perfil.add(loginButton, BorderLayout.CENTER);
		
		Component horizontalStrut = Box.createHorizontalStrut(5);
		panell_carregar_perfil.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(5);
		panell_carregar_perfil.add(horizontalStrut_1, BorderLayout.EAST);
		
		if (ControladorPresentacio.getInstance().llistaUsuaris().isEmpty()) {
			loginButton.setEnabled(false);
		}
		
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		panel.add(verticalStrut_1);
		
		JPanel panell_crearPerfil = new JPanel();
		panell_crearPerfil.setBackground(new Color(219, 167, 119));
		panel.add(panell_crearPerfil);
		panell_crearPerfil.setLayout(new BorderLayout(0, 0));
		
		JButton signupButton = new JButton("Registrar-se");
		signupButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		signupButton.setBackground(new Color(227, 186, 149));
		signupButton.setAlignmentX(0.5f);
		signupButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		    	MenuRegistrarUsuari dialog = new MenuRegistrarUsuari();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModal(true);
				dialog.setVisible(true);
		    }
		});
		panell_crearPerfil.add(signupButton);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(5);
		panell_crearPerfil.add(horizontalStrut_2, BorderLayout.WEST);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(5);
		panell_crearPerfil.add(horizontalStrut_3, BorderLayout.EAST);
		
		Component verticalStrut_1_1 = Box.createVerticalStrut(5);
		panel.add(verticalStrut_1_1);
		
		JPanel panel_normes = new JPanel();
		panel_normes.setBackground(new Color(219, 167, 119));
		panel.add(panel_normes);
		panel_normes.setLayout(new BorderLayout(0, 0));
		
		JButton rulesButton = new JButton("Normes del Mastermind");
		rulesButton.setBackground(new Color(227, 186, 149));
		panel_normes.add(rulesButton, BorderLayout.CENTER);
		rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rulesButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		rulesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NormativaJoc dialog = new NormativaJoc();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				dialog.setModal(true);
			}
		});
		
		Component horizontalStrut_3_1 = Box.createHorizontalStrut(5);
		panel_normes.add(horizontalStrut_3_1, BorderLayout.WEST);
		
		Component horizontalStrut_3_2 = Box.createHorizontalStrut(5);
		panel_normes.add(horizontalStrut_3_2, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(5);
		panel.add(verticalStrut);
		
		JPanel panell_ranquing = new JPanel();
		panell_ranquing.setBackground(new Color(219, 167, 119));
		panel.add(panell_ranquing);
		panell_ranquing.setLayout(new BorderLayout(0, 0));
		
		JButton rankButton = new JButton("Ranquing");
		rankButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rankButton.setBackground(new Color(227, 186, 149));
		rankButton.setAlignmentX(0.5f);
		panell_ranquing.add(rankButton, BorderLayout.CENTER);
		
		rankButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Ranquing dialog = new Ranquing();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				dialog.setModal(true);
		    }
		});
		
		Component horizontalStrut_4_1 = Box.createHorizontalStrut(5);
		panell_ranquing.add(horizontalStrut_4_1, BorderLayout.WEST);
		
		Component horizontalStrut_4_2 = Box.createHorizontalStrut(5);
		panell_ranquing.add(horizontalStrut_4_2, BorderLayout.EAST);
		
		Component verticalStrut_3 = Box.createVerticalStrut(5);
		panel.add(verticalStrut_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(219, 167, 119));
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JButton exitButton = new JButton("Tancar aplicacio");
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		exitButton.setBackground(new Color(227, 186, 149));
		exitButton.setAlignmentX(0.5f);
		exitButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				dispose();
		    }
		});
		panel_1.add(exitButton);
		
		JLabel lblProjecteDeProgramacio = new JLabel("Projecte de Programacio (PROP) grup 14.1 curs 2022/23 QP");
		lblProjecteDeProgramacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjecteDeProgramacio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProjecteDeProgramacio.setAlignmentX(0.5f);
		panel.add(lblProjecteDeProgramacio);
		
		JLabel lblNoms = new JLabel("Marc Sarda Masriera, Aglaya Khalipskaya, Pol Farre Burgos i Pol Kallai Raventos");
		lblNoms.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoms.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNoms.setAlignmentX(0.5f);
		panel.add(lblNoms);


	}

}
