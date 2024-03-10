package main.subgrup14_1.mastermind.presentacio.vistes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class AlertaPopUp extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	
	/**
	 * Constructora de la vista d'AlertaPopUp
	 * @param text Text a mostrar en la finestra
	 */
	public AlertaPopUp(String text) {
		setTitle("Atencio!");
		setType(Type.POPUP);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-300/2, screenSize.height/2-175/2, 300, 175);
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(219, 167, 119));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		
		Component horizontalGlue = Box.createHorizontalGlue();
		contentPanel.add(horizontalGlue);
		
		JLabel lblNewLabel = new JLabel("<html>" + text + "</html>");
		contentPanel.add(lblNewLabel);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		contentPanel.add(horizontalGlue_1);
	}

}
