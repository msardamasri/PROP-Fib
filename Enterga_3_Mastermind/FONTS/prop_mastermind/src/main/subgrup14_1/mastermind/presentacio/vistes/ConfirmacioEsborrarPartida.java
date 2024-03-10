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
import javax.swing.border.EmptyBorder;

import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ConfirmacioEsborrarPartida extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructora de la vista ConfirmacioEsborrarPartida
	 * @param uidPartida Identificador de la partida
	 * @param n Nombre de partides guardades
	 */
	public ConfirmacioEsborrarPartida(String uidPartida, Integer n) {
		getContentPane().setBackground(new Color(219, 167, 119));
		setTitle("Confirmacio");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-320/2, screenSize.height/2-150/2, 320, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(219, 167, 119));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			Component horizontalGlue = Box.createHorizontalGlue();
			contentPanel.add(horizontalGlue);
		}
		{
			JLabel lblNewLabel = new JLabel("Estas segur que vols esborrar la partida?");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			contentPanel.add(lblNewLabel);
		}
		{
			Component horizontalGlue = Box.createHorizontalGlue();
			contentPanel.add(horizontalGlue);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(219, 167, 119));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
			{
				Component horizontalGlue = Box.createHorizontalGlue();
				buttonPane.add(horizontalGlue);
			}
			{
				JButton okButton = new JButton("Si");
				okButton.setBackground(new Color(227, 186, 149));
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ControladorPresentacio.getInstance().esborrarPartida(uidPartida);
						dispose();
						if (n > 1) ControladorPresentacio.getInstance().showSeleccioPartidaGuardada();
						else ControladorPresentacio.getInstance().showMenuPartida();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				buttonPane.add(horizontalStrut);
			}
			{
				JButton cancelButton = new JButton("No");
				cancelButton.setBackground(new Color(227, 186, 149));
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						ControladorPresentacio.getInstance().showSeleccioPartidaGuardada();
					}
				});
				buttonPane.add(cancelButton);
			}
			{
				Component horizontalGlue = Box.createHorizontalGlue();
				buttonPane.add(horizontalGlue);
			}
		}
	}

}
