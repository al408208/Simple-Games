package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.Constantes;
import assets.Recursos;
import model.Personaje;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Finjuego extends JDialog {

	/**
	 Dialogo para mostrar a los 3 campeones
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<Personaje> capturados;


	/**
	 * Create the dialog.
	 */
	public Finjuego(ArrayList<Personaje> ps) {
		this.capturados = ps;
		setTitle("El juego ha terminado");
		setBounds(100, 100, 465, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(Recursos.podio));
		label.setBounds(48, 142, 350, 165);
		contentPanel.add(label);

		JLabel lbl1 = new JLabel();
		if(Constantes.maxJugadores>1) {//esto lo hago para que puedas ir cambiando el max de jugadores
			lbl1.setIcon(img(capturados.get(capturados.size()-1).getPersonaje()));
		}		
		lbl1.setBounds(175, 11, 105, 143);
		contentPanel.add(lbl1);
		
		JLabel lbl2 = new JLabel();
		if(Constantes.maxJugadores>2) {
			lbl2.setIcon(img(capturados.get(capturados.size()-2).getPersonaje()));
		}
		lbl2.setBounds(57, 50, 105, 143);
		contentPanel.add(lbl2);
	
		JLabel lbl3 = new JLabel();
		if(Constantes.maxJugadores>3) {
			lbl3.setIcon(img(capturados.get(capturados.size()-3).getPersonaje()));
		}
		lbl3.setBounds(293, 50, 105, 143);
		contentPanel.add(lbl3);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		} 
	}
	
	private ImageIcon img(int n) {
		switch(n){
		case 2:
			return(new ImageIcon(Recursos.p2));
		case 3:
			return(new ImageIcon(Recursos.p3));
		case 4: 
			return(new ImageIcon(Recursos.p4));
		case 5:
			return(new ImageIcon(Recursos.p5));
	    case 6: 
	    	return(new ImageIcon(Recursos.p6));
		default:
			return null;			
		}
		
	}
	
}
