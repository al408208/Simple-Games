package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import assets.Recursos;

public class MiPanel extends JPanel{//metodo para darle fondo a un panel

	/**
	 Panel para tener imagen de fondo 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(Recursos.portada, 0, 0, 1340, 650, null);
	}
}
