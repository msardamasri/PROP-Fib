package main.subgrup14_1.mastermind.presentacio.vistes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class NormativaJoc extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructora de la vista NormativaJoc
	 */
	public NormativaJoc() {
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-850/2, screenSize.height/2-600/2, 850, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(219, 167, 119));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
			{
				JPanel panellText = new JPanel();
				panellText.setBackground(new Color(219, 167, 119));
				contentPanel.add(panellText);
				{
					JLabel lblNewLabel = new JLabel("<html><div>"
							+ "<h1 style=\"text-align: justify;\">Normativa de Mastermind</h1>"
							+ "</div>"
							+ "<div style=\"text-align: justify;\">"
							+ "<h2>1. Objectiu</h2>"
							+ "</div>"
							+ "<div>"
							+ "<p style=\"text-align: justify;\" align=\"justify\">Mastermind pot ser jugat com a CODEMAKER (encarregat de fromular el codi secret) o CODEBREAKER (encarregat de desxifrar el codi secret).</p>"
							+ "<ul style=\"text-align: justify;\">"
							+ "<li>En cas de <strong>jugar com a CODEMAKER</strong> has d'idear un codi secret format per quatre colors seguint un patro valid en funcio de la dificultat:<br></br>"
							+ "per la dificultat facil pots escollir entre 4 colors, sense possibiltiat que aquests es repeteixin, en la dificultat intermitja pots escollir entre 5 colors, <br></br>"
							+ "podent repetir-los, i en dificultat dificil pots escollir entre 6 colors, tamb&eacute; amb la possibilitat de repetir-los.</li>"
							+ "<li>En cas de <strong>jugar com a CODEBREAKER</strong> has de desxifrar el codi secret introduit pel CODEMAKER, disposes d'un panell de seleccio per <br></br>"
							+ "formular l'intent a la part inferior de la pantalla, on hauras de seguir el patro de codi en funcio de la dificultat: per la dificultat facil pots <br></br>"
							+ "escollir entre 4 colors, sense possibilitat que aquests es repeteixin, en la dificultat intermitja pots escollir entre 5 colors, poden repetir-los, i en <br></br>"
							+ "dificultat dificil pots escollir entre 6 colors, tamb&eacute; amb la possibilitat de repetir-los. Un cop realitzat l'intent observaras <br></br>"
							+ "la correcio d'aquest a la part esquerra de la pantalla, on cada punt negre indicara un color encertat en la posicio correcta i cada punt <br></br>"
							+ "blanc indicara un color encertat pero en una posicio incorrecta. La posicio dels punts de correcio no es correspon amb la posicio dels colors <br></br>"
							+ "del codi introduit, sempre es mostraran primer els punts negres <br></br>"
							+ "seguits dels blancs.</li>"
							+ "</ul>"
							+ "<p>El nombre m&agrave;xim d'intents que es poden realitzar per a les diferents dificultats es:</p>"
							+ "<ul>"
							+ "<li>Facil: 12 intents.</li>"
							+ "<li>Intermitja: 10 intents.</li>"
							+ "<li>Dificil: 8 intents.</li>"
							+ "</ul>"
							+ "<h2 style=\"text-align: justify;\">2. Puntuacio</h2>"
							+ "<p style=\"text-align: justify;\">El sistema de puntuacio varia en funcio de la dificultat i del rol escollit.</p>"
							+ "<ul>"
							+ "<li style=\"text-align: justify;\"><strong>Jugant com a CODEMAKER</strong>: es parteix de 0 punts de base i per a cada intent que l'algorisme del CODEBREAKER <br></br>"
							+ "realitzi es sumaran punts en funcio de la dificultat (300 en dificultat facil, 200 en dificultat intermitja i 100 en dificultat dificil).</li>"
							+ "<li><strong>Jugant com a CODEBREAKER</strong>: es parteix dels punts base (1200 per a dificultat facil, 2000 per a dificultat intermitja i 2400 per a dificultat <br></br>"
							+ "dificil) i per a cada intent que es realizti es descomptaran certs punts en funcio de la dificultat (100 en dificultat facil, 200 en dificultat <br></br>"
							+ "intermitja i 300 en dificultat dificil). A mes, per a cada pista usada tambe es descompara certa puntuacio, per a mes informacio llegir l'apartat 3.</li>"
							+ "</ul>"
							+ "<h2 style=\"text-align: justify;\">3. Pistes</h2>"
							+ "<p style=\"text-align: justify;\">Quan es juga com a CODEBREAKER es permet usar pistes si es te dificultat per desxifrar el codi secret. Quan es prem el boto de pista es <br></br>"
							+ "descomptara certa puntuacio en funcio de la dificultat de la partida (400 en dificultat facil, 700 en dificultat intermitja i 900 en dificultat dificil) i es mostrara un dels <br></br>"
							+ "colors del codi. Si al premer el boto de pista la dificultat pasa a ser zero, o ja ho era, el panell de pistes mostrara el codi secret automaticament. En usar una pista la <br></br>"
							+ "partida sera guardada automaticament.</p>"
							+ "<h2 style=\"text-align: justify;\">4. Algorismes</h2>"
							+ "<p>Quan es juga com a CODEMAKER s'usaran diferents algorismes per a desxifrar el codi en funcio de la dificultat escollida. Els algorismes son els seguents:</p>"
							+ "<ul>"
							+ "<li>Facil: <em>Five-Guess.</em></li>"
							+ "<li>Intermitja: Algorisme genetic.</li>"
							+ "<li>Dificil: Algorisme genetic.</li>"
							+ "</ul>"
							+ "<h2 style=\"text-align: justify;\">5. Resultats</h2>"
							+ "<p style=\"text-align: justify;\">En acabar una partida, es pot consultar l'apartat de ranquing on es veuran, per ordre, tots els usuaris, el nombre de partides que han <br></br>"
							+ "jugat i la seva puntuacio maxima.</p>"
							+ "</div></html>");
					panellText.add(lblNewLabel);
				}
				JScrollPane scrollBar = new JScrollPane(panellText,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				contentPanel.add(scrollBar);
			}
			{
				JPanel panellBotons = new JPanel();
				panellBotons.setBackground(new Color(219, 167, 119));
				contentPanel.add(panellBotons);
				panellBotons.setLayout(new BoxLayout(panellBotons, BoxLayout.X_AXIS));
				{
					Component horizontalGlue = Box.createHorizontalGlue();
					panellBotons.add(horizontalGlue);
				}
				{
					JButton btnEnrere = new JButton("Tancar");
					btnEnrere.setBackground(new Color(227, 186, 149));
					btnEnrere.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					panellBotons.add(btnEnrere);
					{
						Component horizontalGlue = Box.createHorizontalGlue();
						panellBotons.add(horizontalGlue);
					}
				}
			}
		}
	}

}
