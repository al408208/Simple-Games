package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.Constantes;
import assets.Recursos;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class DialogoInfo extends JDialog {

	/**
	 Para el comienzo de la partida
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfHost;
	private JTextField tfPuerto;
	private String instrucciones="El juego es simple. Todos sereis Mortys menos uno, Rick. Cada personaje se elige aleatoriamente y "
			+ "cada uno tiene su habilidad especial, explicada más abajo, que se usa con el espacio. Los rayos te recargarán  (abajo a la izquierda) y podras usar tu habilidad especial, pero si te chocas con otro compañero se te desactivará."
			+ " Rick deberá cazar a todos los Mortys, sobrevive hasta ser el último con vida. ¡SUERTE!";
	private JLabel lblp4;

	/**
	 * Create the dialog.
	 */
	public DialogoInfo(JFrame padre, boolean modal) {
		super(padre,"Configuracion",modal);
		setBounds(100, 100, 720, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblHost = new JLabel("Host:");
		lblHost.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHost.setBounds(30, 20, 58, 25);
		contentPanel.add(lblHost);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPuerto.setBounds(357, 20, 58, 25);
		contentPanel.add(lblPuerto);
		
		tfHost = new JTextField("192.168.1.131");
		tfHost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfHost.setColumns(10);
		tfHost.setBounds(139, 20, 100, 25);
		contentPanel.add(tfHost);
		
		tfPuerto = new JTextField(String.valueOf(Constantes.PORTSERVER));
		tfPuerto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfPuerto.setColumns(10);
		tfPuerto.setBounds(469, 20, 100, 25);
		contentPanel.add(tfPuerto);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setBounds(30, 60, 659, 116);
		textArea.setText(instrucciones);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		contentPanel.add(textArea);
		
		JLabel lbl1 = new JLabel();
		lbl1.setIcon(new ImageIcon(Recursos.p1));
		lbl1.setBounds(35, 210, 100, 143);
		contentPanel.add(lbl1);
		
		JLabel lbl2 = new JLabel();
		lbl2.setIcon(new ImageIcon(Recursos.p2));
		lbl2.setBounds(155, 210, 100, 140);
		contentPanel.add(lbl2);
		
		JLabel lbl3 = new JLabel();
		lbl3.setIcon(new ImageIcon(Recursos.p3));
		lbl3.setBounds(265, 210, 100, 140);
		contentPanel.add(lbl3);
		
		JLabel lbl4 = new JLabel();
		lbl4.setIcon(new ImageIcon(Recursos.p4));
		lbl4.setBounds(375, 210, 105, 140);
		contentPanel.add(lbl4);
		
		JLabel lbl5 = new JLabel();
		lbl5.setIcon(new ImageIcon(Recursos.p5));
		lbl5.setBounds(495, 210, 100, 140);
		contentPanel.add(lbl5);
		
		JLabel lbl6 = new JLabel();
		lbl6.setIcon(new ImageIcon(Recursos.p6));
		lbl6.setBounds(589, 210, 100, 140);
		contentPanel.add(lbl6);
		
		JLabel lblp1 = new JLabel("Teletransportarse");
		lblp1.setBounds(600, 343, 100, 36);
		contentPanel.add(lblp1);
		
		JLabel lblp2 = new JLabel("Escudo anti Rick");
		lblp2.setBounds(165, 343, 100, 36);
		contentPanel.add(lblp2);
		
		JLabel lblp3 = new JLabel("Super velocidad");
		lblp3.setBounds(283, 343, 100, 36);
		contentPanel.add(lblp3);
		
		lblp4 = new JLabel("Tama\u00F1o mini");
		lblp4.setBounds(399, 343, 100, 36);
		contentPanel.add(lblp4);
		
		JLabel lblp5 = new JLabel("2 vidas");
		lblp5.setBounds(518, 343, 58, 36);
		contentPanel.add(lblp5);
		
		JLabel lblp6 = new JLabel("Sin habilidad, pero es más");
		lblp6.setBounds(27, 343, 125, 36);
		contentPanel.add(lblp6);
		
		JLabel lblp62 = new JLabel("r\u00E1pido que los dem\u00E1s");
		lblp62.setBounds(27, 358, 125, 36);
		contentPanel.add(lblp62);
		
		JLabel lblMortyBicho = new JLabel("Rick Cyborg");
		lblMortyBicho.setBounds(50, 190, 58, 14);
		contentPanel.add(lblMortyBicho);
		
		JLabel lblMortyRobot = new JLabel("Morty Robot");
		lblMortyRobot.setBounds(170, 190, 67, 14);
		contentPanel.add(lblMortyRobot);
		
		JLabel lblMortyFuego = new JLabel("Morty Fuego");
		lblMortyFuego.setBounds(285, 190, 67, 14);
		contentPanel.add(lblMortyFuego);
		
		JLabel lblMortyPez = new JLabel("Morty Pez");
		lblMortyPez.setBounds(400, 190, 67, 14);
		contentPanel.add(lblMortyPez);
		
		JLabel lblMortyEsqueleto = new JLabel("Morty Esqueleto");
		lblMortyEsqueleto.setBounds(493, 190, 78, 14);
		contentPanel.add(lblMortyEsqueleto);
		
		JLabel lblRickCyborg = new JLabel("Morty Bicho");
		lblRickCyborg.setBounds(620, 190, 67, 14);
		contentPanel.add(lblRickCyborg);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ok();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		this.setLocation(450, 200); 
        this.setResizable(false); 
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
	}
	
	private void ok() {
	
	((VPrincipal)this.getParent()).config(this.tfHost.getText(),Integer.parseInt(this.tfPuerto.getText()));
	
	}
}
