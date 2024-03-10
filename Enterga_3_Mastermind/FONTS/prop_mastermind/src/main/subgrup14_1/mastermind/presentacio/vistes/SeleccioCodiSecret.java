package main.subgrup14_1.mastermind.presentacio.vistes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;
import main.subgrup14_1.mastermind.utils.BotoCircular;
import main.subgrup14_1.mastermind.utils.PartidaEnJoc;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class SeleccioCodiSecret extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JButton submitButton;
	
	/**
	 * Constructora de la vista SeleccioCodiSecret
	 */
	public SeleccioCodiSecret() {
		setTitle("Escollir combinaci\u00F3 secreta");
		getContentPane().setBackground(new Color(219, 167, 119));
		PartidaEnJoc partida = ControladorPresentacio.getInstance().infoPartidaEnJoc();
		Dificultat diff = partida.getDificultat();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-350/2, screenSize.height/2-150/2, 350, 150);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(219, 167, 119));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.desktop);
		contentPanel.add(panel);
		
		JPanel panell_seleccio = new JPanel();
		panell_seleccio.setBackground(SystemColor.desktop);
		contentPanel.add(panell_seleccio);
		panell_seleccio.setLayout(new BoxLayout(panell_seleccio, BoxLayout.X_AXIS));
		
		Component horizontalGlue_5 = Box.createHorizontalGlue();
		panell_seleccio.add(horizontalGlue_5);
		
		BotoCircular col1 = new BotoCircular(" ", true, diff, null);
		col1.setAlignmentX(0.5f);
		col1.setBackground(Color.BLACK);
		panell_seleccio.add(col1);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panell_seleccio.add(horizontalGlue_1);
		
		BotoCircular col2 = new BotoCircular(" ", true, diff, null);
		col2.setAlignmentX(0.5f);
		col2.setBackground(Color.BLACK);
		panell_seleccio.add(col2);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panell_seleccio.add(horizontalGlue_2);
		
		BotoCircular col3 = new BotoCircular(" ", true, diff, null);
		col3.setAlignmentX(0.5f);
		col3.setBackground(Color.BLACK);
		panell_seleccio.add(col3);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		panell_seleccio.add(horizontalGlue_4);
		
		BotoCircular col4 = new BotoCircular(" ", true, diff, null);
		col4.setAlignmentX(0.5f);
		col4.setBackground(Color.BLACK);
		panell_seleccio.add(col4);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		panell_seleccio.add(horizontalGlue_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.desktop);
		contentPanel.add(panel_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		contentPanel.add(verticalStrut);
		
		JPanel panell_bSubmit = new JPanel();
		panell_bSubmit.setBorder(null);
		panell_bSubmit.setBackground(new Color(219, 167, 119));
		contentPanel.add(panell_bSubmit);
		panell_bSubmit.setLayout(new BoxLayout(panell_bSubmit, BoxLayout.X_AXIS));
		
		submitButton = new JButton("Escollir combinacio");
		submitButton.setAlignmentX(0.5f);
		submitButton.setBackground(new Color(219, 167, 119));
		
		submitButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	List<Integer> codi = new ArrayList<Integer>();
		    	codi.add(col1.getColor());
		        codi.add(col2.getColor());
		        codi.add(col3.getColor());
		        codi.add(col4.getColor());
		        if (ControladorPresentacio.getInstance().iniciarCodi(codi) != null) {
		        	dispose();
		        	ControladorPresentacio.getInstance().showJoc(false);
		        };
		    }
		});
		
		JButton backButton = new JButton("Enrere");
		backButton.setBackground(new Color(219, 167, 119));
		backButton.setAlignmentX(0.5f);
		backButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		    	ControladorPresentacio.getInstance().abandonarPartida();
	        	ControladorPresentacio.getInstance().botoCrearPartida();
		    }
		});
		panell_bSubmit.add(backButton);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		panell_bSubmit.add(horizontalStrut);
		panell_bSubmit.add(submitButton);
	}
}
