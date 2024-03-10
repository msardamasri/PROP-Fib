package main.subgrup14_1.mastermind.utils;
import javax.swing.JButton;

import main.subgrup14_1.mastermind.domini.enums.Dificultat;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class BotoCircular extends JButton{

	private static final long serialVersionUID = 1L;
	private boolean mouseOver = false;
	private boolean mousePressed = false;
	private boolean pressable;
	private Color c;
	private Dificultat d;
	private boolean invisible = false;

	/**
	 * Converteix un enter a color
	 * @param i Enter a convertir
	 * @return Color corresponent a l'enter
	 */
	private Color intToCol(Integer i) {
		if (i == 0) return Color.RED;
		if (i == 1) return Color.BLUE;
		if (i == 2) return Color.GREEN;
		if (i == 3) return Color.YELLOW;
		if (i == 4) return Color.MAGENTA;
		if (i == 5) return Color.ORANGE;
		if (i == 6) return Color.BLACK;
		if (i == 7) return Color.WHITE;
		return new Color(0f, 0f, 0f, 0f);
	}
	/**
	 * Obtenir el codi del color del boto
	 * @return Obte el codi del color del boto
	 */
	public Integer getColor() {
		if (c == Color.RED) return 0;
		if (c == Color.BLUE) return 1;
		if (c == Color.GREEN) return 2;
		if (c == Color.YELLOW) return 3;
		if (c == Color.MAGENTA) return 4;
		if (c == Color.ORANGE) return 5;
		if (c == Color.BLACK) return 6;
		if (c == Color.WHITE) return 7;
		return 8;
	}
	/**
	 * Constructora amb els parametres especificats
	 * @param text Text del boto
	 * @param isPressable Boolea que indica si esta habilitat
	 * @param dif Dificultat de la partida
	 * @param col Color a mostrar
	 */
	public BotoCircular(String text, boolean isPressable, Dificultat dif, Integer col){
		super(text);
		pressable = isPressable;
		d = dif;
		if (col != null) c = intToCol(col);
		else c = Color.RED;
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);

		MouseAdapter mouseListener = new MouseAdapter(){

			@Override
			public void mousePressed(MouseEvent me){
				if(pressable && contains(me.getX(), me.getY())){
					mousePressed = true;
					repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent me){
				mousePressed = false;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent me){
				mouseOver = false;
				mousePressed = false;
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent me){
				mouseOver = contains(me.getX(), me.getY());
				repaint();
			}
		};

		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);		
	}
	/**
	 * Construcotra amb els paremetres epsecificats
	 * @param text Text a mostrar
	 * @param inv Boolea que indica si el boto ha de ser invisible
	 */
	public BotoCircular(String text, boolean inv){
		super(text);
		pressable = false;
		d = null;
		c = new Color(0f, 0f, 0f, 0f);
		invisible = inv;
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);

		MouseAdapter mouseListener = new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent me){}
			@Override
			public void mouseReleased(MouseEvent me){}
			@Override
			public void mouseExited(MouseEvent me){}
			@Override
			public void mouseMoved(MouseEvent me){}
		};

		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);		
	}

	/**
	 * Obte diametre del boto
	 * @return diametre del boto
	 */
	private int getDiameter(){
		int diameter = Math.min(getWidth(), getHeight());
		return diameter;
	}

	@Override
	public Dimension getPreferredSize(){
		FontMetrics metrics = getGraphics().getFontMetrics(getFont());
		int minDiameter = 10 + Math.max(metrics.stringWidth(getText()), metrics.getHeight());
		return new Dimension(minDiameter, minDiameter);
	}

	@Override
	public boolean contains(int x, int y){
		int radius = getDiameter()/2;
		return Point2D.distance(x, y, getWidth()/2, getHeight()/2) < radius;
	}

	/**
	 * Canvia de color
	 */
	private void nextColor() {
		if (d == Dificultat.FACIL) {
			if (c == Color.RED) c = Color.BLUE;
			else if (c == Color.BLUE) c = Color.GREEN;
			else if (c == Color.GREEN) c = Color.YELLOW;
			else if (c == Color.YELLOW) c = Color.RED;
		}
		else if (d == Dificultat.INTERMIG) {
			if (c == Color.RED) c = Color.BLUE;
			else if (c == Color.BLUE) c = Color.GREEN;
			else if (c == Color.GREEN) c = Color.YELLOW;
			else if (c == Color.YELLOW) c = Color.MAGENTA;
			else if (c == Color.MAGENTA) c = Color.RED;
		}
		else {
			if (c == Color.RED) c = Color.BLUE;
			else if (c == Color.BLUE) c = Color.GREEN;
			else if (c == Color.GREEN) c = Color.YELLOW;
			else if (c == Color.YELLOW) c = Color.MAGENTA;
			else if (c == Color.MAGENTA) c = Color.ORANGE;
			else if (c == Color.ORANGE) c = Color.RED;
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		int diameter = getDiameter();
		int radius = diameter/2;
		
		if(mousePressed) nextColor();
		g.setColor(c);
		g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);

		g.setColor(Color.BLACK);
		if(mouseOver && pressable) g.setColor(Color.LIGHT_GRAY);
		if(invisible) g.setColor(new Color(0f, 0f, 0f, 0f));
		g.drawOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);

		g.setColor(Color.BLACK);
		g.setFont(getFont());
		if(invisible) g.setColor(new Color(0f, 0f, 0f, 0f));
		FontMetrics metrics = g.getFontMetrics(getFont());
		int stringWidth = metrics.stringWidth(getText());
		int stringHeight = metrics.getHeight();
		g.drawString(getText(), getWidth()/2 - stringWidth/2, getHeight()/2 + stringHeight/4);
	}
}