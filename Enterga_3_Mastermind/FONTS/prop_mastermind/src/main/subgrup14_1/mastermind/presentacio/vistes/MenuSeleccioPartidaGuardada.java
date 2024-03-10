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
import main.subgrup14_1.mastermind.utils.InformacioPartida;
import main.subgrup14_1.mastermind.utils.Utils;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class MenuSeleccioPartidaGuardada extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private List<InformacioPartida> partList;
	private Component guiList;

	/**
	 * Actualitza i retorna la llista de partides
	 * @param uidUser Identificador de l'usuari
	 * @return Component Llista de partides
	 */
	public Component actualitzaPartList(String uidUser) {
		this.partList = ControladorPresentacio.getInstance().llistaPartidesUsuari(uidUser);
		return panellPartides();
	}
	
	/**
	 * Construeix la llista de partides disponibles
	 * @return Component amb les partides disponibles
	 */
	public Component panellPartides() {
		JPanel panellP = new JPanel();
		panellP.setLayout(new BoxLayout(panellP, BoxLayout.Y_AXIS));
		Component vs = Box.createVerticalStrut(5);
		panellP.add(vs);
		
		String uidUser = ControladorPresentacio.getInstance().informacioUsuariCarregat().getUid();
		this.partList = ControladorPresentacio.getInstance().llistaPartidesUsuari(uidUser);
		
		for(int j = 0; j < this.partList.size(); j++) {
			JPanel filaUsuari = new JPanel();
			filaUsuari.setLayout(new BoxLayout(filaUsuari, BoxLayout.X_AXIS));
			  
			Component hGlue = Box.createHorizontalGlue();
			filaUsuari.add(hGlue);
			  
			JLabel userLabel = new JLabel(Utils.difToString(this.partList.get(j).getDificultat()));
			filaUsuari.add(userLabel);
			  
			Component hGlue1 = Box.createHorizontalGlue();
			filaUsuari.add(hGlue1);
			
			JLabel numP = new JLabel("Puntuacio: " + this.partList.get(j).getPuntuacio().toString());
			filaUsuari.add(numP);
			  
			Component hGlue2 = Box.createHorizontalGlue();
			filaUsuari.add(hGlue2);
			  
			JLabel maxP = new JLabel("Creada el: " + Utils.formatData(this.partList.get(j).getData()));
			filaUsuari.add(maxP);
			  
			Component hGlue3 = Box.createHorizontalGlue();
			filaUsuari.add(hGlue3);
			  
			final String uidPart = this.partList.get(j).getUidPartida();
		
			JButton btnNewButton = new JButton("Carregar");
			btnNewButton.setBackground(new Color(227, 186, 149));
			btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNewButton.setEnabled(true);
			btnNewButton.addActionListener(new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent e) {
					dispose();
					ControladorPresentacio.getInstance().carregarPartida(uidPart);	
				}
			});
			filaUsuari.add(btnNewButton);
			Component hGlue4 = Box.createHorizontalGlue();
			filaUsuari.add(hGlue4);
				
			JButton btnD = new JButton("Esborrar");
			btnD.setBackground(new Color(227, 186, 149));
			btnD.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnD.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnD.setEnabled(true);
			btnD.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					ConfirmacioEsborrarPartida dialog = new ConfirmacioEsborrarPartida(uidPart, partList.size());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			});
			filaUsuari.add(btnD);
			Component hGlue5 = Box.createHorizontalGlue();
			filaUsuari.add(hGlue5);
			
			filaUsuari.setBackground(new Color(227, 186, 149));
			panellP.add(filaUsuari);
			
			Component verticalStrut = Box.createVerticalStrut(5);
			panellP.add(verticalStrut);
	  };
	  panellP.setBackground(new Color(227, 186, 149));
	  JScrollPane scrollBar = new JScrollPane(panellP,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	  contentPanel.add(scrollBar);
	  return scrollBar;
	}
	
	/**
	 * Constructora de la vista MenuSeleccioPartidaGuardada
	 */
	public MenuSeleccioPartidaGuardada() {
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
		
		JLabel lblNewLabel = new JLabel("Selecciona la partida per a carregar:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(lblNewLabel);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		contentPanel.add(verticalGlue_1);
		
		this.guiList = panellPartides();
		contentPanel.add(this.guiList);
		
		Component verticalGlue = Box.createVerticalGlue();
		contentPanel.add(verticalGlue);
		
		JButton backButton = new JButton("Enrere");
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setBackground(new Color(227, 186, 149));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ControladorPresentacio.getInstance().showMenuPartida();
			}
		});
		contentPanel.add(backButton);
	}
}
