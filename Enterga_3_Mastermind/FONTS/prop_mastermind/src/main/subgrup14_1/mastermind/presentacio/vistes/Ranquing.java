package main.subgrup14_1.mastermind.presentacio.vistes;

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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;
import main.subgrup14_1.mastermind.utils.InformacioUsuari;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class Ranquing extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructora de la vista Ranquing
	 */
	public Ranquing() {
		getContentPane().setBackground(new Color(219, 167, 119));
		setBackground(new Color(219, 167, 119));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-650/2, screenSize.height/2-300/2, 650, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		contentPanel.setBackground(new Color(219, 167, 119));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		contentPanel.add(verticalGlue_2);
		
		JLabel lblNewLabel = new JLabel("Ranquing de millors jugadors de Mastermind:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(lblNewLabel);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		contentPanel.add(verticalGlue_1);
		
		List<InformacioUsuari> userList = ControladorPresentacio.getInstance().llistaRanquingUsuaris();
		
		if(!userList.isEmpty()) {
			JPanel panellUsuaris = new JPanel();
			panellUsuaris.setLayout(new BoxLayout(panellUsuaris, BoxLayout.Y_AXIS));
			Component vs = Box.createVerticalStrut(5);
			panellUsuaris.add(vs);
			
		  for(Integer j = 0; j < userList.size(); j++) {
			  JPanel filaUsuari = new JPanel();
			  filaUsuari.setLayout(new BoxLayout(filaUsuari, BoxLayout.X_AXIS));
			  
			  Component hGlue = Box.createHorizontalGlue();
			  filaUsuari.add(hGlue);
			  
			  Integer aux = j+1;
			  JLabel posLabel = new JLabel("Top " + aux.toString());
			  filaUsuari.add(posLabel);
			  
			  Component hGlue0 = Box.createHorizontalGlue();
			  filaUsuari.add(hGlue0);
			  
			  JLabel userLabel = new JLabel(userList.get(j).getUsername());
			  filaUsuari.add(userLabel);
			  
			  Component hGlue1 = Box.createHorizontalGlue();
			  filaUsuari.add(hGlue1);
			
			  JLabel numP = new JLabel("Partides jugades: " + userList.get(j).getNumPartidesJugades().toString());
			  filaUsuari.add(numP);
			  
			  Component hGlue2 = Box.createHorizontalGlue();
			  filaUsuari.add(hGlue2);
			  
			  JLabel maxP = new JLabel("Max. Puntuacio: " + userList.get(j).getMaxPuntuacio().toString());
			  filaUsuari.add(maxP);
			  
			  Component hGlue3 = Box.createHorizontalGlue();
			  filaUsuari.add(hGlue3);
			  
			  filaUsuari.setBackground(new Color(227, 186, 149));
			  panellUsuaris.add(filaUsuari);
			
			  Component verticalStrut = Box.createVerticalStrut(5);
			  panellUsuaris.add(verticalStrut);
		  };
		  panellUsuaris.setBackground(new Color(227, 186, 149));
		  JScrollPane scrollBar=new JScrollPane(panellUsuaris,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		  contentPanel.add(scrollBar);
		};
		
		Component verticalGlue = Box.createVerticalGlue();
		contentPanel.add(verticalGlue);
		
		JButton backButton = new JButton("Enrere");
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setBackground(new Color(227, 186, 149));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPanel.add(backButton);
	}
}
