package main.subgrup14_1.mastermind.presentacio.vistes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;
import main.subgrup14_1.mastermind.presentacio.controladors.ControladorPresentacio;
import main.subgrup14_1.mastermind.utils.BotoCircular;
import main.subgrup14_1.mastermind.utils.Pair;
import main.subgrup14_1.mastermind.utils.PartidaEnJoc;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class Joc extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Integer numPositions = 4;
	private Integer numTorns;
	
	private JPanel panell_correccions_llista;
	private JPanel panell_intents_llista;
	private JButton submitButton;
	private JButton pistaButton;
	private JLabel puntuacio;
	private JLabel pistesRestLabel;
	private JPanel panell_stats;
	
	private PartidaEnJoc partida;
	
	/**
	 * Construeix un panell amb els intents
	 * @param intent Intents de codi
	 * @return JPanel amb tots els intents afegits
	 */
	private JPanel pintaIntent(List<Integer> intent) {
		JPanel panell_intent = new JPanel();
		panell_intent.setBackground(new Color(16, 137, 62));
		panell_intent.setLayout(new BoxLayout(panell_intent, BoxLayout.X_AXIS));
		for(int i = 0; i < intent.size(); i++) {
			Component hg = Box.createHorizontalGlue();
			panell_intent.add(hg);
			BotoCircular b = new BotoCircular(" ", false, null, intent.get(i));
			panell_intent.add(b);
		}
		for(int i = intent.size(); i < numPositions; i++) {
			Component hg = Box.createHorizontalGlue();
			panell_intent.add(hg);
			BotoCircular b = new BotoCircular(" ", false, null, 8);
			panell_intent.add(b);
		}
		Component hg = Box.createHorizontalGlue();
		panell_intent.add(hg);
		return panell_intent;
	};
	
	/**
	 * Construeix un panell amb les correccions
	 * @param correction Correccions
	 * @param blank Boolean que indica si s'ha de deixar en blanc
	 * @return JPanel amb totes les correccions afegides
	 */
	private JPanel pintaCorreccio(Pair<Integer, Integer> correction, boolean blank) {
		JPanel panell_correccio = new JPanel();
		panell_correccio.setBackground(new Color(219, 167, 119));
		panell_correccio.setLayout(new BoxLayout(panell_correccio, BoxLayout.X_AXIS));
		if (!blank) {
			for (int i = 0; i < correction.getR(); ++i) {
				Component hg = Box.createHorizontalGlue();
				panell_correccio.add(hg);
				BotoCircular b = new BotoCircular("", false, null, 6);
				panell_correccio.add(b);
			}	
			for (int i = 0; i < correction.getL(); ++i) {
				Component hg = Box.createHorizontalGlue();
				panell_correccio.add(hg);
				BotoCircular b = new BotoCircular("", false, null, 7);
				panell_correccio.add(b);
			}
			for (int i = 0; i < numPositions-correction.getR()-correction.getL(); ++i) {
				Component hg = Box.createHorizontalGlue();
				panell_correccio.add(hg);
				BotoCircular b = new BotoCircular("", false, null, 8);
				panell_correccio.add(b);
			}
		}
		else {
			for (int i = 0; i < numPositions; ++i) {
				Component hg = Box.createHorizontalGlue();
				panell_correccio.add(hg);
				BotoCircular b = new BotoCircular("", false, null, 8);
				panell_correccio.add(b);
			}
		}
		Component hg = Box.createHorizontalGlue();
		panell_correccio.add(hg);
		return panell_correccio;
	};
	
	/**
	 * Avisa a l'usuari que la partida ha acabat
	 * @param codebreaker Boolea que indica si l'usuari esta jugant com a codebreaker
	 */
	private void partidaAcabada(boolean codebreaker) {
		String msg;
		if (codebreaker && partida.getCorreccions().get(partida.getCorreccions().size()-1).getR() == 4) msg = "Enhorabona, has desxifrat el codi secret!";
		else if (codebreaker) msg = "Oh no!, no has pogut desxifrar el codi secret";
		else if (partida.getCorreccions().get(partida.getCorreccions().size()-1).getR() == 4)  msg = "Oh no!, he desxifrat el teu codi";
		else msg = "Enhorabona, no he pogut desxifrar el teu codi!";
		ControladorPresentacio.getInstance().showPopUp(msg);
	}
	
	/**
	 * Calcula les pistes usades
	 * @return Nombre de pistes usades per l'usuari
	 */
	public Integer pistesUsades() {
		Integer p = partida.getPuntuacio();
		if (p == 0) return 4;
		Dificultat dif = partida.getDificultat();
		if (dif == Dificultat.FACIL) {
			p += partida.getIntents().size() * 100;
			return (1200 - p)/400;
		}
		else if (dif == Dificultat.INTERMIG) {
			p += partida.getIntents().size() * 200;
			return (2000 - p)/700;
		}
		else {
			p += partida.getIntents().size() * 300;
			return (2400 - p)/900;
		}
	}
	
	/**
	 * Construeix un panell amb les pistes usades
	 * @return JPanel amb les pistes usades
	 */
	public JPanel pintaPistes() {
		try {
    		List<Integer> codiSecret = ControladorPresentacio.getInstance().codiJoc();
			partida = ControladorPresentacio.getInstance().infoPartidaEnJoc();
			Integer pistes = pistesUsades();
	    	List<Integer> pista = new ArrayList<Integer>();
	    	for (int i = 0; i < pistes; ++i) pista.add(codiSecret.get(i));
	    	return pintaIntent(pista);
    	}
    	catch (Exception e) {
    		AlertaPopUp dialog = null;
			dialog = new AlertaPopUp(e.getMessage());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		return pintaIntent(new ArrayList<Integer>());
	}
	
	/**
	 * Constructora de la vista Joc
	 * @param codebreaker Boolea que indica si el jugador juga com a codebreaker
	 */
	public Joc(boolean codebreaker) {
		setTitle("Partida");
		getContentPane().setBackground(new Color(219, 167, 119));
		partida = ControladorPresentacio.getInstance().infoPartidaEnJoc();
		Dificultat diff = partida.getDificultat();
		if(diff == Dificultat.FACIL) numTorns = 12;
		else if(diff == Dificultat.INTERMIG) numTorns = 10;
		else if (diff == Dificultat.DIFICIL) numTorns = 8;
		
		List<List<Integer>> intents = partida.getIntents();
		List<Pair<Integer, Integer>> correccions = partida.getCorreccions();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2-500/2, screenSize.height/2-700/2, 500, 700);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(219, 167, 119));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(219, 167, 119));
		contentPanel.add(toolBar);
		
		panell_stats = new JPanel();
		panell_stats.setBackground(new Color(219, 167, 119));
		toolBar.add(panell_stats);
		panell_stats.setLayout(new BoxLayout(panell_stats, BoxLayout.X_AXIS));
		
		puntuacio = new JLabel("Puntuaci\u00F3: " + partida.getPuntuacio());
		puntuacio.setAlignmentX(Component.CENTER_ALIGNMENT);
		puntuacio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panell_stats.add(puntuacio);
		
		Component horizontalGlue_6 = Box.createHorizontalGlue();
		panell_stats.add(horizontalGlue_6);
		
		if (codebreaker) {
			Integer aux = 2 - pistesUsades();
			if (aux < 0) aux = 0;
			pistesRestLabel = new JLabel("Pistes restants: " + aux.toString());
			pistesRestLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pistesRestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			panell_stats.add(pistesRestLabel);
			
			Component horizontalGlue = Box.createHorizontalGlue();
			panell_stats.add(horizontalGlue);
		}
		
		JButton boto_pausa = new JButton("| |");
		boto_pausa.setBackground(new Color(227, 186, 149));
		boto_pausa.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		    	ControladorPresentacio.getInstance().showMenuPausa(partida.getAcabada());
		    }
		});
		panell_stats.add(boto_pausa);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.65);
		splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(splitPane);
		
		JPanel panell_correccions = new JPanel();
		panell_correccions.setBackground(new Color(219, 167, 119));
		panell_correccions.setForeground(new Color(0, 0, 0));
		splitPane.setRightComponent(panell_correccions);
		panell_correccions.setLayout(new BoxLayout(panell_correccions, BoxLayout.Y_AXIS));
		
		panell_correccions_llista = new JPanel();
		panell_correccions_llista.setAlignmentY(Component.TOP_ALIGNMENT);
		panell_correccions_llista.setBackground(new Color(219, 167, 119));
		panell_correccions.add(panell_correccions_llista);
		panell_correccions_llista.setLayout(new BoxLayout(panell_correccions_llista, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < correccions.size(); i++) {
			Component verticalGlue = Box.createVerticalGlue();
			panell_correccions_llista.add(verticalGlue);
			JPanel correccio = pintaCorreccio(correccions.get(i), false);
			panell_correccions_llista.add(correccio);
		};
		for(int i = 0; i < numTorns-correccions.size(); i++) {
			Component verticalGlue = Box.createVerticalGlue();
			panell_correccions_llista.add(verticalGlue);
			JPanel correccio = pintaCorreccio(null, true);
			panell_correccions_llista.add(correccio);
		};
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		panell_correccions.add(verticalGlue_1);
		
		if (codebreaker) {
			JSeparator separator_1 = new JSeparator();
			separator_1.setForeground(new Color(0, 0, 0));
			separator_1.setBackground(new Color(0, 0, 0));
			panell_correccions.add(separator_1);
			
			JPanel panell_dummy_1 = new JPanel();
			panell_dummy_1.setBackground(new Color(0f, 0f, 0f, 0f));
			panell_correccions.add(panell_dummy_1);
			panell_dummy_1.setLayout(new BoxLayout(panell_dummy_1, BoxLayout.X_AXIS));
			
			JPanel pista = pintaPistes();
			pista.setBackground(new Color(219, 167, 119));
			panell_dummy_1.add(pista);
			
			Component verticalStrut_2 = Box.createVerticalStrut(15);
			panell_correccions.add(verticalStrut_2);
			
			JPanel panell_dummy_2 = new JPanel();
			panell_dummy_2.setBackground(new Color(219, 167, 119));
			panell_correccions.add(panell_dummy_2);
			panell_dummy_2.setLayout(new BoxLayout(panell_dummy_2, BoxLayout.X_AXIS));
			
			pistaButton = new JButton("Usar pista");
			pistaButton.setAlignmentX(0.5f);
			pistaButton.setBackground(new Color(227, 186, 149));
			
			pistaButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	ControladorPresentacio.getInstance().usaPista();
			    	partida = ControladorPresentacio.getInstance().infoPartidaEnJoc();
			    	JPanel pistes = pintaPistes();
			    	pistes.setBackground(new Color(219, 167, 119));
			    	panell_dummy_1.removeAll();
			    	panell_dummy_1.add(pistes);
			    	panell_dummy_1.revalidate();
			    	Integer aux2 = 2 - pistesUsades();
					if (aux2 < 0) aux2 = 0;
					final Integer aux3 = aux2;
			    	pistesRestLabel.setText("Pistes restants: " + aux3.toString());
			    	puntuacio.setText("Puntuaci\u00F3: " + partida.getPuntuacio());
			    }
			});
			panell_dummy_2.add(pistaButton);
		}
		
		JPanel panell_intents = new JPanel();
		panell_intents.setForeground(new Color(16, 137, 62));
		panell_intents.setBorder(null);
		panell_intents.setBackground(new Color(16, 137, 62));
		splitPane.setLeftComponent(panell_intents);
		panell_intents.setLayout(new BoxLayout(panell_intents, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(16, 137, 62));
		panell_intents.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		panell_intents_llista = new JPanel();
		panell_intents_llista.setBackground(new Color(16, 137, 62));
		panel_2.add(panell_intents_llista);
		panell_intents_llista.setLayout(new BoxLayout(panell_intents_llista, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < intents.size(); i++) {
			Component verticalGlue = Box.createVerticalGlue();
			panell_intents_llista.add(verticalGlue);
			JPanel intent = pintaIntent(intents.get(i));
			panell_intents_llista.add(intent);
		};
		for(int i = 0; i < numTorns-intents.size(); i++) {
			Component verticalGlue = Box.createVerticalGlue();
			panell_intents_llista.add(verticalGlue);
			JPanel intent = pintaIntent(new ArrayList<Integer>());
			panell_intents_llista.add(intent);
		};
		
		Component verticalGlue = Box.createVerticalGlue();
		panel_2.add(verticalGlue);
		
		if (codebreaker) {
			JSeparator separator = new JSeparator();
			separator.setForeground(new Color(0, 0, 0));
			separator.setBackground(new Color(0, 0, 0));
			separator.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			panel_2.add(separator);
			
			JPanel panell_seleccio = new JPanel();
			panell_seleccio.setBackground(new Color(16, 137, 62));
			panel_2.add(panell_seleccio);
			panell_seleccio.setLayout(new BoxLayout(panell_seleccio, BoxLayout.X_AXIS));
			
			Component horizontalGlue_5 = Box.createHorizontalGlue();
			panell_seleccio.add(horizontalGlue_5);
			
			Integer c1 = null, c2 = null, c3 = null, c4 = null;
			if (!partida.getIntents().isEmpty()) {
				c1 = partida.getIntents().get(partida.getIntents().size()-1).get(0);
				c2 = partida.getIntents().get(partida.getIntents().size()-1).get(1);
				c3 = partida.getIntents().get(partida.getIntents().size()-1).get(2);
				c4 = partida.getIntents().get(partida.getIntents().size()-1).get(3);
			}
			
			BotoCircular col1 = new BotoCircular(" ", true, diff, c1);
			col1.setAlignmentX(0.5f);
			col1.setBackground(Color.BLACK);
			panell_seleccio.add(col1);
			
			Component horizontalGlue_1 = Box.createHorizontalGlue();
			panell_seleccio.add(horizontalGlue_1);
			
			BotoCircular col2 = new BotoCircular(" ", true, diff, c2);
			col2.setAlignmentX(0.5f);
			col2.setBackground(Color.BLACK);
			panell_seleccio.add(col2);
			
			Component horizontalGlue_2 = Box.createHorizontalGlue();
			panell_seleccio.add(horizontalGlue_2);
			
			BotoCircular col3 = new BotoCircular(" ", true, diff, c3);
			col3.setAlignmentX(0.5f);
			col3.setBackground(Color.BLACK);
			panell_seleccio.add(col3);
			
			Component horizontalGlue_4 = Box.createHorizontalGlue();
			panell_seleccio.add(horizontalGlue_4);
			
			BotoCircular col4 = new BotoCircular(" ", true, diff, c4);
			col4.setAlignmentX(0.5f);
			col4.setBackground(Color.BLACK);
			panell_seleccio.add(col4);
			
			Component horizontalGlue_3 = Box.createHorizontalGlue();
			panell_seleccio.add(horizontalGlue_3);
			
			Component verticalStrut = Box.createVerticalStrut(15);
			panel_2.add(verticalStrut);
			
			JPanel panell_bSubmit = new JPanel();
			panell_bSubmit.setBorder(null);
			panell_bSubmit.setBackground(new Color(16, 137, 62));
			panel_2.add(panell_bSubmit);
			panell_bSubmit.setLayout(new BoxLayout(panell_bSubmit, BoxLayout.X_AXIS));
			
			submitButton = new JButton("Provar combinacio");
			submitButton.setAlignmentX(0.5f);
			submitButton.setBackground(new Color(227, 186, 149));
			
			submitButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	List<Integer> intent = new ArrayList<Integer>();
			        intent.add(col1.getColor());
			        intent.add(col2.getColor());
			        intent.add(col3.getColor());
			        intent.add(col4.getColor());
		        	partida = ControladorPresentacio.getInstance().intentarCodi(intent);
		        	if (partida != null) {
			        	puntuacio.setText("Puntuaci\u00F3: " + partida.getPuntuacio().toString());
			    		       	
			        	panell_intents_llista.removeAll();
			        	for(int i = 0; i < partida.getIntents().size(); i++) {
			        		Component verticalGlue = Box.createVerticalGlue();
			        		panell_intents_llista.add(verticalGlue);
			    			JPanel intents = pintaIntent(partida.getIntents().get(i));
			    			panell_intents_llista.add(intents);
			    		};
			    		for(int i = 0; i < numTorns-partida.getIntents().size(); i++) {
			    			Component verticalGlue = Box.createVerticalGlue();
			    			panell_intents_llista.add(verticalGlue);
			    			JPanel intents = pintaIntent(new ArrayList<Integer>());
			    			panell_intents_llista.add(intents);
			    		};
			    		
			    		panell_correccions_llista.removeAll();
			    		for(int i = 0; i < correccions.size(); i++) {
			    			Component verticalGlue = Box.createVerticalGlue();
			    			panell_correccions_llista.add(verticalGlue);
			    			JPanel correccio = pintaCorreccio(correccions.get(i), false);
			    			panell_correccions_llista.add(correccio);
			    		};
			    		for(int i = 0; i < numTorns-correccions.size(); i++) {
			    			Component verticalGlue = Box.createVerticalGlue();
			    			panell_correccions_llista.add(verticalGlue);
			    			JPanel correccio = pintaCorreccio(null, true);
			    			panell_correccions_llista.add(correccio);
			    		};
			        	
			        	panell_correccions_llista.revalidate();
			        	panell_intents_llista.revalidate();
			        	
			        	if (partida.getAcabada()) {
			        		submitButton.setEnabled(false);
			        		pistaButton.setEnabled(false);
			        		partidaAcabada(codebreaker);
			        	}
				    }
			    }
			});
			panell_bSubmit.add(submitButton);
			if (partida.getAcabada()) {
	    		submitButton.setEnabled(false);
	    		pistaButton.setEnabled(false);
	    		partidaAcabada(codebreaker);
	    	}
		}
		else if (partida.getAcabada()) {
    		partidaAcabada(codebreaker);
    	}
	}
}
