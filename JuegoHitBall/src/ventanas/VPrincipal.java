package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.Recursos;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class VPrincipal extends JFrame {

	private JPanel contentPane;
	private GameEngine game;
	private JLabel p; 
	private MiPanel mipanel;
	public Recursos r;
	public JLabel vida;
	public JLabel vida2;
	public JLabel vida3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VPrincipal frame = new VPrincipal();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VPrincipal() {
		r.loadAssets();
		setIconImage(r.icono);
		setTitle("Jelly Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 816, 700);//no vale 800
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mipanel=new MiPanel(Recursos.caratula);
		mipanel.setBounds(0, 0, 800, 600);
		contentPane.add(mipanel);
		
		game = new GameEngine(this);
		contentPane.add(game);

		JPanel panel = new JPanel();
		panel.setBounds(0, 600, 800, 61);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btn2 = new JButton();
		btn2.setIcon(new ImageIcon(Recursos.yjelly));
		btn2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn2.setForeground(Color.BLACK);
		btn2.setEnabled(false);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.cambio();
				game.requestFocus();
				if(game.isBandera()) {
					Recursos.pausa.stop();
					Recursos.musica.loop();
				}else {
					Recursos.musica.stop();
					Recursos.pausa.loop();
				}
			}		
		});
		btn2.setBounds(430, 10, 170, 41);
		btn2.setBorderPainted(false);
		panel.add(btn2);
		
		JButton btn1 = new JButton();
		btn1.setIcon(new ImageIcon(Recursos.redjelly));
		btn1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn1.setForeground(Color.BLACK);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GameLoop(game).start();
				game.requestFocus();
				btn1.setEnabled(false);
				btn2.setEnabled(true);
			}
		});
		btn1.setBounds(270, 10, 120, 41);
		btn1.setBorderPainted(false);
		panel.add(btn1);
		
		JButton btn3 = new JButton();
		btn3.setIcon(new ImageIcon(Recursos.pjelly));
		btn3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn3.setForeground(Color.BLACK);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.musica.stop();
				dispose();
			}
		});
		btn3.setBounds(640, 10, 110, 41);
		btn3.setBorderPainted(false);
		panel.add(btn3);
		
		p = new JLabel("Goal: 200");
		p.setBounds(10, 15, 128, 30);
		p.setFont(new Font("Tahoma", Font.PLAIN, 25));
		p.setForeground(Color.PINK);
		panel.add(p);
		
		vida = new JLabel();
		vida.setIcon(new ImageIcon(Recursos.vida));
		vida.setBounds(150, 15, 30, 30);
		panel.add(vida);
		
		vida2 = new JLabel();
		vida2.setIcon(new ImageIcon(Recursos.vida));
		vida2.setBounds(185, 15, 30, 30);
		panel.add(vida2);
		
		vida3 = new JLabel();
		vida3.setIcon(new ImageIcon(Recursos.vida));
		vida3.setBounds(220, 15, 30, 30);
		panel.add(vida3);
		
		r.musica.loop();
	}
	public void setP(int punt) {
		this.p.setText("Score: "+String.valueOf(punt));
	}
}
